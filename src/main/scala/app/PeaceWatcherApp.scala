package app
import kafka.{ReportConsumer, ReportProducer}
import model.Report

import java.util.concurrent.{Executors, TimeUnit}

object PeaceWatcherApp extends App {
  val reportProducer = new ReportProducer()

  val scheduler = Executors.newScheduledThreadPool(3)
  scheduler.scheduleAtFixedRate(new Runnable {
    def run() = {
      val report = Report.creationReport()
      reportProducer.writeReport(report)
    }
  }, 0, 10, TimeUnit.SECONDS)


  val reportConsumer = new ReportConsumer()
  val consumerThread = new Thread(() => reportConsumer.consumeReports())
  consumerThread.start()

  Thread.sleep(30000)
  scheduler.shutdown()
  reportProducer.producer.close()
}
