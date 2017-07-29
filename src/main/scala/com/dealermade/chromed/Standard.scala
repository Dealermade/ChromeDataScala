package com.dealermade.chromed

case class Standard(id: Int,
                    description: String,
                    categories: Seq[CategoryAssociation],
                    styles: Seq[Style],
                    installed: Option[InstallationCause])

object Standard {
  def apply(elem: xml.Node, stylesMap: Map[Int, Style]): Standard =
    Standard(
      (elem \ "id").text.toInt,
      (elem \ "description").text,
      (elem \ "categories")
        .map(CategoryAssociation(_)),
      (elem \ "styleId")
        .map(s => stylesMap(s.text.toInt)),
      (elem \ "installed")
        .headOption
        .map(InstallationCause(_)))
}