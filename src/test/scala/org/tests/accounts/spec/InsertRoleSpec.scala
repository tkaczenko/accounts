package org.tests.accounts.spec

import org.accounts.models.{MasterJsonProtocol, ResponseString}
import org.accounts.routes.AccountRoute
import org.scalatest.FlatSpec
import org.scalatest.concurrent.ScalaFutures
import spray.http.ContentTypes._
import spray.http.HttpEntity
import spray.routing.HttpService
import spray.testkit.ScalatestRouteTest
import org.scalatest.concurrent.Eventually.eventually

/**
  * Created by tkacz- on 02.07.16.
  */
class InsertRoleSpec extends FlatSpec with ScalatestRouteTest with HttpService with AccountRoute with ScalaFutures {
  import MasterJsonProtocol._

  val url = "/insert_role"
  def actorRefFactory = system

  it should ("return JSON response with code 200") in {
    Post(url, HttpEntity(`application/json`,
      """{"id":1,
        |"name":"Some role"}""".stripMargin)
    ) ~> routs ~> check {
      eventually {
        responseAs[ResponseString] === ResponseString(code = 200, message = "Success")
      }
    }
  }
}
