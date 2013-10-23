package softshake.ui

import softshake.ContactServiceComponent
import softshake.db.Db
import vaadin.scala._
import vaadin.scala.server.ScaladinRequest
import scala.beans.BeanProperty

class DemoUI extends UI(title = "Hello SoftShake") {

  case class Contact(@BeanProperty id: Int, @BeanProperty first: String, @BeanProperty last: String, @BeanProperty email: String)

  lazy val env: ContactServiceComponent = softshake.Env.demoCapEnv

  override def init(request: ScaladinRequest) {
    content_=(layout)
    table.refresh()
  }

  lazy val table = new Table {
    sizeFull
    styleNames += Reindeer.TableStrong

    def refresh() = {
      container = new BeanItemContainer(readContacts())
      visibleColumns = Seq("id", "first", "last", "email")
    }
  }

  val form = new VerticalLayout {
    sizeUndefined
    val first = new TextField() {
      caption = Some("First Name")
    }

    val last = new TextField {
      caption = Some("Last Name")
    }
    val email = new TextField {
      caption = Some("Email")
    }

    val save = Button("Save", _ ⇒ {
      for (
        f ← first.value;
        l ← last.value;
        e ← email.value
      ) {
        env.service.add(f, l, e)
        table.refresh()
      }
    })
    components += (first, last, email, save)

  }

  val layout = new VerticalLayout {
    margin = true
    sizeFull

    components += (form, table)
  }

  def readContacts() = env.service.load.map {
    case (id, first, last, email) ⇒ Contact(id, first, last, email)
  }
}
