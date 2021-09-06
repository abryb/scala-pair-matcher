package com.github.abryb.scalapairmatcher.matcher

trait CountingMatcherInterface extends MatcherInterface {

  /**
   * @return current count of processed numbers from input
   */
  def processedNumbers: Int

  /**
   * @return current count of found pairs
   */
  def foundPairs: Int
}
