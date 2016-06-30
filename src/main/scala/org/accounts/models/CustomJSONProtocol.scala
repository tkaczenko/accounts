package org.accounts.models

import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormatter, ISODateTimeFormat}
import spray.httpx.SprayJsonSupport

/**
  * Created by tkacz- on 22.06.16.
  */
object CustomJSONProtocol {

  import spray.json._

  case class Account(id: Int,
                     enabled: Boolean,
                     login: String,
                     email: String,
                     name: Option[String],
                     surname: Option[String],
                     roles: Seq[String],
                     groups: Seq[String],
                     permissions: Seq[String],
                     info: AccountInfo,
                     created: DateTime,
                     hash: Int,
                     sessionTime: Long)

  case class UpdatePassword(id: Int, hash: Int)
  case class UpdatePasswordByUser(login: String, hash: Int)
  case class UpdateInfo(login: String, info: AccountInfo)
  case class AccountId(id: Int)

  case class Response(code: Int, message: String)

  object UpdatePassword extends DefaultJsonProtocol with SprayJsonSupport {
    implicit val updatePasswordFormat = jsonFormat2(UpdatePassword.apply)
  }
  object UpdatePasswordByUser extends DefaultJsonProtocol with SprayJsonSupport {
    implicit val updatePasswordFormat = jsonFormat2(UpdatePasswordByUser.apply)
  }

  object UpdateInfo extends DefaultJsonProtocol with SprayJsonSupport {
    implicit val accountInfoFormat = jsonFormat2(AccountInfo.apply)
    implicit val updateInfoFormat = jsonFormat2(UpdateInfo.apply)
  }

  object AccountId extends DefaultJsonProtocol with SprayJsonSupport {
    implicit val accountIdFormat = jsonFormat1(AccountId.apply)
  }

  object Response extends DefaultJsonProtocol with SprayJsonSupport {
    implicit val responseFormat = jsonFormat2(Response.apply)
  }

  object Account extends DefaultJsonProtocol with SprayJsonSupport {

    implicit object DateJsonFormat extends RootJsonFormat[DateTime] {
      private val parserISO: DateTimeFormatter = ISODateTimeFormat.dateTimeNoMillis();

      override def write(obj: DateTime) = JsString(parserISO.print(obj))

      override def read(json: JsValue): DateTime = json match {
        case JsString(s) => parserISO.parseDateTime(s)
        case _ => throw new DeserializationException(s"Expected ISO Date format")
      }
    }

    implicit val accountInfoFormat = jsonFormat2(AccountInfo.apply)
    implicit val accountFormat = jsonFormat13(Account.apply)
  }

}
