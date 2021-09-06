package com.github.abryb.scalapairmatcher.matcher

trait MatcherInterface {
  def getPairs(it: Iterator[Int]): Iterator[(Int, Int)]
}
