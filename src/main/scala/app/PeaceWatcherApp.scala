package app
import java.util.concurrent.{Executors, TimeUnit}
import kafka.ReportProducer
import model.Report

object PeaceWatcherApp extends App {
  val reportProducer = new ReportProducer()

  val scheduler = Executors.newScheduledThreadPool(3)
  scheduler.scheduleAtFixedRate(new Runnable {
    def run() = {
      val report = Report.creationReport()
      reportProducer.writeReport(report)
    }
  }, 0, 5, TimeUnit.SECONDS)

  // Attendez 1 minute avant de terminer le programme
  Thread.sleep(30000)
  scheduler.shutdown()
  reportProducer.producer.close()
}
