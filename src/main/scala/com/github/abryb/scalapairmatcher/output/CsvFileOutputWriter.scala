package com.github.abryb.scalapairmatcher.output

import java.io.{BufferedWriter, FileWriter}

class CsvFileOutputWriter(filePath: String) extends OutputWriter {
  override def write(it: Iterator[(Int, Int)]): Unit = {
    val writer = new BufferedWriter(new FileWriter(this.filePath))

    it.foreach(pair => {
      writer.write(pair.toString() + "\n")
    })

    writer.close()
  }
}
