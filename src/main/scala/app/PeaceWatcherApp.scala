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
      val report = Report.creationReport()
      val alerts: List[Alert] = Report.analyzeReport(report)
      alerts match {
        case Nil => println("No alert to write.")
        case _ => alertProducer.writeAlerts(alerts)
      }
      println("Writing reports")
      println(alerts)
      reportProducer.writeReport(report)
    }
  }, 0, 10, TimeUnit.SECONDS)
}
