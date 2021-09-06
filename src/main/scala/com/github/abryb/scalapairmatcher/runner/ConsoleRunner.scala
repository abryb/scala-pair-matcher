package com.github.abryb.scalapairmatcher.runner

import java.util

import com.github.abryb.scalapairmatcher.input.InputIterator
import com.github.abryb.scalapairmatcher.matcher.{CountingMatcher, CountingMatcherInterface, MatcherInterface}
import com.github.abryb.scalapairmatcher.output.OutputWriter

final class ConsoleRunner extends RunnerInterface {
  private[this] val startTime: Long = System.currentTimeMillis()
  private[this] var peakMemory: Long = currentMemoryUsageInMB()
  private[this] var matcher: CountingMatcherInterface = _
  private[this] val timer = new util.Timer()

  def run(matcher: MatcherInterface, inputIterator: InputIterator, outputWriter: OutputWriter): Unit = {
    this.matcher = CountingMatcher(matcher)

    // run asynchronous tasks
    timerSchedule(currentMemoryUsageInMB, 0L, 500L)
    timerSchedule(printState, 1000L, 2000L)

    // matching
    outputWriter.write(this.matcher.getPairs(inputIterator))

    // cleanup
    timer.cancel()

    // print summary
    printSummary()
  }

  private def timerSchedule(task: () => Unit, delay: Long, period: Long): Unit = {
    this.timer.schedule(
      new java.util.TimerTask {
        def run(): Unit = task()
      }
      , delay, period)
  }

  private def printSummary(): Unit = {
    println("Process ended. Summary: ")
    printState()
  }

  private def printState(): Unit = {
    val memoryUsage = currentMemoryUsageInMB();
    val time = (System.currentTimeMillis() - startTime) / 1e3
    val formatter = java.text.NumberFormat.getIntegerInstance
    val formattedProcessed = formatter.format(matcher.processedNumbers)
    val formattedFound = formatter.format(matcher.foundPairs)
    println(
      f"""Processed $formattedProcessed numbers and found $formattedFound pairs in $time s.
         |Memory usage: peak $peakMemory MB, current $memoryUsage MB."""
        .stripMargin.replaceAll("\n", " ")
    )
  }

  private def currentMemoryUsageInMB(): Long = {
    val memoryUsage = (Runtime.getRuntime.totalMemory - Runtime.getRuntime.freeMemory) / (1024 * 1024)
    if (memoryUsage > peakMemory) {
      peakMemory = memoryUsage
    }
    memoryUsage
  }
}
