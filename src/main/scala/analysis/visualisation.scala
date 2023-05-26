package analysis

import com.microsoft.azure.storage.CloudStorageAccount
import org.apache.spark.sql.{DataFrame, SparkSession}



class Visualisation {

  def readBlobJsonToDataFrame(storageAccountName: String, storageAccountKey: String, containerName: String, blobName: String): DataFrame = {
    val storageConnectionString = s"DefaultEndpointsProtocol=https;AccountName=$storageAccountName;AccountKey=$storageAccountKey;EndpointSuffix=core.windows.net"
    val storageAccount = CloudStorageAccount.parse(storageConnectionString)
    val blobClient = storageAccount.createCloudBlobClient()

    val container = blobClient.getContainerReference(containerName)
    val blobReference = container.getBlockBlobReference(blobName)

    val inputStream = blobReference.openInputStream()

    val spark = SparkSession.builder()
      .appName("Lecture des données depuis Azure Blob Storage")
      .getOrCreate()

    val json = scala.io.Source.fromInputStream(inputStream).mkString
    val rdd = spark.sparkContext.parallelize(Seq(json))
    val df = spark.read.json(rdd)

    df
  }


  val storageAccountName = "peacewatcher"
  val storageAccountKey = "vvhaz7qpOLYy4E3SGKnj7STJmR2oId9jm6nkUSHgJYsNQ8I0WvGvld50Rir/EvUfJTLERVLesYdR+AStKXA5Rg=="
  val containerName = "peacewatcher"
  val blobName = "topics/alerts/year=2023/month=05/day=21/hour=20/alerts+1+0000000418.json"

  val df = readBlobJsonToDataFrame(storageAccountName, storageAccountKey, containerName, blobName)

  df.show()

}
