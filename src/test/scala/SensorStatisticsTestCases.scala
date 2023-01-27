import com.SensorStatistics.SensorStatisticsRunnerClass
import com.sun.org.apache.regexp.internal.RETest.test

import java.io.File

class SensorStatisticsTestCases extends FunSuite {

  val sensorStatistic = new SensorStatisticsRunnerClass()
  var directory = "D:\\Sensor_Statistics\\Sensor_Statistics_files"
  var fileList: List[File] = null

  test(" Testcase for checking number of csv files processed") {
    val dir = new File(directory)
    if (dir.exists() && dir.isDirectory) {
      fileList = dir.listFiles.filter(_.isFile).toList
    } else
      List[File]()

  val numbOfProcessedFiles = sensorStatistic.numbOfProcessedFiles(directory)
  println(s" number of processed files should be ${fileList}.Length")
}
test("Testcase for checking number of Processed files failed") {
  val mockDataForFailedMeasurements = 2
  val filesize = sensorStatistic.numbOfProcessedFiles(directory)
  val numOfProcessedMeasurements = sensorStatistic.numOfProcessedMeasurements()
  println(s" number of processed files should be ${fileList}.Length")
}
test("Test Number of Processed Measurements"){
val mockSensorList = List("s1","s2","s1","s1","s1","s3","s2","s2","s3","s2","s1")
  val filesize = sensorStatistic.numbOfProcessedFiles(directory)
    val numberOfProcMeasurements = sensorStatistic.numOfFailedMeasurements()
    println(s"Number of Processed Measurements should be ${mockSensorList}.size")
}
      test("Test number of Processed Failed"){
          val  mockDataForFailedMeasurements = 2
          var filesize = sensorStatistic.numOfProcessedFiles(directory)
          val numberOfProcessedMeasurements = sensorStatistic.numOfProcessedMeasurements()
          val numberOfFailedMeasure = sensorStatistic.numOfFailedMeasurements()
        println("Number of Failed Measures")
      }
}