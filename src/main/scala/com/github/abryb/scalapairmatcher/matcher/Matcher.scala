package com.github.abryb.scalapairmatcher.matcher

import com.github.abryb.scalapairmatcher.exception.MatcherExceptions.InvalidNumberException
import scala.collection.mutable

/**
 *
 * Memory complexity: min O(1) - max O(n)
 * in worse case scenario when every number from input is different than other we have to save it.
 * In practice this is constant as input comes from finite set of possible values.
 *
 * Time complexity: O(n)
 *
 * @param pairSum target pair sum. E.g. if pairSum = 12 then Matcher will return pairs like (2,10), (5,7) etc
 * @param min     inclusive
 * @param max     inclusive
 */
final class Matcher(val pairSum: Int, val min: Int = Int.MinValue, val max: Int = Int.MaxValue) extends MatcherInterface {

  def getPairs(it: Iterator[Int]): Iterator[(Int, Int)] = {
    val counts: mutable.Map[Int, Int] = mutable.Map().withDefaultValue(0)

    it.filter(n => {
      this.validate(n)
      val nPair = this.pairSum - n
      if (counts(nPair) > 0) {
        counts(nPair) -= 1
        true
      } else {
        counts(n) += 1
        false
      }
    }).map(n => {
      val nPair = this.pairSum - n
      if (n < nPair) (n, nPair) else (nPair, n)
    })
  }

  private def validate(number: Int): Unit = {
    if (number > this.max) {
      throw new InvalidNumberException(s"Matcher was created to handle maximum integer $max. $number given.")
    }
    if (number < this.min) {
      throw new InvalidNumberException(s"Matcher was created to handle minimum integer $min. $number given.")
    }
  }
}
