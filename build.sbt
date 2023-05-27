name := "peacewatcher"

version := "0.1"

scalaVersion := "2.13.10"
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "3.4.0"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "2.0.5"
libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-native" % "3.6.6",
  "org.json4s" %% "json4s-ast" % "3.6.6",
  "org.json4s" %% "json4s-core" % "3.6.6",
  "org.json4s" %% "json4s-jackson" % "3.6.6",
  "org.json4s" %% "json4s-scalap" % "3.6.6"
)
libraryDependencies += "com.azure" % "azure-storage-blob" % "12.14.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.2.0"
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.2.0"
libraryDependencies += "com.microsoft.azure" % "azure-storage" % "7.0.0"
libraryDependencies += "org.apache.hadoop" % "hadoop-azure" % "3.3.1"

