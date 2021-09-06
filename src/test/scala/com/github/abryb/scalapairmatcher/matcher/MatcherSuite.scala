package com.github.abryb.scalapairmatcher.matcher

import com.github.abryb.scalapairmatcher.exception.MatcherExceptions
import org.scalatest.FunSuite

class MatcherSuite extends FunSuite {
  test("Can match 2 elements") {
    val matcher = new Matcher(pairSum = 1)
    val numbers = Seq(0, 1)
    val result = matcher.getPairs(numbers.iterator)
    assert(result.size === 1)
  }

  test("Returns tuples with smaller element first") {
    val matcher = new Matcher(pairSum = 1)
    val numbers = Seq(1, 0)
    val result = matcher.getPairs(numbers.iterator)
    assert(result.toList(0) === (0, 1))
  }

  test("Returns empty iterator if empty iterator given") {
    val matcher = new Matcher(pairSum = 1)
    val numbers = Seq()
    val result = matcher.getPairs(numbers.iterator)
    assert(result.size === 0)
  }

  test("Matcher throws exception if number greater than max given") {
    val matcher = new Matcher(pairSum = 3, max = 3)
    val numbers = Seq(4)
    assertThrows[MatcherExceptions.InvalidNumberException] {
      matcher.getPairs(numbers.iterator).toList
    }
  }

  test("Matcher throws exception if number less than min given") {
    val matcher = new Matcher(pairSum = 3, min = 0)
    val numbers = Seq(-1)
    matcher.getPairs(numbers.iterator)
    assertThrows[MatcherExceptions.InvalidNumberException] {
      matcher.getPairs(numbers.iterator).toList
    }
  }

  test("Can handle comprehensive input") {
    val matcher = new Matcher(pairSum = 3, min = -7, max = 10)
    val numbers = Seq(0, 2, 1, 3, 3, 1, 0, 10, -7)
    val result = matcher.getPairs(numbers.iterator)
    assert(result.toList == List((1, 2), (0, 3), (0, 3), (-7, 10)))
  }
}