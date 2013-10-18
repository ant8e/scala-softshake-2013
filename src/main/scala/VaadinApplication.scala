package softshake.ui

import softshake.db.Db
import vaadin.scala._
import vaadin.scala.server.ScaladinRequest
import scala.beans.BeanProperty

class DemoUI extends UI(title = "Hello SoftShake") {
  Class.forName("org.h2.Driver")
  var contacts = readContacts()

  override def init(request: ScaladinRequest) {
    content_=(layout)
  }
  case class Contact(@BeanProperty id: Int, @BeanProperty first: String, @BeanProperty last: String, @BeanProperty email: String)

  val table = new Table {
    sizeFull
    styleNames += Reindeer.TableStrong
    container = new BeanItemContainer(contacts)

    visibleColumns = Seq("id", "first", "last")
  }
  val layout = new VerticalLayout {
    margin = true
    sizeFull
    //components += Label("This is a basic application")

    components += table
  }

  def readContacts() = Db.listAll.map { case (id, first, last, email) ⇒ Contact(id, first, last, email) }
}
