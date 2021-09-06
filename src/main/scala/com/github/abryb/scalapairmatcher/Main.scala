package com.github.abryb.scalapairmatcher


import com.github.abryb.scalapairmatcher.input.CsvFileInputIterator
import com.github.abryb.scalapairmatcher.matcher.Matcher
import com.github.abryb.scalapairmatcher.output.CsvFileOutputWriter
import com.github.abryb.scalapairmatcher.runner.ConsoleRunner
import com.github.abryb.scalapairmatcher.exception.ArgsExceptions
import com.github.abryb.scalapairmatcher.exception.MatcherExceptions

object Main {

  val usage: String =
    """Usage:
      |scala-pair-matcher <input.csv> <output.csv> [--pair=NUM] [--min=NUM] [--max=NUM]
      |   Arguments:
      |     input.csv     input file in csv format
      |     output.csv    output file in csv format
      |   Options:
      |     --pair=NUM    make pairs whose sum is NUM          (default 0)
      |     --min=NUM     validate if input numbers are >= NUM (default Int.MinValue)
      |     --max=NUM     validate if input numbers are <= NUM (default Int.MaxValue)
      |     --help        print help/usage
      |""".stripMargin


  def main(args: Array[String]) {

    try {
      if (args.contains("--help")) {
        println(usage)
        sys.exit(0)
      }
      val appArgs = Args.parseArgs(args)

      new ConsoleRunner().run(
        matcher = new Matcher(pairSum = appArgs.pairOption, min = appArgs.minOption, max = appArgs.maxOption),
        inputIterator = new CsvFileInputIterator(appArgs.inputFile),
        outputWriter = new CsvFileOutputWriter(appArgs.outputFile)
      )
    } catch {
      case e: ArgsExceptions.InvalidArgsException =>
        println(e.getMessage)
        println(usage)
        sys.exit(1)
      case e: java.io.FileNotFoundException =>
        println(e.getMessage)
        sys.exit(2)
      case e: MatcherExceptions.InvalidNumberException =>
        println(e.getMessage)
        sys.exit(3)
    }
  }
}
