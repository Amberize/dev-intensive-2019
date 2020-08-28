package ru.skillbranch.devintensive.extensions

fun String.truncate(size: Int = 16): String {
    val str = this.trimEnd()

    if (str.length <= size) {
        return str
    }

    return str.substring(0, size).trimEnd() + "..."
}

fun String.stripHtml(): String {
    var stripped = this.replace(Regex("<.*?>"), "")

    stripped = stripped.replace(Regex("( )+"), " ")

    return stripped
}