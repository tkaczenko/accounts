package org.accounts.models

import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormatter, ISODateTimeFormat}
import spray.httpx.SprayJsonSupport
import spray.json._

/**
  * Created by tkacz- on 22.06.16.
  */
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

case class UpdateGroup(id: Int, name: String)

case class UpdateRole(id: Int, name: String)

case class ResponseString(code: Int, message: String)

case class ResponseSeqString(code: Int, message: Seq[String])

case class ResponseSeqAccount(code: Int, message: Seq[Account])

case class ResponseLong(code: Int, message: Long)

object MasterJsonProtocol extends DefaultJsonProtocol with SprayJsonSupport {
  implicit def toAccount(accountEntity: AccountEntity) =
    Account(
      id = accountEntity.id,
      enabled = accountEntity.enabled,
      login = accountEntity.login,
      email = accountEntity.email,
      name = accountEntity.name,
      surname = accountEntity.surname,
      roles = accountEntity.roles,
      groups = accountEntity.groups,
      permissions = accountEntity.permissions,
      info = accountEntity.info,
      created = accountEntity.created,
      hash = accountEntity.hash,
      sessionTime = accountEntity.session_time
    )

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

  implicit val updatePasswordFormat = jsonFormat2(UpdatePassword.apply)
  implicit val updatePasswordByUserFormat = jsonFormat2(UpdatePasswordByUser.apply)
  implicit val updateInfoFormat = jsonFormat2(UpdateInfo.apply)
  implicit val accountIdFormat = jsonFormat1(AccountId.apply)
  implicit val updateGroupFormat = jsonFormat2(UpdateGroup.apply)
  implicit val updateRoleFormat = jsonFormat2(UpdateRole.apply)
  implicit val responseStringFormat = jsonFormat2(ResponseString.apply)
  implicit val responseSeqStringFormat = jsonFormat2(ResponseSeqString.apply)
  implicit val responseSeqAccountFormat = jsonFormat2(ResponseSeqAccount.apply)
  implicit val responseLongFormat = jsonFormat2(ResponseLong.apply)

}
