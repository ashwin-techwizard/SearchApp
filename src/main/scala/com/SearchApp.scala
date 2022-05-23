package com

import java.io.File
import java.util.Scanner
import scala.collection.mutable
import scala.collection.mutable.Set
import scala.io.Source
import scala.util.control.Breaks.{break, breakable}

object SearchApp {

  def getListOfFiles(dir: String): List[File] = {
    val file = new File(dir)
    file.listFiles.filter(_.isFile).toList
  }

  def indexer(path: String): Map[String, mutable.Set[String]] = {
    val searchMap = mutable.Map[String, Set[String]]()
    val fileList = getListOfFiles(path)
    fileList.foreach { file =>
      for (line <- Source.fromFile(file.getPath).getLines) {
        line.split("\\s").map(_.toLowerCase()).foreach { word =>
          if (searchMap.contains(word)) {
            searchMap(word) = searchMap(word) += file.getName
          } else {
            searchMap += (word -> mutable.Set(file.getName))
          }
        }
      }
    }
    println(s"${fileList.size} files indexed")
    searchMap.toMap
  }

  def scorer(parts: Float, base: Float): Int = {
    ((parts / base) * 100).toInt
  }

  def search(text: String, searchMap: Map[String, Set[String]]): List[(String, Int)] = {
    val wordList = text.split("\\s")
    val total = wordList.size.toFloat
    wordList.flatMap { word =>
      if (searchMap.contains(word.toLowerCase)) {
        searchMap(word.toLowerCase())
      } else None
    }.groupBy(identity)
      .map(t => (t._1, scorer(t._2.length.toFloat, total))).toList
      .sortBy(_._2).reverse.take(10)
  }


  def main(args: Array[String]): Unit = {
    if (args.length == 0) {
      throw new IllegalArgumentException("No directory path to index")
    }
    val index = indexer(args(0))
    val scanner = new Scanner(System.in)
    var text = ":quit"
    breakable {
      while (true) {
        println("search > ")
        text = scanner.nextLine()
        if (text.equals(":quit")) {
          break()
        }
        val hits = search(text, index)
        if (hits.isEmpty) {
          println("no matches found")
        } else
          hits.foreach(results => println(s"${results._1} : ${results._2}%"))
      }
    }
    scanner.close()
  }
}
