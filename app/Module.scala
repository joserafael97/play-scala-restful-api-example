import javax.inject._

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import play.api.{Configuration, Environment}

import repository.TaskRepository
import repository.TaskRepositoryImpl


class Module (environment: Environment, configuration: Configuration)
    extends AbstractModule
    with ScalaModule {

  override def configure() = {
    bind[TaskRepository].to[TaskRepositoryImpl].in[Singleton]
  }

}
