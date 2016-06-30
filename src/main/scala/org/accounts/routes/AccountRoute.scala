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
          entity(as[UpdatePasswordByUser]) { password => implicit requestContext =>
            AccountService.updatePasswordProfile(password)
          }
        } ~
        path("profile" / "update_info") {
          entity(as[UpdateInfo]) { info => implicit requestContext =>
            AccountService.updateInfo(info)
          }
        } ~
        path("list") {
          implicit requestContext =>
            AccountService.list()
        } ~
        path("enable") {
          entity(as[AccountId]) { accountId => implicit requestContext =>
            AccountService.enable(accountId)
          }
        } ~
        path("disable") {
          entity(as[AccountId]) { accountId => implicit requestContext =>
            AccountService.disable(accountId)
          }
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
