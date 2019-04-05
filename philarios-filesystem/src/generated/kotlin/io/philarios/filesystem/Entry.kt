package io.philarios.filesystem

import io.philarios.core.Deferred
import io.philarios.core.DslBuilder
import io.philarios.core.Scaffold
import io.philarios.core.Scaffolder
import io.philarios.core.Wrapper
import io.philarios.util.registry.Registry
import kotlin.String
import kotlin.collections.Iterable
import kotlin.collections.List
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

sealed class Entry

data class Directory(val name: String, val entries: List<Entry>) : Entry()

data class File(val name: String, val content: String) : Entry()

sealed class EntrySpec<in C, out T : Entry>

class DirectorySpec<in C>(internal val body: DirectoryBuilder<C>.() -> Unit) : EntrySpec<C, Directory>()

class FileSpec<in C>(internal val body: FileBuilder<C>.() -> Unit) : EntrySpec<C, File>()

@DslBuilder
interface DirectoryBuilder<out C> {
    val context: C

    fun name(value: String)

    fun <T : Entry> entry(spec: EntrySpec<C, T>)

    fun <T : Entry> entry(ref: EntryRef<T>)

    fun <T : Entry> entry(value: T)

    fun include(body: DirectoryBuilder<C>.() -> Unit)

    fun include(spec: DirectorySpec<C>)

    fun <C2> include(context: C2, body: DirectoryBuilder<C2>.() -> Unit)

    fun <C2> include(context: C2, spec: DirectorySpec<C2>)

    fun <C2> includeForEach(context: Iterable<C2>, body: DirectoryBuilder<C2>.() -> Unit)

    fun <C2> includeForEach(context: Iterable<C2>, spec: DirectorySpec<C2>)
}

@DslBuilder
interface FileBuilder<out C> {
    val context: C

    fun name(value: String)

    fun content(value: String)

    fun include(body: FileBuilder<C>.() -> Unit)

    fun include(spec: FileSpec<C>)

    fun <C2> include(context: C2, body: FileBuilder<C2>.() -> Unit)

    fun <C2> include(context: C2, spec: FileSpec<C2>)

    fun <C2> includeForEach(context: Iterable<C2>, body: FileBuilder<C2>.() -> Unit)

    fun <C2> includeForEach(context: Iterable<C2>, spec: FileSpec<C2>)
}

sealed class EntryRef<T : Entry> {
    internal abstract val key: String
}

class DirectoryRef(override val key: String) : EntryRef<Directory>()

class FileRef(override val key: String) : EntryRef<File>()

internal sealed class EntryShell

internal data class DirectoryShell(var name: Scaffold<String>? = null, var entries: List<Scaffold<Entry>>? = null) : EntryShell(),
        Scaffold<Directory> {
    override suspend fun resolve(registry: Registry): Directory {
        checkNotNull(name) { "Directory is missing the name property" }
        coroutineScope {
            entries?.let{ it.forEach { launch { it.resolve(registry) } } }
        }
        val value = Directory(
            name!!.let{ it.resolve(registry) },
            entries.orEmpty().let{ it.map { it.resolve(registry) } }
        )
        return value
    }
}

internal data class FileShell(var name: Scaffold<String>? = null, var content: Scaffold<String>? = null) : EntryShell(),
        Scaffold<File> {
    override suspend fun resolve(registry: Registry): File {
        checkNotNull(name) { "File is missing the name property" }
        checkNotNull(content) { "File is missing the content property" }
        val value = File(
            name!!.let{ it.resolve(registry) },
            content!!.let{ it.resolve(registry) }
        )
        return value
    }
}

@DslBuilder
internal class DirectoryShellBuilder<out C>(override val context: C, internal var shell: DirectoryShell = DirectoryShell()) : DirectoryBuilder<C> {
    override fun name(value: String) {
        shell = shell.copy(name = Wrapper(value))
    }

