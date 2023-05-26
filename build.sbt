name := "peacewatcher"

version := "0.1"

scalaVersion := "2.12.16"
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "3.4.0"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "2.0.5"
libraryDependencies += "org.json4s" %% "json4s-native" % "4.0.6"


libraryDependencies += "com.azure" % "azure-storage-blob" % "12.14.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.2.0"
libraryDependencies += "com.microsoft.azure" % "azure-storage" % "7.0.0"
