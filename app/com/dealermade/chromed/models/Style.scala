package com.dealermade.chromed.models

case class Style(id: Int,
                 name: String,
                 trim: Option[String],
                 nameWithoutTrim: Option[String],
                 bodyTypes: Seq[BodyType],
                 stockImages: Seq[StockImage])

object Style {
  def apply(elem: xml.Node): Style =
    Style(
      (elem \@ "id").toInt,
      elem \@ "name",
      elem.attribute("trim")
        .map(_.text),
      elem.attribute("nameWoTrim") match {
        case Some(attr :: _) => Some(attr.text)
        case _ => None
      },
      (elem \ "bodyType")
        .map(e => BodyType(e.asInstanceOf[xml.Elem])),
      (elem \ "stockImage")
        .map(e => StockImage(e.asInstanceOf[xml.Elem]))
    )
}
