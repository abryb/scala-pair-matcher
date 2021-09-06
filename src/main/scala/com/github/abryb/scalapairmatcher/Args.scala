package com.github.abryb.scalapairmatcher

import com.github.abryb.scalapairmatcher.exception.ArgsExceptions.InvalidArgsException

case class Args(inputFile: String, outputFile: String, pairOption: Int, minOption: Int, maxOption: Int) {
}

object Args {

  /**
   * @throws InvalidArgsException if args are invalid
   */
  def parseArgs(args: Array[String]): Args = {

    var inputFile: String = null
    var outputFile: String = null
    var pairOption = 0
    var minOption = Int.MinValue
    var maxOption = Int.MaxValue
    for (arg <- args) {
      if (arg.startsWith("--pair")) {
        pairOption = arg.substring(7).toInt
      } else if (arg.startsWith("--min")) {
        minOption = arg.substring(6).toInt
      } else if (arg.startsWith("--max")) {
        maxOption = arg.substring(6).toInt
      } else if (null == inputFile) {
        inputFile = arg
      } else if (null == outputFile) {
        outputFile = arg
      }
    }

    if (null == inputFile || null == outputFile) {
      throw new InvalidArgsException()
    }

    new Args(
      inputFile = inputFile,
      outputFile = outputFile,
      pairOption = pairOption,
      minOption = minOption,
      maxOption = maxOption
    )
  }
}