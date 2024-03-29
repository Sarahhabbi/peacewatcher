package kafka

import model.Alert

import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.json4s.{Formats, NoTypeHints}
import org.json4s.native.Serialization
import org.json4s.native.Serialization.write
class AlertProducer {
  val props = new Properties()
  props.load(new java.io.FileInputStream("./alert_producer_client.properties"))

  val producer = new KafkaProducer[String, String](props)
  implicit val formats: AnyRef with Formats = Serialization.formats(NoTypeHints)

  def writeAlert(alert: Option[Alert]): Unit = {
      val jsonString = write(alert)
      val record = new ProducerRecord[String, String]("alerts", jsonString)
      println("Sending data to alert topic")
      producer.send(record)
  }
}
