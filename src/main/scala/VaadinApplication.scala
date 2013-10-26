package softshake.ui

import softshake.app
import vaadin.scala._
import vaadin.scala.server.ScaladinRequest
import scala.beans.BeanProperty

class DemoUI extends UI(title = "Hello SoftShake") {

  override def init(request: ScaladinRequest) {
    content_=(layout)

  }

  case class Contact(@BeanProperty id: Int, @BeanProperty firstName: String, @BeanProperty lastName: String, @BeanProperty email: String)

  val table = new Table {
    sizeFull()
    container = new BeanItemContainer[Contact](app.mainComponent.service.listAll.map { case (i, f, l, e) ⇒ Contact(i, f, l, e) })
  }
  val layout = new VerticalLayout {
    val hello = Label("HelloWorld")
    sizeFull()
    components += (table)
  }
}
