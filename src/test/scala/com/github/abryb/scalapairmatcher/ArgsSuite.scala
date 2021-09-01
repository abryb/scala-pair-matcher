package com.github.abryb.scalapairmatcher

import com.github.abryb.scalapairmatcher.exception.ArgsExceptions.InvalidArgsException
import org.scalatest.FunSuite

class ArgsSuite extends FunSuite {
  test("Parses valid args") {

    val args  = Args.parseArgs(Array("input.csv", "output.csv", "--pair=12", "--min=-30", "--max=20"))

    assert(args.inputFile == "input.csv")
    assert(args.outputFile == "output.csv")
    assert(args.pairOption == 12)
    assert(args.minOption == -30)
    assert(args.maxOption == 20)
  }

  test("Throws InvalidArgsException if no output file given") {
    assertThrows[InvalidArgsException] {
      val args  = Args.parseArgs(Array("input.csv", "--pair=12", "--min=-30", "--max=20"))
    }
  }

  test("Throws InvalidArgsException if no input and output file given") {
    assertThrows[InvalidArgsException] {
      val args  = Args.parseArgs(Array("--pair=12", "--min=-30", "--max=20"))
    }
  }
}
