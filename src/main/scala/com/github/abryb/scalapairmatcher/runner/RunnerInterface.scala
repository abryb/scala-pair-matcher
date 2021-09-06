package com.github.abryb.scalapairmatcher.runner

import com.github.abryb.scalapairmatcher.input.InputIterator
import com.github.abryb.scalapairmatcher.matcher.MatcherInterface
import com.github.abryb.scalapairmatcher.output.OutputWriter

trait RunnerInterface {
  def run(matcher: MatcherInterface, inputIterator: InputIterator, outputWriter: OutputWriter): Unit
}
