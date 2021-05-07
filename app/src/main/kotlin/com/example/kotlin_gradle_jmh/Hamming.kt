package com.example.kotlin_gradle_jmh

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
}
