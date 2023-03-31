package model
import java.util.concurrent.atomic.AtomicInteger
import scala.util.Random
case class Report(
                       id : Int,
                       longitude : Double,
                       latitude : Double,
                       nameCitizen : Citizen,
                       wordHeard : String
                       )

object Report{
  val counter = new AtomicInteger(0)
  val citizenList: List[Citizen] = CSV.read("src/main/scala/data/liste_des_prenoms.csv", Citizen.parseCitizen).lines.toList
  def creationReport(): Report = {
    val id = counter.incrementAndGet()
    val longitude = 48 + Random.nextDouble()
    val latitude = 2 + Random.nextDouble()
    val nameCitizen = Random.shuffle(citizenList).head
    val wordHeard = "Hello"
    Report(id, longitude, latitude, nameCitizen, wordHeard)
  }
}