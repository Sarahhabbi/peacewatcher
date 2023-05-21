package app
import kafka.{AlertProducer, ReportConsumer, ReportProducer}
import model.{Alert, Report}

import java.util.concurrent.{Executors, TimeUnit}

object PeaceWatcherApp extends App {
  val reportProducer = new ReportProducer()
  val alertProducer = new AlertProducer()

  val scheduler = Executors.newScheduledThreadPool(1)
  scheduler.scheduleAtFixedRate(new Runnable {
    def run(): Unit = {
      val report = Report.creationReport()
      val alerts: List[Alert] = Report.analyzeReport(report)
      println("Writing alerts and reports")
      alertProducer.writeAlerts(alerts)
      reportProducer.writeReport(report)
    }
  }, 0, 10, TimeUnit.SECONDS)

  val reportConsumer = new ReportConsumer()
  val consumerThread = new Thread(() => reportConsumer.consumeReports())
  consumerThread.start()

  reportProducer.producer.close()
}
