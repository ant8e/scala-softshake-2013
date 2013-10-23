package softshake

import softshake.db.Db.{ ContactRepositoryComponentH2Impl, ContactRepositoryComponent }

trait ContactServiceComponent {
  def service: ContactService

  trait ContactService {
    def load: List[(Int, String, String, String)]

    def add(first: String, last: String, email: String): Unit
  }

}

trait ContactServicePassThroughComponent extends ContactServiceComponent {
  self: ContactRepositoryComponent ⇒

  def service = new PassThroughImpl

  class PassThroughImpl extends ContactService {
    def load: List[(Int, String, String, String)] = {
      contactRepository.listAll
    }

    def add(first: String, last: String, email: String) = {
      contactRepository.insert(first.capitalize, last.capitalize, email)
    }
  }

}

trait ContactServiceCapitalizeComponent extends ContactServicePassThroughComponent {
  self: ContactRepositoryComponent ⇒

  override def service = new PassThroughImpl {
    override def add(first: String, last: String, email: String) = {
      super.add(first.capitalize, last.capitalize, email)
    }
  }
}

object Env {
  val demoEnv = new ContactServicePassThroughComponent with ContactRepositoryComponentH2Impl
  val demoCapEnv = new ContactServiceCapitalizeComponent with ContactRepositoryComponentH2Impl
}
