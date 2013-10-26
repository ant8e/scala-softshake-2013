package softshake.db

import scala.slick.driver.H2Driver.simple._
import Database.threadLocalSession

object Schema {
  val contact = new Table[(Int, String, String, String)]("Contact") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def first = column[String]("first")

    def last = column[String]("last")

    def email = column[String]("email")

    def * = id ~ first ~ last ~ email

    def forInsert = first ~ last ~ email

  }

}

trait ContactRepositoryComponenent {
  def repository: ContactRepository

  trait ContactRepository {
    def listAll(): List[(Int, String, String, String)]

    def insert(f: String, l: String, e: String): Int
  }

}

trait ContactRepositoryH2Component extends ContactRepositoryComponenent {
  def repository = new ContactRepository {

    import Schema.contact

    val url = "jdbc:h2:file:softshake"
    val driver = "org.h2.Driver"

    val db = Database.forURL(url, driver)

    def listAll() = db withSession {
      Query(contact).list()
    }

    def insert(f: String, l: String, e: String) = db.withSession {
      contact.forInsert returning contact.id insert (f, l, e)
    }
  }

}

