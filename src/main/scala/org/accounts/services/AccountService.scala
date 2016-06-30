package org.accounts.services

import org.accounts.CustomLogger
import org.accounts.dao.AccountDAO
import org.accounts.models.CustomJSONProtocol._
import spray.http.StatusCodes
import spray.routing.RequestContext

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

/**
  * Created by tkacz- on 25.06.16.
  */

object AccountService {
  def create(account: Account)(implicit requestContext: RequestContext) = {
    AccountDAO.findByLogin(account.login) map {
      case None =>
        AccountDAO.create(account)
          .onComplete(processResult)
      case _ =>
        requestContext.complete(Response(StatusCodes.NotFound.intValue, "Someone already has that login"))
    }
  }

  def update(account: Account)(implicit requestContext: RequestContext) = {
    AccountDAO.findByLogin(account.login) map {
      case None =>
        AccountDAO.findById(account.id) map {
          case None =>
            requestContext.complete(Response(StatusCodes.NotFound.intValue, "This id doesn't match any document"))
          case _ =>
            AccountDAO.updateById(account)
              .onComplete(processResult)
        }
      case _ =>
        requestContext.complete(Response(StatusCodes.NotFound.intValue, "Someone already has that login"))
    }
  }

  def updatePassword(updatePassword: UpdatePassword)(implicit requestContext: RequestContext) = {
    AccountDAO.findById(updatePassword.id) map {
      case None =>
        requestContext.complete(Response(StatusCodes.NotFound.intValue, "This id doesn't match any document"))
      case _ =>
        AccountDAO.updatePasswordByID(updatePassword.id, updatePassword.hash)
          .onComplete(processResult)
    }
  }

  def updatePasswordProfile(updatePasswordByUser: UpdatePasswordByUser)(implicit requestContext: RequestContext) = {
    AccountDAO.findByLogin(updatePasswordByUser.login) map {
      case None =>
        requestContext.complete(Response(StatusCodes.NotFound.intValue, "This id doesn't match any document"))
      case _ =>
        AccountDAO.updatePasswordByLogin(updatePasswordByUser.login, updatePasswordByUser.hash)
          .onComplete(processResult)
    }
  }

  def updateInfo(updateInfo: UpdateInfo)(implicit requestContext: RequestContext) = {
    AccountDAO.findByLogin(updateInfo.login) map {
      case None =>
        requestContext.complete(Response(StatusCodes.NotFound.intValue, "This id doesn't match any document"))
      case _ =>
        AccountDAO.updateInfo(updateInfo.login, updateInfo.info)
          .onComplete(processResult)
    }
  }

  def list()(implicit requestContext: RequestContext) = {
    AccountDAO.findAll()
      .onComplete(processResult)
  }

  def processResult[T](value: Try[T])(implicit requestContext: RequestContext) = {
    value match {
      case Success(writeResult) => requestContext.complete(Response(StatusCodes.OK.intValue, "Success"))
      case Failure(ex) => {
        CustomLogger.logger.error(ex.getMessage, ex)
        requestContext.complete(Response(StatusCodes.BadRequest.intValue, "Error"))
      }
    }
  }
}
