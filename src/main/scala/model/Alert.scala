package model

import java.time.LocalDate

case class Alert(longitude: Double,
                 latitude: Double,
                 citizenName: String,
                 peaceScore: Int ,
                 currentDate: LocalDate)
