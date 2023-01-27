package com.SensorStatistics


trait SensorStats {

  //declarations
  def numOfProcessedFiles(dir: String): Int

  def humidityList(): Unit

  def numOfProcessedMeasurements(dir: String): Int

  def numOfFailedMeasurements(): Int

  def MinMaxAvgHumidity(): Unit

  def sortsSensorsByHighestAvgHumidity(): Unit

}
