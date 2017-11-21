package controllers.task

import javax.inject.{ Inject, Provider }

import play.api.MarkerContext

import scala.concurrent.{ ExecutionContext, Future }
import scala.util.Random
import domain.Task
import repository.TaskRepository
import repository.TaskId
import repository.TaskData

/**
 * Controls access to the backend data, returning [[Task]]
 */
class TaskResourceHandler @Inject() (
  routerProvider: Provider[TaskRouter],
  taskRepository: TaskRepository)(implicit ec: ExecutionContext) {

  def create(taskInput: TaskFormInput)(implicit mc: MarkerContext): Future[Task] = {
    val data = TaskData(TaskId(Random.nextInt(400).toString()), taskInput.title, taskInput.description)

    taskRepository.create(data).map { id =>
      createTaskResource(data)
    }
  }

  def lookup(id: String)(implicit mc: MarkerContext): Future[Option[Task]] = {
    val taskFuture = taskRepository.get(TaskId(id))

    taskFuture.map { maybeTaskData =>
      maybeTaskData.map { taskData =>
        createTaskResource(taskData)
      }
    }
  }

  def find(implicit mc: MarkerContext): Future[Iterable[Task]] = {

    taskRepository.list().map { taskDataList =>
      taskDataList.map(taskData => createTaskResource(taskData))
    }
  }

  def remove(id: String)(implicit mc: MarkerContext): Future[String] = {
    taskRepository.remove(TaskId(id)).map { taskData => createTaskResource(taskData).id }
  }

  def update(id: String, taskInput: TaskFormInput)(implicit mc: MarkerContext): Future[Task] = {
    val data = TaskData(TaskId(id), taskInput.title, taskInput.description)
    taskRepository.update(data).map { id => createTaskResource(data) }
  }

  private def createTaskResource(t: TaskData): Task = {
    Task(t.id.toString, t.title, t.description)
  }

}
