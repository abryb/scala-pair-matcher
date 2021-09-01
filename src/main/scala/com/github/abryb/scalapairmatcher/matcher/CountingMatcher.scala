package com.github.abryb.scalapairmatcher.matcher

final class CountingMatcher(decorated: MatcherInterface) extends CountingMatcherInterface {
  protected var _processedNumbers = 0
  protected var _foundPairs = 0

  def processedNumbers: Int = _processedNumbers

  def foundPairs: Int = _foundPairs

  override def getPairs(it: Iterator[Int]): Iterator[(Int, Int)] = {
    val it2 = it.map(x => {
      _processedNumbers += 1
      x
    })
    decorated.getPairs(it2).map(x => {
      _foundPairs += 1
      x
    })
  }
}

object CountingMatcher {
  def apply(decorated: MatcherInterface): CountingMatcherInterface = {
    decorated match {
      case interface: CountingMatcherInterface =>interface
      case _ => new CountingMatcher(decorated)
    }
  }
}