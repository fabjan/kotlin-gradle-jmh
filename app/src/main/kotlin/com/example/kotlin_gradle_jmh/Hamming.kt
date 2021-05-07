package com.example.kotlin_gradle_jmh

import kotlin.math.min

object Hamming {
    fun computeLoop(a: String, b: String): Int {
        require(a.length == b.length) { "length must be the same" }
        var diff = 0
        for (i in a.indices) {
            if (a[i] != b[i]) {
                diff += 1
            }
        }
        return diff
    }

    fun computeZip(a: String, b: String): Int {
        require(a.length == b.length) { "length must be the same" }
        return a.zip(b).count { it.first != it.second }
    }

    fun computeZipIter(a: String, b: String): Int {
        require(a.length == b.length) { "length must be the same" }
        return ZipIter(a, b).count { it.first != it.second }
    }

    fun computeZipIterAnonGeneric(a: String, b: String): Int {
        require(a.length == b.length) { "length must be the same" }
        return a.listView().zipIter(b.listView()).count { it.first != it.second }
    }
}

class ZipIter(val a: String, val b: String) : Iterable<Pair<Char, Char>> {
    override fun iterator(): Iterator<Pair<Char, Char>> {
        return ZipIterator(a, b)
    }
}

class ZipIterator(val a: String, val b: String) : Iterator<Pair<Char, Char>> {
    private var i = 0
    private var end = min(a.length, b.length)

    override fun hasNext(): Boolean {
        return i < end
    }

    override fun next(): Pair<Char, Char> {
        val p = Pair(a[i], b[i])
        i++
        return p
    }
}

/**
 * Returns an iterable of pairs built from the characters of `this` and the [other] char lists with the same index.
 * The iterable has the length of the shortest char list.
 */
fun <T> List<T>.zipIter(other: List<T>) = object : Iterable<Pair<T, T>> {
    override operator fun iterator(): Iterator<Pair<T, T>> = object : Iterator<Pair<T, T>> {
        var i = 0
        val end = min(this@zipIter.lastIndex, other.lastIndex)
        override fun hasNext() = i <= end
        override fun next(): Pair<T, T> {
            val p = Pair(this@zipIter[i], other[i])
            i++
            return p
        }
    }
}

// Implement List<Char> for a string without copying it.
fun CharSequence.listView(): List<Char> = object : List<Char> {
    override val size: Int get() = this@listView.length
    override fun get(index: Int) = this@listView[index]

    // left as an exercise for the reader
    override fun contains(element: Char) = TODO("Not yet implemented")
    override fun containsAll(elements: Collection<Char>) = TODO("Not yet implemented")
    override fun indexOf(element: Char) = TODO("Not yet implemented")
    override fun isEmpty() = TODO("Not yet implemented")
    override fun iterator() = TODO("Not yet implemented")
    override fun lastIndexOf(element: Char) = TODO("Not yet implemented")
    override fun listIterator() = TODO("Not yet implemented")
    override fun listIterator(index: Int) = TODO("Not yet implemented")
    override fun subList(fromIndex: Int, toIndex: Int) = TODO("Not yet implemented")
}