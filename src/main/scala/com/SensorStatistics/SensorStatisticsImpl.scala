package com.SensorStatistics

case class SensorMeasurement(sensorid: String, humidity: String)

  object SensorStatistics {

    def main(args: Array[String]): Unit = {

      //Reading the csv files from directory
      var directory = "D:\\Sensor_Statistics\\Sensor_Statistics_files"

      val sensorSpark = new SensorStatisticsRunnerClass()
      val numberOfProcessedFile = sensorSpark.numOfProcessedFiles(directory)
      // printing number of processed files
      println(s"Number of processed Files:" + numberOfProcessedFile)
      val numberOfProcessedMeasurements = sensorSpark.numOfProcessedMeasurements()
      //printing number of processed Measurements
      println(s"Number of processed Measurements:" + numberOfProcessedMeasurements)
      val numberOfFailedMeasurements = sensorSpark.numOfFailedMeasurements()
      //printing number of processed measurements
      println(s"Number of processed Measurements:" + numberOfFailedMeasurements)

    }

  }


