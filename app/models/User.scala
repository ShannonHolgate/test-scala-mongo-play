package models

import play.api.Play.current
import java.util.Date
import com.novus.salat._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import se.radley.plugin.salat._
import mongoContext._

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 02/11/13
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 */
case class User(
                 id: ObjectId = new ObjectId,
                 username: String,
                 password: String = "default",
                 added: Date = new Date(),
                 updated: Option[Date] = None,
                 deleted: Option[Date] = None
                 )

object User extends ModelCompanion[User, ObjectId] {
  val dao = new SalatDAO[User, ObjectId](collection = mongoCollection("users")) {}

  def all(): List[User] = dao.find(MongoDBObject.empty).toList
  def create(username: String) {
    dao.insert(User(username = username))
  }

  def delete(username: String) {
    dao.remove(MongoDBObject("username" -> username))
  }
  def findOneByUsername(username: String): Option[User] = dao.findOne(MongoDBObject("username" -> username))
}