package model
import scala.util.Try
case class Citizen(
                  name : String,
                  peaceScore : Int
                  )

object Citizen{
  def parseCitizen(line: Array[String]): Option[Citizen] = {
    (Try(line(0)).toOption, Try(line(1).toInt).toOption) match {
      case (Some(a), Some(b)) => Some(Citizen(a,b))
      case _ => None
    }
  }
}
