package org.accounts.dao

import org.accounts.MongoDBConnection
import org.accounts.models.{AccountEntity, AccountInfo}
import reactivemongo.bson._

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by tkacz- on 22.06.16.
  */
object AccountDAO {
  def create(accountEntity: AccountEntity) = MongoDBConnection.collection_accounts
    .insert(accountEntity)

  def updateById(accountEntity: AccountEntity) = MongoDBConnection.collection_accounts
    .findAndUpdate(
      BSONDocument("_id" -> accountEntity.id),
      BSONDocument("$set" -> accountEntity)
    )

  def updatePasswordByID(id: Int, hash: Int) = MongoDBConnection.collection_accounts
    .findAndUpdate(
      BSONDocument("_id" -> id),
      BSONDocument("$set" -> BSONDocument("hash" -> hash))
    )

  def updatePasswordByLogin(login: String, hash: Int) = MongoDBConnection.collection_accounts
    .findAndUpdate(
      BSONDocument("login" -> login),
      BSONDocument("$set" -> BSONDocument("hash" -> hash))
    )

  def updateInfo(login: String, accountInfo: AccountInfo) = MongoDBConnection.collection_accounts
    .findAndUpdate(
      BSONDocument("login" -> login),
      BSONDocument("$set" -> BSONDocument("info" -> accountInfo))
    )

  def findAll() = MongoDBConnection.collection_accounts
    .find(BSONDocument.empty)
    .cursor[AccountEntity]()
    .collect[List]()

  def setEnabled(id: Int, enabled: Boolean) = MongoDBConnection.collection_accounts
    .findAndUpdate(
      BSONDocument("_id" -> id),
      BSONDocument("$set" -> BSONDocument("enabled" -> enabled))
    )

  def checkPassword(login: String, oldPass: Int, newPass: Int): Boolean = {
    if (findByLogin(login).equals(None)) {
      false
    } else {
      true
    }
  }

  def findById(id: Int) = MongoDBConnection.collection_accounts
    .find(BSONDocument("_id" -> id))
    .one[AccountEntity]

  def findByLogin(login: String) = MongoDBConnection.collection_accounts
    .find(BSONDocument("login" -> login))
    .one[AccountEntity]

}
