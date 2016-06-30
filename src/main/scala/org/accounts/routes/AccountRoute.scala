package org.accounts.routes

import org.accounts.models._
import org.accounts.services.AccountService
import spray.routing.HttpService

trait AccountRoute extends HttpService {
  val routs =
    post {
      import MasterJsonProtocol._
      import spray.httpx.SprayJsonSupport.sprayJsonUnmarshaller

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
          entity(as[UpdateGroup]) { group => implicit requestContext =>
            AccountService.deleteGroup(group)
          }
        } ~
        path("insert_groupd") {
          entity(as[UpdateGroup]) { group => implicit requestContext =>
            AccountService.insertGroup(group)
          }
        } ~
        path("delete_role") {
          entity(as[UpdateRole]) { role => implicit requestContext =>
            AccountService.deleteRole(role)
          }
        } ~
        path("insert_role") {
          entity(as[UpdateRole]) { role => implicit requestContext =>
            AccountService.insertRole(role)
          }
        } ~
        path("session_time") {
          complete(???)
        } ~
        path("permissions") {
          complete(???)
        }
    }
}
