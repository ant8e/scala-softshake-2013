package softshake

import softshake.db.{ ContactRepositoryH2Component, ContactRepositoryComponenent }

trait ContactServiceComponent {
  def service: ContactService

  trait ContactService {
    def listAll: List[(Int, String, String, String)]
  }

}

trait ContactServiceBasicComponent extends ContactServiceComponent {
  self: ContactRepositoryComponenent ⇒
  def service = new ContactService {
    def listAll: List[(Int, String, String, String)] = repository.listAll()
  }

}

object app {
  lazy val mainComponent = new ContactServiceBasicComponent with ContactRepositoryH2Component {}
}