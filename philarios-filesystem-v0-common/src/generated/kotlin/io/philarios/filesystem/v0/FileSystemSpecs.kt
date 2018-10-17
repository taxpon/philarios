package io.philarios.filesystem.v0

import io.philarios.core.v0.Scaffold
import io.philarios.core.v0.Spec

open class DirectorySpec<in C>(internal val body: DirectoryBuilder<C>.() -> Unit) : Spec<C, Directory> {
    override fun connect(context: C): Scaffold<Directory> {
        val builder = DirectoryBuilder<C>(context)
        builder.apply(body)
        return builder.shell
    }
}

open class FileSpec<in C>(internal val body: FileBuilder<C>.() -> Unit) : Spec<C, File> {
    override fun connect(context: C): Scaffold<File> {
        val builder = FileBuilder<C>(context)
        builder.apply(body)
        return builder.shell
    }
}