package com.dealermade.chromed.models

case class CategoryAssociation(id: Int,
                               removed: Option[Boolean])

object CategoryAssociation {
  def apply(elem: xml.Node): CategoryAssociation =
    CategoryAssociation(
      (elem \@ "id").toInt,
      elem.attribute("removed")
        .map(_.head.text.toBoolean))
}
