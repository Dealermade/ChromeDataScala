package com.dealermade.chromed.models

case class Vehicle(modelYear: Int,
                   division: String,
                   model: String,
                   styles: Seq[Style],
                   engines: Seq[Engine],
                   standard: Seq[Standard]) {

  def stockImageUrl: Option[String] = styles
    .headOption
    .flatMap(_.stockImages.headOption.map(_.url))
}

object Vehicle {

  def apply(elem: xml.Node): Vehicle = {
    val styles = (elem \ "style")
      .map(Style(_))

    val vinDescription = (elem \ "vinDescription")
      .headOption
      .map(VinDescription(_))

    vinDescription match {
      case Some(v) =>
        Vehicle(
          v.modelYear,
          v.division,
          v.modelName,
          styles,
          (elem \ "engine").map(Engine(_)),
          (elem \ "standard").map(Standard(_, styles.map(s => (s.id, s)).toMap)))
      case None =>
        Vehicle(
          (elem \@ "modelYear").toInt,
          elem \@ "bestMakeName",
          elem \@ "bestModelName",
          styles,
          (elem \ "engine").map(Engine(_)),
          (elem \ "standard").map(Standard(_, styles.map(s => (s.id, s)).toMap)))
    }
  }
}
