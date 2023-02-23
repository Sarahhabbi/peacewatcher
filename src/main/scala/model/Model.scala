package model
import java.util.concurrent.atomic.AtomicInteger
import scala.util.Random

object Model{
  def main(args: Array[String]):Unit = {
    val counter = new AtomicInteger(0)
    val citizenList: List[Citizen] = CSV.read("src/main/scala/data/liste_des_prenoms.csv", Citizen.parseCitizen).lines.toList

    def creationReport(): Option[Report] = {
      val id = counter.incrementAndGet()
      val longitude = 48 + Random.nextDouble()
      val latitude = 2 + Random.nextDouble()
      val nameCitizen = Random.shuffle(citizenList).head
      val wordHeard = "Hello"
      Some(Report(id, longitude, latitude, nameCitizen, wordHeard))
    }

    println(citizenList)
  }
}
