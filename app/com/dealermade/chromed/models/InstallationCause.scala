package com.dealermade.chromed.models

import scala.collection.immutable

import enumeratum._

sealed trait InstallationCause extends EnumEntry

object InstallationCause extends Enum[InstallationCause] {
  val values: immutable.IndexedSeq[InstallationCause] = findValues

  case object Engine extends InstallationCause
  case object RelatedCategory extends InstallationCause
  case object RelatedColor extends InstallationCause
  case object CategoryLogic extends InstallationCause
  case object OptionLogic extends InstallationCause
  case object OptionCodeBuild extends InstallationCause
  case object ExteriorColorBuild extends InstallationCause
  case object InteriorColorBuild extends InstallationCause
  case object EquipmentDescriptionInput extends InstallationCause
  case object ExteriorColorInput extends InstallationCause
  case object InteriorColorInput extends InstallationCause
  case object OptionCodeInput extends InstallationCause
  case object BaseEquipment extends InstallationCause
  case object VIN extends InstallationCause
  case object NonFactoryEquipmentInput extends InstallationCause

  def apply(elem: xml.Node): Option[InstallationCause] =
    InstallationCause.withNameInsensitiveOption(elem \@ "cause")
}
