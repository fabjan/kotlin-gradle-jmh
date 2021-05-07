package com.example.kotlin_gradle_jmh

import kotlin.test.Test
import kotlin.test.assertSame

class HammingTest {
    @Test
    fun testHammingCanLoop() {
        assertSame(3, Hamming.computeLoop("foo", "bar"))
    }
    @Test
    fun testHammingCanZip() {
        assertSame(3, Hamming.computeZip("foo", "bar"))
    }
    @Test
    fun testHammingCanCount() {
        assertSame(1, Hamming.computeLoop("foo", "roo"))
    }
}
