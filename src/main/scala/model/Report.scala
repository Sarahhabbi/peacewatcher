package model
import java.util.concurrent.atomic.AtomicInteger
import scala.util.Random
import scala.io.Source
import java.io.{File, PrintWriter}
import java.time.LocalDate
import java.time.format.DateTimeFormatter
case class Report(
                       id : Int,
                       longitude : Double,
                       latitude : Double,
                       nameCitizen : String,
                       peaceScore : Int,
                       wordHeard : String
                       ) {
}

object Report {
  val counterFile = new File("src/main/scala/data/report_counter.txt")
  val counter = readCounter()

  val words = Source.fromFile("src/main/scala/data/words.txt", "ISO-8859-1").getLines().toList
  val citizenList = Source.fromFile("src/main/scala/data/names.txt", "ISO-8859-1").getLines().toList

  def creationReport(): Report = {
    val id = counter.incrementAndGet()
    val longitude = 48 + Random.nextDouble()
    val latitude = 2 + Random.nextDouble()
    val nameCitizen = Random.shuffle(citizenList).head
    val peaceScore = Random.nextInt(101)
    val wordHeard = Random.shuffle(words).head
    val report = Report(id, longitude, latitude, nameCitizen, peaceScore, wordHeard)
    writeCounter()
    report
  }

  def analyzeReport(report: Report): Option[Alert] = {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val currentDate = LocalDate.now()
    val dateString = currentDate.format(dateFormatter)
    report.peaceScore match{
      case x if x < 50 => Some(Alert(report.latitude, report.longitude, report.nameCitizen, report.peaceScore, report.wordHeard, dateString))
      case _ => None
    }
  }

  def readCounter(): AtomicInteger = {
    if (counterFile.exists()) {
      val content = Source.fromFile(counterFile).mkString.trim
      new AtomicInteger(content.toInt)
    } else {
      new AtomicInteger(0)
    }
  }

  def writeCounter(): Unit = {
    val writer = new PrintWriter(counterFile)
    writer.println(counter.get())
    writer.close()
  }
}
