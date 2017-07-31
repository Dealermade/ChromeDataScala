package com.dealermade.chromed

import com.dealermade.chromed.client.ChromedClient
import play.api.inject.{Binding, Module}
import play.api.{Configuration, Environment}

class ChromedModule extends Module {

  override def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] = {


    Seq(bind[ChromedClient].toSelf)
  }
}
