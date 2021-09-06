package com.github.abryb.scalapairmatcher.input

import scala.io.Source

/**
 * @throws java.io.FileNotFoundException if file with filePath does not exists
 */
final class CsvFileInputIterator(filePath: String) extends InputIterator {
  private[this] val source = Source.fromFile(filePath)
  private[this] val it = source.getLines()
  private[this] var sourceOpened = true
  closeSourceIfIteratorIsEmpty()

  override def hasNext: Boolean = sourceOpened && it.hasNext

  override def next(): Int = {
    val next = it.next()
    closeSourceIfIteratorIsEmpty()
    next.toInt
  }

  def closeSourceIfIteratorIsEmpty(): Unit = {
    if (!it.hasNext) {
      source.close()
      sourceOpened = false
    }
  }
}
