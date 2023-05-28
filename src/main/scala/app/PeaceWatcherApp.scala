package app
import kafka.{AlertProducer, ReportProducer}
import model.{Alert, Report}

import java.util.concurrent.{Executors, TimeUnit}

object PeaceWatcherApp extends App {
  val reportProducer = new ReportProducer()
  val alertProducer = new AlertProducer()

  val scheduler = Executors.newScheduledThreadPool(1)
  scheduler.scheduleAtFixedRate(new Runnable {
    def run(): Unit = {
      println("-----------------")
      val report = Report.creationReport()
      val alert: Option[Alert] = Report.analyzeReport(report)
      println(s"Writing alerts if needed: ${alert}")
      alert match {
        case None => println("No alert to write.")
        case _ => alertProducer.writeAlert(alert)
      }
      println(">>> Alerts written!")
      println(s"Writing report: ${report.id}")
      reportProducer.writeReport(report)
      println(">>> Report written!")
      println("-----------------")
    }
  }, 0, 10, TimeUnit.SECONDS)

  scheduler.awaitTermination(5, TimeUnit.HOURS)
  scheduler.shutdown()

  reportProducer.producer.close()
}
