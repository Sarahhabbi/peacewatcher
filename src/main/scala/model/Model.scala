package model

class Model {
  case class PeaceWatcherReport(id: Int, location: (Double, Double), citizens: List[Citizen], wordsHeard: List[String]) {
    def triggerAlert: Option[(Int, (Double, Double), String)] = {
      val agitatedCitizen = citizens.find(_.peaceScore < 0)
      agitatedCitizen match {
        case Some(c) => Some((id, location, c.name))
        case None => None
      }
    }
  }

  // EXEMPLE
  case class Citizen(name: String, peaceScore: Double)

  val report1 = PeaceWatcherReport(1, (45.50, -73.58), List(Citizen("John Doe", 0.8), Citizen("Jane Doe", -0.2)), List("loud argument", "angry tone"))
  val report2 = PeaceWatcherReport(2, (37.77, -122.42), List(Citizen("Jane Smith", 0.6), Citizen("John Smith", 0.9)), List("happy laughter", "calm tone"))
}
