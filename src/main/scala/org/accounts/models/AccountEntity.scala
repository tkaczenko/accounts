package org.accounts.models

import org.accounts.models.CustomJSONProtocol.Account
import org.joda.time.format.ISODateTimeFormat
import org.joda.time.{DateTime, DateTimeZone}
import reactivemongo.bson.{BSONDateTime, BSONDocument, BSONDocumentReader, BSONDocumentWriter, BSONHandler, BSONObjectID}

/**
  * Created by tkacz- on 21.06.16.
  */
case class AccountEntity(id: BSONObjectID = BSONObjectID.generate,
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
                         session_time: Long)

object AccountEntity {
  implicit def toAccountEntity(account: Account) =
    AccountEntity(
      enabled = account.enabled,
      login = account.login,
      email = account.email,
      name = account.name,
      surname = account.surname,
      roles = account.roles,
      groups = account.groups,
      permissions = account.permissions,
      info = account.info,
      created = account.created,
      hash = account.hash,
      session_time = account.sessionTime
    )

  DateTimeZone.setDefault(DateTimeZone.UTC)

  implicit object BSONDateTimeHandler extends BSONHandler[BSONDateTime, DateTime] {
    val fmt = ISODateTimeFormat.dateTimeNoMillis()

    def read(time: BSONDateTime) = new DateTime(time.value)

    def write(jdtime: DateTime) = BSONDateTime(jdtime.getMillis)
  }

  implicit object AccountEntityReader extends BSONDocumentReader[AccountEntity] {
    def read(doc: BSONDocument): AccountEntity =
      AccountEntity(
        id = doc.getAs[BSONObjectID]("_id").get,
        enabled = doc.getAs[Boolean]("enabled").get,
        login = doc.getAs[String]("login").get,
        email = doc.getAs[String]("login").get,
        name = doc.getAs[String]("name"),
        surname = doc.getAs[String]("surname"),
        roles = doc.getAs[Seq[String]]("roles").getOrElse(default = Seq()),
        groups = doc.getAs[Seq[String]]("groups").getOrElse(default = Seq()),
        permissions = doc.getAs[Seq[String]]("permissions").getOrElse(default = Seq()),
        info = doc.getAs[AccountInfo]("info").get,
        created = doc.getAs[DateTime]("created").get,
        hash = doc.getAs[Int]("hash").get,
        session_time = doc.getAs[Long]("session_time").getOrElse(15)
      )
  }

  implicit object AccountEntityWriter extends BSONDocumentWriter[AccountEntity] {
    def write(account: AccountEntity): BSONDocument =
      BSONDocument(
        "_id" -> account.id,
        "enabled" -> account.enabled,
        "login" -> account.login,
        "email" -> account.email,
        "name" -> account.name,
        "surname" -> account.surname,
        "roles" -> account.roles,
        "groups" -> account.groups,
        "permissions" -> account.permissions,
        "info" -> account.info,
        "created" -> account.created,
        "hash" -> account.hash,
        "session_time" -> account.session_time
      )
  }

}