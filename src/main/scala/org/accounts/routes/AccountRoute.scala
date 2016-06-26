package org.accounts.routes

import spray.routing.HttpService

trait AccountRoute extends HttpService {
  val routs =
    post {
      path("create") {
        complete(???)
      } ~
        path("update") {
          complete(???)
        } ~
        path("update_password") {
          complete(???)
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
