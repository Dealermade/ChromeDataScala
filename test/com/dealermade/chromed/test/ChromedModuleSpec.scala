package com.dealermade.chromed.test

import scala.concurrent.ExecutionContext

import com.dealermade.chromed.ChromedModule
import com.dealermade.chromed.client.ChromedClient
import com.dealermade.chromed.models.Vehicle
import org.scalatest.{AsyncFlatSpec, FlatSpec}
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder

class ChromedModuleSpec extends AsyncFlatSpec {

  val application: Application = new GuiceApplicationBuilder()
    .bindings(new ChromedModule)
    .build()

  implicit val ec: ExecutionContext = application.injector.instanceOf(classOf[ExecutionContext])
  val client: ChromedClient = application.injector.instanceOf(classOf[ChromedClient])

  behavior of "ChromedClient"

  it should "describe a vehicle by its vin" in {
    client.describeVehicle(vin = "3VWJM71K98M043923")
      .mapTo[Option[Vehicle]]
      .map { v =>
        assert(v.isDefined)
        assert(v.get.modelYear == 2008)
        assert(v.get.model == "Jetta Sedan")
        assert(v.get.division == "Volkswagen")
      }
  }

  it should "return an empty option when a vehicle isn't found" in {
    client.describeVehicle(vin = "INVALIDVIN")
      .mapTo[Option[Vehicle]]
      .map { v => assert(v.isEmpty) }
  }
}
