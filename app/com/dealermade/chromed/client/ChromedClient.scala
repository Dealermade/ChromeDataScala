package com.dealermade.chromed.client

import javax.inject.{Inject, Singleton}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}
import scala.xml.XML

import com.dealermade.chromed.models.Vehicle
import play.api.Configuration
import play.api.libs.ws.WSClient

@Singleton
class ChromedClient @Inject()(ws: WSClient,
                              configuration: Configuration)
                             (implicit ec: ExecutionContext) {

  private val accountNumber = configuration.getOptional[String]("chromed.account.number")
  private val accountSecret = configuration.getOptional[String]("chromed.account.secret")

  def describeVehicle(vin: String): Future[Option[Vehicle]] = {
    val data =
      <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                        xmlns:urn="urn:description7a.services.chrome.com">
        <soapenv:Header/>
        <soapenv:Body>
          <urn:VehicleDescriptionRequest>
            <urn:accountInfo number={accountNumber.getOrElse("")}
                             secret={accountSecret.getOrElse("")}
                             country="US" language="en"/>
            <urn:vin>{vin}</urn:vin>
          </urn:VehicleDescriptionRequest>
        </soapenv:Body>
      </soapenv:Envelope>

    ws.url("https://services.chromedata.com/Description/7a")
      .withHttpHeaders("Content-Type" -> "text/xml;charset=UTF-8")
      .post(data.toString)
      .map { r =>
        Try(XML.loadString(r.body))
          .toOption
          .map(c => c \ "Body" \ "VehicleDescription")
          .flatMap(_.headOption)
          .map(Vehicle(_))
      }
  }
}

