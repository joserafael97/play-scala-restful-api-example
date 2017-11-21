
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/rafaelfeitosa/workspace/Scala/workspace/play-scala-restful-api-example/conf/routes
// @DATE:Tue Nov 21 19:14:57 BRT 2017

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:1
  Application_0: controllers.Application,
  // @LINE:3
  controllers_task_TaskRouter_0: controllers.task.TaskRouter,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:1
    Application_0: controllers.Application,
    // @LINE:3
    controllers_task_TaskRouter_0: controllers.task.TaskRouter
  ) = this(errorHandler, Application_0, controllers_task_TaskRouter_0, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, Application_0, controllers_task_TaskRouter_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.Application.index"""),
    prefixed_controllers_task_TaskRouter_0_1.router.documentation,
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:1
  private[this] lazy val controllers_Application_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_Application_index0_invoker = createInvoker(
    Application_0.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """""",
      Seq()
    )
  )

  // @LINE:3
  private[this] val prefixed_controllers_task_TaskRouter_0_1 = Include(controllers_task_TaskRouter_0.withPrefix(this.prefix + (if (this.prefix.endsWith("/")) "" else "/") + "tasks"))


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:1
    case controllers_Application_index0_route(params@_) =>
      call { 
        controllers_Application_index0_invoker.call(Application_0.index)
      }
  
    // @LINE:3
    case prefixed_controllers_task_TaskRouter_0_1(handler) => handler
  }
}
