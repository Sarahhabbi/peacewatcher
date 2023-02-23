package model

import java.util.concurrent.atomic.AtomicInteger
import scala.util.Random

val counter = new AtomicInteger(0)
val citizenList : List[Citizen] = CSV.read("src/main/scala/data/liste_des_prenoms.csv", Citizen.parseCitizen).lines.toList
case class Report(
                       id : Int,
                       longitude : Double,
                       latitude : Double,
                       nameCitizen : Citizen,
                       wordHeard : List[String]
                       )

object Report{
  def creationReport(): Option[Report] = {
    val id = counter.incrementAndGet()
    val longitude = 48 + Random.nextDouble()
    val latitude = 2 + Random.nextDouble()
    val nameCitizen = citizenList(Random.nextInt(citizenList.length))
    val wordHeard = ???
    Some(Report(id, longitude, latitude, nameCitizen, wordHeard = ???))
  }
}