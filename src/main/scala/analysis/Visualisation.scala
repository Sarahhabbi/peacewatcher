import org.apache.spark.sql.{DataFrame, SparkSession}

object Visualisation extends App {

  def readBlobJsonToDataFrame(): DataFrame = {
    val storageAccountKey = "8FLbdOXTCFgFqdd45L8xUEn5d1XHTMwuDkxu2lqCL9cJdSHVhHJuYKJjBFBZmztLhMIdZL9t6sze+AStSz79kw=="
    val sparkMaster = "local[*]"
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

  val df = readBlobJsonToDataFrame()

  df.show()
  println(df.count())
}
