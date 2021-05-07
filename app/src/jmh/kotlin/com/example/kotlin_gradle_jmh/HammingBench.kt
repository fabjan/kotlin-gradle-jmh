package com.example.kotlin_gradle_jmh

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit.SECONDS

@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 5, time = 2, timeUnit = SECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = SECONDS)
@Fork(2)
@Threads(4)
@State(Scope.Benchmark) // we need a state to use Params
open class HammingBench {

    @Param("A", "B")
    var left = ""

    @Param("A", "C")
    var right = ""

    @Param(
        "counterexcommunication",
        "dacryocystosyringotomy"
    )
    var leftLong = ""

    @Param(
        "dacryocystoblennorrhea",
        "deanthropomorphization"
    )
    var rightLong = ""

    @Benchmark
    fun testLoop(): Int {
        return Hamming.computeLoop(left, right) + Hamming.computeLoop(leftLong, rightLong)
    }

    @Benchmark
    fun testZip(): Int {
        return Hamming.computeZip(left, right) + Hamming.computeZip(leftLong, rightLong)
    }

    @Benchmark
    fun testZipIter(): Int {
        return Hamming.computeZipIter(left, right) + Hamming.computeZipIter(leftLong, rightLong)
    }

    @Benchmark
    fun testZipIterAnonGeneric(): Int {
        return Hamming.computeZipIterAnonGeneric(left, right) + Hamming.computeZipIterAnonGeneric(leftLong, rightLong)
    }
}
