package org.accounts.dao

import org.accounts.MongoDBConnection
import org.accounts.models.AccountEntity
import reactivemongo.bson._

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by tkacz- on 22.06.16.
  */
object AccountDAO {
  def create(account: AccountEntity) = MongoDBConnection.collection_accounts
    .insert(account)

  def findById(id: String) = MongoDBConnection.collection_accounts
    .find(BSONDocument("_id" -> BSONObjectID(id))).one[AccountEntity]

  def findByLogin(login: String) = MongoDBConnection.collection_accounts
    .find(BSONDocument("login" -> login)).one[AccountEntity]

}