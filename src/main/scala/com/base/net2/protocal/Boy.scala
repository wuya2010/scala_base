package com.base.net2.protocal

sealed abstract class BoyGirl

case class Boy(age: Int, msg: String) extends BoyGirl

case class Girl(age: Int, msg: String) extends BoyGirl
