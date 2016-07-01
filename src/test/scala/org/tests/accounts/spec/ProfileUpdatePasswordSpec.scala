package org.tests.accounts.spec

import org.accounts.models.{MasterJsonProtocol, ResponseString}
import org.accounts.routes.AccountRoute
import org.scalatest.FlatSpec
import org.scalatest.concurrent.ScalaFutures
import spray.http.ContentTypes._
import spray.http.HttpEntity
import spray.routing.HttpService
import spray.testkit.ScalatestRouteTest

class ProfileUpdatePasswordSpec extends FlatSpec with ScalatestRouteTest with HttpService with AccountRoute with ScalaFutures {
  import MasterJsonProtocol._

  val url = "/profile/update_password"
  def actorRefFactory = system

  it should ("return JSON response with code 200") in {
    Post(url, HttpEntity(`application/json`,
      """{"login":"ivanov111",
        |"oldHash":123456,
        |"hash":654321}""".stripMargin)
    ) ~> routs ~> check {
      responseAs[ResponseString].message === ResponseString(code = 200, message = "Success")
    }
  }

}