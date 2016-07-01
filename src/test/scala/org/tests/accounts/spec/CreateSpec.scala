package org.tests.accounts.spec

import org.accounts.models.{MasterJsonProtocol, ResponseString}
import org.accounts.routes.AccountRoute
import org.scalatest.FlatSpec
import org.scalatest.concurrent.ScalaFutures
import spray.http.ContentTypes._
import spray.http.HttpEntity
import spray.routing.HttpService
import spray.testkit.ScalatestRouteTest

class CreateSpec extends FlatSpec with ScalatestRouteTest with HttpService with AccountRoute with ScalaFutures {
  import MasterJsonProtocol._

  val url = "/create"

  def actorRefFactory = system

  it should ("return JSON response with code 200") in {
    Post(url, HttpEntity(`application/json`,
      """{"id":1,
        |"enabled":true,
        |"login":"ivanov111",
        |"email":"ivanov@gmail.com",
        |"name":"Ivan",
        |"surname":"Ivanov",
        |"roles":["User"],
        |"groups":["5720a6bf56d06b2bbf907230"],
        |"permissions":["Some"],
        |"info":{"phone":"+380682321234","company":"INC"},
        |"created":"2004-01-02T21:13:45-07:00",
        |"hash":123456,
        |"sessionTime":15}""".stripMargin)
    ) ~> routs ~> check {
      responseAs[ResponseString] === ResponseString(code = 200, message = "Success")
    }
  }
}