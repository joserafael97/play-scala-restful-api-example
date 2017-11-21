package controllers.task

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._
import repository.TaskId

/*
 * Controll task routes
 */
class TaskRouter @Inject() (controller: TaskController) extends SimpleRouter {

  val prefix = "/tasks"

  def link(id: TaskId): String = {
    import com.netaporter.uri.dsl._
    val url = prefix / id.toString
    url.toString()
  }

  override def routes: Routes = {
    case GET(p"/") =>
      controller.index

    case POST(p"/") =>
      controller.process

    case GET(p"/$id") =>
      controller.show(id)

    case DELETE(p"/$id") =>
      controller.remove(id)

    case PUT(p"/$id") =>
      controller.update(id)
  }

}
