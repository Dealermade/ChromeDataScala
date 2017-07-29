package com.dealermade.chromed

case class Vehicle(model: String,
                   modelYear: Int,
                   division: String,
                   styles: Seq[Style],
                   engines: AnyRef,
                   standard: AnyRef,
                   stockImageUrl: String)

object Vehicle {

  def apply(vehicleInfo: xml.Elem): Vehicle = {
    val vin = (vehicleInfo \ "vinDescription").head

    val modelYear = (vin \ "@modelYear").head.text.toInt
    val division = (vin \ "@modelYear").head.text
    val model = (vin \ "@modelYear").head.text

    val styles = null
    val engines = null
    val standard = null


    null
  }
}
