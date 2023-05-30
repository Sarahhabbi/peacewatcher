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

      alert match {
        case None => println("No alert to write.")
        case _ => {
          println(s"💢New alert detected !💢")
          alertProducer.writeAlert(alert)
        }
      }
      println(s"Writing report: ${report.id}")
      reportProducer.writeReport(report)
      println(">>> Report written!")
      println("-----------------")
    }
  }, 0, 10, TimeUnit.SECONDS)

  while (true) {
    Thread.sleep(1000)
  }
}