    override fun <T : Entry> entry(spec: EntrySpec<C, T>) {
        shell = shell.copy(entries = shell.entries.orEmpty() + EntryScaffolder<C, Entry>(spec).createScaffold(context))
    }

    override fun <T : Entry> entry(ref: EntryRef<T>) {
        shell = shell.copy(entries = shell.entries.orEmpty() + Deferred(ref.key))
    }

    override fun <T : Entry> entry(value: T) {
        shell = shell.copy(entries = shell.entries.orEmpty() + Wrapper(value))
    }

    override fun include(body: DirectoryBuilder<C>.() -> Unit) {
        apply(body)
    }

    override fun include(spec: DirectorySpec<C>) {
        apply(spec.body)
    }

    override fun <C2> include(context: C2, body: DirectoryBuilder<C2>.() -> Unit) {
        val builder = split(context)
        builder.apply(body)
        merge(builder)
    }

    override fun <C2> include(context: C2, spec: DirectorySpec<C2>) {
        val builder = split(context)
        builder.apply(spec.body)
        merge(builder)
    }

    override fun <C2> includeForEach(context: Iterable<C2>, body: DirectoryBuilder<C2>.() -> Unit) {
        context.forEach { include(it, body) }
    }

    override fun <C2> includeForEach(context: Iterable<C2>, spec: DirectorySpec<C2>) {
        context.forEach { include(it, spec) }
    }

    private fun <C2> split(context: C2): DirectoryShellBuilder<C2> = DirectoryShellBuilder(context, shell)

    private fun <C2> merge(other: DirectoryShellBuilder<C2>) {
        this.shell = other.shell
    }
}

@DslBuilder
internal class FileShellBuilder<out C>(override val context: C, internal var shell: FileShell = FileShell()) : FileBuilder<C> {
    override fun name(value: String) {
        shell = shell.copy(name = Wrapper(value))
    }

    override fun content(value: String) {
        shell = shell.copy(content = Wrapper(value))
    }

    override fun include(body: FileBuilder<C>.() -> Unit) {
        apply(body)
    }

    override fun include(spec: FileSpec<C>) {
        apply(spec.body)
    }

    override fun <C2> include(context: C2, body: FileBuilder<C2>.() -> Unit) {
        val builder = split(context)
        builder.apply(body)
        merge(builder)
    }

    override fun <C2> include(context: C2, spec: FileSpec<C2>) {
        val builder = split(context)
        builder.apply(spec.body)
        merge(builder)
    }

    override fun <C2> includeForEach(context: Iterable<C2>, body: FileBuilder<C2>.() -> Unit) {
        context.forEach { include(it, body) }
    }

    override fun <C2> includeForEach(context: Iterable<C2>, spec: FileSpec<C2>) {
        context.forEach { include(it, spec) }
    }

    private fun <C2> split(context: C2): FileShellBuilder<C2> = FileShellBuilder(context, shell)

    private fun <C2> merge(other: FileShellBuilder<C2>) {
        this.shell = other.shell
    }
}

class EntryScaffolder<in C, out T : Entry>(internal val spec: EntrySpec<C, T>) : Scaffolder<C, T> {
    override fun createScaffold(context: C): Scaffold<T> {
        val result = when (spec) {
            is DirectorySpec<C> -> DirectoryScaffolder(spec).createScaffold(context)
            is FileSpec<C> -> FileScaffolder(spec).createScaffold(context)
        }
        return result as Scaffold<T>
    }
}

class DirectoryScaffolder<in C>(internal val spec: DirectorySpec<C>) : Scaffolder<C, Directory> {
    override fun createScaffold(context: C): Scaffold<Directory> {
        val builder = DirectoryShellBuilder<C>(context)
        builder.apply(spec.body)
        return builder.shell
    }
}

class FileScaffolder<in C>(internal val spec: FileSpec<C>) : Scaffolder<C, File> {
    override fun createScaffold(context: C): Scaffold<File> {
        val builder = FileShellBuilder<C>(context)
        builder.apply(spec.body)
        return builder.shell
    }
}
