package org.accounts.models

import reactivemongo.bson.{BSONDocument, BSONDocumentReader, BSONDocumentWriter}

/**
  * Created by tkacz- on 22.06.16.
  */
case class AccountInfo(phone: Option[String], company: Option[String])

object AccountInfo {

  implicit object AccountInfoBSONReader extends BSONDocumentReader[AccountInfo] {
    def read(doc: BSONDocument): AccountInfo =
      AccountInfo(
        phone = doc.getAs[String]("phone"),
        company = doc.getAs[String]("company")
      )
  }

  implicit object AccountInfoBSONWriter extends BSONDocumentWriter[AccountInfo] {
    def write(accountInfo: AccountInfo): BSONDocument =
      BSONDocument(
        "phone" -> accountInfo.phone,
        "company" -> accountInfo.company
      )
  }

}