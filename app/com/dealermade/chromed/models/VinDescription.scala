package com.dealermade.chromed.models

case class VinDescription(vin: String,
                          modelYear: Int,
                          division: String,
                          modelName: String)

object VinDescription {
  def apply(elem: xml.Node): VinDescription =
    VinDescription(
      elem \@ "vin",
      (elem \@ "modelYear").toInt,
      elem \@ "division",
      elem \@ "modelName")
}