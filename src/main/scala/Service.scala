package softshake


trait ContactServiceComponent {
  def service: ContactService

  trait ContactService {
    def listAll: List[(Int, String, String, String)]
  }

}

trait ContactServiceBasicComponent extends ContactServiceComponent {

}

object app {
  val mainComponent = ???
}