package softshake.db

import scala.slick.driver.H2Driver.simple._
import Database.threadLocalSession

object Schema {

  val contacts = new Table[(Int, String, String, String)]("Contact") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def firstName = column[String]("first")

    def lastName = column[String]("last")

    def email = column[String]("email")

    def * = id ~ firstName ~ lastName ~ email

    def forInsert = firstName ~ lastName ~ email
  }
}

object Db {

  import Schema.contacts

  val url = "jdbc:h2:file:softshake"
  val driver = "org.h2.Driver"

  val db = Database.forURL(url, driver)

  def createSchema() = {
    db withSession {
      contacts.ddl.drop
      contacts.ddl.create
    }
  }

  def insert(first: String, last: String, email: String) = {
    db.withSession {
      contacts.forInsert returning contacts.id insert (first, last, email)
    }
  }

  def listAll = {
    db.withSession {
      Query(contacts).list()
    }

  }

}

