package com.github.abryb.scalapairmatcher.output

import org.scalatest.FunSuite
import java.nio.charset.StandardCharsets
import java.nio.file.Files

class CsvFileOutputWriterSuite extends FunSuite {
  test("Writes to csv") {

    val temp = Files.createTempFile("CsvFileOutputWriter_output", ".csv")

    val writer = new CsvFileOutputWriter(temp.toString)

    writer.write(Seq((0, 2), (1, 1)).iterator)

    val fileContent = Files.readString(temp, StandardCharsets.US_ASCII);

    assert(fileContent == "(0,2)\n(1,1)\n")
  }
}
