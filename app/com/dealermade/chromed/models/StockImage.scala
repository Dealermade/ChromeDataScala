package com.dealermade.chromed.models

case class StockImage(url: String,
                      filename: String,
                      width: Option[Int],
                      height: Option[Int])

object StockImage {
  def apply(elem: xml.Node): StockImage =
    StockImage(
      elem \@ "url",
      elem \@ "filename",
      elem.attribute("width")
        .map(_.text.toInt),
      elem.attribute("height")
        .map(_.text.toInt))
}