package com.github.abryb.scalapairmatcher.input

import org.scalatest.FunSuite

class CsvFileInputIteratorSuite extends FunSuite {
  test("Reads resources/input.csv file") {
    val reader = new CsvFileInputIterator(getClass.getResource("/input.csv").getPath)
    assert(reader.toList == List(0, 10, 1, 11, 2, 12))
  }
}
