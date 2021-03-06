package org.tests.accounts.spec

import org.accounts.models.{Account, MasterJsonProtocol, ResponseSeqAccount}
import org.accounts.routes.AccountRoute
import org.scalatest.FlatSpec
import org.scalatest.concurrent.Eventually.eventually
import org.scalatest.concurrent.ScalaFutures
import spray.http.ContentTypes._
import spray.http.HttpEntity
import spray.routing.HttpService
import spray.testkit.ScalatestRouteTest

class ListSpec extends FlatSpec with ScalatestRouteTest with HttpService with AccountRoute with ScalaFutures {

  import MasterJsonProtocol._

  val url = "/list"

  def actorRefFactory = system

  "AccountService" should "return JSON response with code 200" in {
    Post(url, HttpEntity(`application/json`,
      """{}""".stripMargin)
    ) ~> routs ~> check {
      eventually {
        responseAs[ResponseSeqAccount].message === List[Account]().nonEmpty
      }
    }
  }

}