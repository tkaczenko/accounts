package org.tests.accounts.spec

import org.accounts.models.{MasterJsonProtocol, ResponseLong}
import org.accounts.routes.AccountRoute
import org.scalatest.FlatSpec
import org.scalatest.concurrent.ScalaFutures
import spray.http.ContentTypes._
import spray.http.HttpEntity
import spray.routing.HttpService
import spray.testkit.ScalatestRouteTest

class SessionTimeSpec extends FlatSpec with ScalatestRouteTest with HttpService with AccountRoute with ScalaFutures {
  import MasterJsonProtocol._

  val url = "/session_time"
  def actorRefFactory = system

  it should ("return JSON response with code 200") in {
    Post(url, HttpEntity(`application/json`,
      """{"login":"ivanov111"}""".stripMargin)
    ) ~> routs ~> check {
      responseAs[ResponseLong].message === 15
    }
  }

}