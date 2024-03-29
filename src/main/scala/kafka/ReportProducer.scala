package kafka
import model.Report

import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.json4s.{Formats, NoTypeHints}
import org.json4s.native.Serialization
import org.json4s.native.Serialization.write

class ReportProducer {
  val props = new Properties()
  props.load(new java.io.FileInputStream("./report_producer_client.properties"))

  val producer = new KafkaProducer[String, String](props)
  implicit val formats: AnyRef with Formats = Serialization.formats(NoTypeHints)

  def writeReport(report:Report) : Unit ={
    val jsonString = write(report)

    val record = new ProducerRecord[String, String]("reports", jsonString)
    println("Sending data to topic reports")
    producer.send(record)
  }
}
