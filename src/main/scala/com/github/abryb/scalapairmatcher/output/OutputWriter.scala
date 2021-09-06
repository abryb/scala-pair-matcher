package com.github.abryb.scalapairmatcher.output

trait OutputWriter {
  def write(it: Iterator[(Int, Int)]): Unit
}

