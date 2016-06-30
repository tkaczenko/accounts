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
        AccountDAO.create(account).onComplete(processResult)
      case _ =>
        requestContext.complete(Response(StatusCodes.NotFound.intValue, "Someone already has that login"))
    }
  }

  def update(account: Account)(implicit requestContext: RequestContext) = {
    AccountDAO.findByLogin(account.login) map {
      case None =>
        AccountDAO.findById(account.id.toString) map {
          case None =>
            requestContext.complete(Response(StatusCodes.NotFound.intValue, "This id doesn't match any document"))
          case _ =>
            AccountDAO.updateById(account).onComplete(processResult)
        }
      case _ =>
        requestContext.complete(Response(StatusCodes.NotFound.intValue, "Someone already has that login"))
    }
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
