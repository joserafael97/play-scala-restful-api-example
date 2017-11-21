package controllers.task

import javax.inject.Inject

import play.api.Logger
import play.api.data.Form
import play.api.libs.json.Json
import play.api.mvc._


import scala.concurrent.{ ExecutionContext, Future }

case class TaskFormInput(title: String, description: String)



class TaskController @Inject() ( cc: TaskControllerComponents )(implicit ec: ExecutionContext)
  extends TaskBaseController(cc){

  private val logger = Logger(getClass)

  private val form: Form[TaskFormInput] = {
    import play.api.data.Forms._

    Form(
      mapping(
        "title" -> text,
        "description" -> text)
        (TaskFormInput.apply)(TaskFormInput.unapply))
  }

  def index: Action[AnyContent] = TaskAction.async { implicit request =>
    logger.trace("index: ")
    taskResourceHandler.find.map { tasks =>
      Ok(Json.toJson(tasks))
    }
  }

  def process: Action[AnyContent] = TaskAction.async { implicit request =>
    logger.trace("process: ")
    processJsonPost()
  }

  def show(id: String): Action[AnyContent] = TaskAction.async { implicit request =>
    logger.trace(s"show: id = $id")
    taskResourceHandler.lookup(id).map { task =>
      Ok(Json.toJson(task))
    }
  }

  def remove(id: String): Action[AnyContent] = TaskAction.async { implicit request =>
    logger.trace(s"show: id = $id")
    taskResourceHandler.remove(id).map { task =>
      Ok(Json.toJson(task))
    }
  }

  def update(id: String): Action[AnyContent] = TaskAction.async { implicit request =>
    logger.trace(s"show: id = $id")
    processJsonPut(id)
  }

  private def processJsonPost[A]()(implicit request: TaskRequest[A]): Future[Result] = {

    def failure(badForm: Form[TaskFormInput]) = {
      
      Future.successful(BadRequest(badForm.errorsAsJson))
    }
    

    def success(input: TaskFormInput) = {
      taskResourceHandler.create(input).map { task =>
        Created(Json.toJson(task))
      }
    }

    form.bindFromRequest().fold(failure, success)
  }

  private def processJsonPut[A](id: String)(implicit request: TaskRequest[A]): Future[Result] = {

    def failure(badForm: Form[TaskFormInput]) = {
      Future.successful(BadRequest(badForm.errorsAsJson))
    }

    def success(input: TaskFormInput) = {
      taskResourceHandler.update(id, input).map { task =>
        Ok(Json.toJson(task))
      }
    }

    form.bindFromRequest().fold(failure, success)
  }
}
