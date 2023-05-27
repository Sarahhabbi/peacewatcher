import org.apache.spark.sql.{DataFrame, SparkSession}

object Visualisation extends App {

  def readBlobJsonToDataFrame(storageAccountName: String, storageAccountKey: String, containerName: String, blobPrefix: String): DataFrame = {

    val sparkMaster = "local[*]" // Mode d'exécution local avec utilisation maximale des cœurs disponibles
    val spark = SparkSession.builder()
      .appName("Lecture des données depuis Azure Blob Storage")
      .master(sparkMaster)
      .getOrCreate()
    spark.conf.set("fs.azure.account.auth.type.peacestate.blob.core.windows.net", "SharedKey")
    spark.conf.set("fs.azure.account.key.peacestate.blob.core.windows.net", storageAccountKey)

    val blobName = "wasbs://peacestate@peacestate.blob.core.windows.net/topics/alerts/year=2023/"
    val df:DataFrame = spark.read.format("json").load(blobName)
    df
  }

  val storageAccountName = "peacestate"
  val storageAccountKey = "8FLbdOXTCFgFqdd45L8xUEn5d1XHTMwuDkxu2lqCL9cJdSHVhHJuYKJjBFBZmztLhMIdZL9t6sze+AStSz79kw=="
  val containerName = "peacestate"
  val blobName = "topics/alerts/year=2023/"

  val df = readBlobJsonToDataFrame(storageAccountName, storageAccountKey, containerName, blobName)

  df.show()
}
