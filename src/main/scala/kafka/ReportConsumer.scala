package kafka

import model.Report
import java.util.Properties
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer
import org.json4s.native.Serialization
import org.json4s.{Formats, NoTypeHints}
import org.json4s.native.Serialization.read

import scala.collection.JavaConverters._

class ReportConsumer {
  val props = new Properties()
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
  props.put(ConsumerConfig.GROUP_ID_CONFIG, "report-consumer-group")
  props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

  val consumer = new KafkaConsumer[String, String](props)
  consumer.subscribe(List("reports").asJava)
  implicit val formats: AnyRef with Formats = Serialization.formats(NoTypeHints)

  def consumeReports(): Unit = {
    while (true) {
      val records = consumer.poll(java.time.Duration.ofSeconds(1))
      for (record <- records.asScala) {
        val jsonString = record.value()
        val report = read[Report](jsonString)
        println(report)
      }
    }
  }
}
