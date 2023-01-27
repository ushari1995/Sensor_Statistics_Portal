package com.SensorStatistics

import java.io.File
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext
import scala.collection.immutable.ListMap
import scala.collection.mutable.ListBuffer

class SensorStatisticsRunnerClass extends SensorStats{

  var fileList: List[File]= null
  var mean = ""
  var humidityListBuffer= new ListBuffer[List[String]]
  var sensoridListBuffer = new ListBuffer[List[String]]
  var map: Map[String, ListBuffer[Int]]= Map()

  def numbOfProcessedFiles(dir: String): Int = {

      val directory = new File(directory)
      if(directory.exists && directory.isDirectory){
          fileList = directory.listFiles.filter(_.isFile).toList
      }else {
        List[File]
      }
          fileList.size
  }

      def numOfProcessedMeasurements(dir: String): Int = {
        var conf = new SparkConf().setAppName("Read CSV Files From Directory").setMaster("local[*]")
        var sc = new SparkContext(conf)
            for(file <- fileList){

                val text = sc.textFile(file.toString)
                val header =text.first().filter(row=>row != header)
                //val textResult = text.filter(row=>row != header)
                val sensors = text.map{
                    line =>
                        val col = line.split(",")
                SensorMeasurement(col(0),col(1))
                }
          val humidityList =
            for {
            line <- sensors
            values = line
            range = values.humidity
          } yield range

          humidityListBuffer += humidityList.collect().toList

            val sensoridsList =
              for {

                line <-sensors
                values = line
                range = values.sensorid
              }yield  range

              sensoridListBuffer += sensoridsList.collect().toList
            }
                val finalSensorIdList: List[String] = sensoridListBuffer.toList.flatten
                val finalHumidityList: List[String] = humidityListBuffer.toList.flatten
                  .map(e => if(e == "NaN") "0" else e).map(x => x.toInt)
                finalSensorIdList.length
            }

  def numOfFailedMeasurements(): Int = {

        val HumidityListwithNaNData :List[String]= humidityListBuffer.toList.flatten
        var count = 0
        for(id <- 0 to (humidityListBuffer.toList.flatten.length-1))

        {
            if(HumidityListwithNaNData(id).equals("NaN"))
            {
                count = count + 1
            }

        } return count
      }

  def MinMaxAvgHumidity(): Unit = {
    val finalSensorIDList = List[String]=sensoridListBuffer.toList.flatten
    val finalHumidityList = List[Int]=humidityListBuffer.toList.flatten
                                            .map(e => if(e=="NaN") "0" else e)
                                            .map(x => x.toInt)

    var count_1 = 0
    for(string <- finalSensorIDList){
          if(map.contains(string)){
              map += (str -> (map(str) += finalHumidityList(count_1)))
          }else {
              map +=( str -> ListBuffer(finalHumidityList(count_1)))
          }
          count_1 = count_1+1
    }
      println(" sensor-id"+"," + "min" + "," +"max" + "," + "avg :")
      for(senid <- map )
        {
          val remainder= senid._2.filterNot(p => p.equals(0))
          val sum = (senid._2.filterNot(p => p.equals(0)).sum)
          var avg= 0
          if(!remainder.isEmpty || !sum.equals(0))
            {
                avg = (sum/remainder.size)
            }else
            {
              avg=0
            }
            mean = if(senid._2.filterNot(prog=> prog.equals(0)).isEmpty) "NaN"
                        else
                    if(avg!=0)
                      avg.toString else "NaN"

            val min= if(senid._2.filterNot(prog=> prog.equals(0)).isEmpty)) "NaN"
                else
                      senid._2.filterNot(prog=> prog.equals(0)).min
            val max = if(senid._2.filterNot(prog=> prog.equals(0)).isEmpty) "NaN"
              else {
            senid._2.filterNot(prog=> prog.equals(0)).max

            println(senid._1 + "," + min+ "," + mean + "," + max)
            }
        }

    def sortsSensorsByHighestAvgerageHumidit(): Unit ={
        var sortMap: Map[String, Int]= Map()
        val finalSensorIdList: List[String] = sensoridListBuffer.toList.flatten
        for(sensid <- map){
          val remainder =sensid._2.filterNot(prog=> prog.equals(0))
          val sum = (sensid._2.filterNot(prog=> prog.equals(0)).sum)
          var average = 0
          if(!remainder.isEmpty || !sum.equals(0))
            {
                  average = (sum/remainder.size)
            }else
            {
              average = 0
            }

          mean = if(sensid._2.filterNot(prog => prog.equals(0)).isEmpty) "NaN"
          else
                  if(average!=0) average.toString
                  else {
                    "NaN"
                    val mean_1 = if (sensid._2 == List(0)) "NaN"
                    else
                      mean
                    if (mean_1 != "NaN") {
                      sortMap += (sensid._1 -> mean_1.toInt)
                    } else {
                      sortMap += (sensid._1 -> 0)
                    }
                  }

          print("sorts sensors by highest average humidity: ")
            for(x <- ListMap(sortMap.toSeq.sortWith(_._2 > _._2):_*))
              {
                if(sensid._2 == 0)
                  println(sensid._1 -> "NaN"+", ")
                else
                  println(sensid._1 -> sensid._2+", ")
              }
        }

    }
  }
}
