package com.dealermade.chromed.models

case class BodyType(id: Int,
                    name: String,
                    primary: Option[Boolean])

object BodyType {
  def apply(elem: xml.Node): BodyType = {
    BodyType(
      (elem \@ "id").toInt,
      elem.text,
      elem.attribute("primary")
        .map(_.head.text.toBoolean))
  }
}
