package org.tests.accounts.spec

import org.accounts.models.{MasterJsonProtocol, ResponseString}
import org.accounts.routes.AccountRoute
import org.scalatest.FlatSpec
import org.scalatest.concurrent.Eventually.eventually
import org.scalatest.concurrent.ScalaFutures
import spray.http.ContentTypes._
import spray.http.HttpEntity
import spray.routing.HttpService
import spray.testkit.ScalatestRouteTest

class UpdateSpec extends FlatSpec with ScalatestRouteTest with HttpService with AccountRoute with ScalaFutures {

  import MasterJsonProtocol._

  val url = "/create"

  def actorRefFactory = system

  "AccountService" should "return JSON response with code 200" in {
    Post(url, HttpEntity(`application/json`,
      """{"id":1,
        |"enabled":false,
        |"login":"ivanov222",
        |"email":"ivanov2@gmail.com",
        |"name":"Ivan",
        |"surname":"Ivanov",
        |"roles":["Admin"],
        |"groups":["5720a6bf56d06b2bbf907230"],
        |"permissions":["Some"],
        |"info":{"phone":"+380682321234","company":"Ltd"},
        |"created":"2012-01-02T21:13:45-07:00",
        |"hash":123456,
        |"sessionTime":15}""".stripMargin)
    ) ~> routs ~> check {
      eventually {
        responseAs[ResponseString] === ResponseString(code = 200, message = "Success")
      }
    }
  }

}