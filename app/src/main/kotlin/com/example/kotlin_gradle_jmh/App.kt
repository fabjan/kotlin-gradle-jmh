package com.example.kotlin_gradle_jmh

import kotlin.random.Random

class App {
    fun diff(a: String, b: String): Int {
        return if (Random.nextBoolean()) {
            print("🕴 calculating...")
            Hamming.computeZip(a, b)
        } else {
            print("💃 calculating...")
            Hamming.computeLoop(a, b)
        }
    }
}

fun main(args: Array<String>) {
    require(args.size == 2) { "I need two strings to work with" }

    val a = args[0]
    val b = args[1]
    val d = App().diff(a, b)

    println("distance: $d")
}
