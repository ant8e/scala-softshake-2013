package softshake.ui

import softshake.app
import vaadin.scala._
import vaadin.scala.server.ScaladinRequest
import scala.beans.BeanProperty

class DemoUI extends UI(title = "Hello SoftShake") {

  override def init(request: ScaladinRequest) {
    content_=(layout)

  }


  val layout = new VerticalLayout {
    val hello = Label("HelloWorld")
    sizeFull()
    components += (hello)
  }
}
