package model

import java.util.concurrent.atomic.AtomicInteger
import scala.util.Random

val counter = new AtomicInteger(0)
case class Report(
                       id : Int,
                       longitude : Double,
                       latitude : Double,
                       nameCitizen : List[Citizen],
                       wordHeard : List[String]
                       )

object Report{
  def creationReport(): Option[Report] = {
    val id = counter.incrementAndGet()
    val longitude = 48 + Random.nextDouble()
    val latitude = 2 + Random.nextDouble()
    val nameCitizen = ???
    val wordHeard = ???
    Some(Report(id, longitude, latitude, nameCitizen = ???, wordHeard = ???))
  }
}