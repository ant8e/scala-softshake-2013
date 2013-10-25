package softshake.db

import scala.slick.driver.H2Driver.simple._
import Database.threadLocalSession

object Schema {
  val contact = ???

}

object Db {

  import Schema.contact

  val url = "jdbc:h2:file:softshake"
  val driver = "org.h2.Driver"

  val db = Database.forURL(url, driver)


}

