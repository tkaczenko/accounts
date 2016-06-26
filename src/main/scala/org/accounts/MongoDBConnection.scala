package org.accounts

import org.accounts.Config.MongoDB._
import reactivemongo.api.MongoDriver
import reactivemongo.api.collections.bson.BSONCollection

import scala.concurrent.ExecutionContext.Implicits.global

object MongoDBConnection {
  val driver = new MongoDriver
  val connection = driver.connection(List(mongoHost))
  val db = connection(dbName)
  val collection_accounts: BSONCollection = db(collectionAccounts)

  def init(): Unit = {}
}