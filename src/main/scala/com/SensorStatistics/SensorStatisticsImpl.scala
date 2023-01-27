package com.SensorStatistics

case class SensorMeasurement(sensorid: String, humidity: String)

  object SensorStatistics {

    def main(args: Array[String]): Unit = {

      var directory = "D:\\Sensor_Statistics\\Sensor_Statistics_files"

      val sensorSpark = new SensorStatisticsRunnerClass()
      val numberOfProcessedFile = sensorSpark.numOfProcessedFiles(directory)
      println(s"Number of processed Files:" + numberOfProcessedFile)
      val numberOfProcessedMeasurements = sensorSpark.numOfProcessedMeasurements()
      println(s"Number of processed Measurements:" + numberOfProcessedMeasurements)
      val numberOfFailedMeasurements = sensorSpark.numOfFailedMeasurements()
      println(s"Number of processed Measurements:" + numberOfFailedMeasurements)

    }

  }


