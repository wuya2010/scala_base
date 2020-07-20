package com.elianda.test4

import java.util.Collections.synchronizedMap

import scala.collection.JavaConversions._
import scala.collection.mutable

/**
  * @author kylinWang
  * @data 2018/12/25 15:07
  *
  */


class LRUCache[K, V](maxEntries: Int)
  extends java.util.LinkedHashMap[K, V](200, .75f, true) {

  override def removeEldestEntry(eldest: java.util.Map.Entry[K, V]): Boolean
  = size > maxEntries

}

object LRUCache {
  def apply[K, V](maxEntries: Int): mutable.Map[K, V]
  = synchronizedMap(new LRUCache[K, V](maxEntries))
}
