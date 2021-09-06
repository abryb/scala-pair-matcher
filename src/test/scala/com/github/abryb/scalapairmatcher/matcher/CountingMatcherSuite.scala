package com.github.abryb.scalapairmatcher.matcher

import org.scalatest.FunSuite
import org.scalamock.scalatest.MockFactory

import scala.collection.mutable

class CountingMatcherSuite extends FunSuite with MockFactory {

  class MatcherStub(result: Iterator[(Int, Int)]) extends MatcherInterface {
    override def getPairs(it: Iterator[Int]): Iterator[(Int, Int)] = {
      // fake input iterator processing
      while (it.hasNext) it.next()
      result
    }
  }

  test("Does count") {
    val input = mutable.Seq(0, 1, 0, 1).iterator
    val matcherStub = new MatcherStub(Seq((0, 1), (0, 1)).iterator)

    val matcher = new CountingMatcher(matcherStub)

    matcher.getPairs(input).toList

    assert(matcher.processedNumbers == 4)
    assert(matcher.foundPairs == 2)
  }
}
