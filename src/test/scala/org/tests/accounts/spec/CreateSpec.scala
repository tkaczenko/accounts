package org.tests.accounts.spec

import org.accounts.models.{Account, AccountInfo, MasterJsonProtocol, ResponseString}
import org.accounts.routes.AccountRoute
import org.joda.time.DateTime
import org.scalatest.FlatSpec
import org.scalatest.concurrent.Eventually.eventually
import org.scalatest.concurrent.ScalaFutures
import spray.routing.HttpService
import spray.testkit.ScalatestRouteTest

class CreateSpec extends FlatSpec with ScalatestRouteTest with HttpService with AccountRoute with ScalaFutures {

  import MasterJsonProtocol._

  val url = "/create"

  def actorRefFactory = system

  val account = Account(
    id = 1,
    enabled = true,
    login = "ivanov111",
    email = "ivanov@gmail.com",
    name = Some("Ivan"),
    surname = Some("Ivanov"),
    roles = Seq[String]("User"),
    groups = Seq[String]("5720a6bf56d06b2bbf907230"),
    permissions = Seq[String]("Some"),
    info = AccountInfo(phone = Some("+380682321234"), company = Some("Inc")),
    created = new DateTime("2004-01-02T21:13:45-07:00"),
    hash = 123456,
    sessionTime = 15
  )

  "AccountService" should "return JSON response with message Success" in {
    Post(url, account) ~> routs ~> check {
      eventually {
        responseAs[ResponseString] === ResponseString(code = 200, message = "Success")
      }
    }
  }

  it should "return JSON response with message Someone already has that login" in {
    Post(url, account) ~> routs ~> check {
      eventually {
        responseAs[ResponseString] === ResponseString(code = 200, message = "Someone already has that login")
      }
    }
  }
}