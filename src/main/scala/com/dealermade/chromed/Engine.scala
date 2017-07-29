package com.dealermade.chromed

case class Engine(engineType: String,
                  fuelType: String,
                  cylinders: Seq[Int])

object Engine {
  def apply(elem: xml.Node): Engine =
    Engine(
      (elem \ "engineType").text,
      (elem \ "fuelType").text,
      (elem \ "cylinders").map(e => e.text.toInt))
}
