package model

import java.io.File
import scala.io.Source

final case class ReadResult[A](lines: Iterator[A], nbInvalidLine: Int)

object CSV {
  def read[A](fileName: String, parseLine: Array[String] => Option[A], regex: String = ","): ReadResult[A] = {
    val line = Source.fromFile(new File(fileName))
      .getLines()
      .drop(1)
      .map(line => line.split(";")
        .map(_.trim))
      .map(parseLine)

    val (parsedLine, invalidLine) = line.partition(line => line.isDefined)
    ReadResult(parsedLine.flatten, invalidLine.size)
  }
}