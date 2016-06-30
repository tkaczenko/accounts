package org.accounts.routes

import org.accounts.models.CustomJSONProtocol._
import org.accounts.services.AccountService
import spray.routing.HttpService

trait AccountRoute extends HttpService {
  val routs =
    post {
      path("create") {
        entity(as[Account]) { account => implicit requestContext =>
          AccountService.create(account)
        }
      } ~
        path("update") {
          entity(as[Account]) { account => implicit requestContext =>
            AccountService.update(account)
          }
        } ~
        path("update_password") {
          entity(as[UpdatePassword]) { password => implicit requestContext =>
            AccountService.updatePassword(password)
          }
        } ~
        path("profile" / "update_password") {
          complete(???)
        } ~
        path("profile" / "update_info") {
          complete(???)
        } ~
        path("list") {
          complete(???)
        } ~
        path("enable") {
          complete(???)
        } ~
        path("disable") {
          complete(???)
        } ~
        path("delete_group") {
          complete(???)
        } ~
        path("delete_role") {
          complete(???)
        } ~
        path("insert_role") {
          complete(???)
        } ~
        path("session_time") {
          complete(???)
        } ~
        path("permissions") {
          complete(???)
        }
    }
}
