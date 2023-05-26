package analysis

import com.microsoft.azure.storage.CloudStorageAccount
import org.apache.spark.sql.{DataFrame, SparkSession}



class visualisation {

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

  val storageAccountName = "peacestate"
  val storageAccountKey = "8FLbdOXTCFgFqdd45L8xUEn5d1XHTMwuDkxu2lqCL9cJdSHVhHJuYKJjBFBZmztLhMIdZL9t6sze+AStSz79kw=="
  val containerName = "peacestate"
  val blobName = "topics/alerts/year=2023/alerts+0+0000002572.json"

  val df = readBlobJsonToDataFrame(storageAccountName, storageAccountKey, containerName, blobName)

  df.show()
}






