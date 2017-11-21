package repository

import javax.inject.{ Inject, Singleton }
import akka.actor.ActorSystem
import play.api.{ Logger, MarkerContext }
import play.api.libs.concurrent.CustomExecutionContext
import scala.concurrent.Future

final case class TaskData(id: TaskId, title: String, description: String)

/**
 * Create representation class for Task ID and extended of Abstract AnyVal
 * @param underlying
 */
class TaskId private (val underlying: Int) extends AnyVal {
  override def toString: String = underlying.toString
}

/**
 *
 */
object TaskId {
  def apply(raw: String): TaskId = {
    require(raw != null)
    new TaskId(Integer.parseInt(raw))
  }
}

class TaskExecutionContext @Inject() (actorSystem: ActorSystem) extends CustomExecutionContext(actorSystem, "repository.dispatcher")

/**
 * A pure non-blocking interface for the PostRepository.
 */
trait TaskRepository {
  def create(data: TaskData)(implicit mc: MarkerContext): Future[TaskId]

  def list()(implicit mc: MarkerContext): Future[Iterable[TaskData]]

  def remove(id: TaskId)(implicit mc: MarkerContext): Future[TaskData]

  def update(data: TaskData)(implicit mc: MarkerContext): Future[TaskId]

  def get(id: TaskId)(implicit mc: MarkerContext): Future[Option[TaskData]]
}

/**
 * A trivial implementation for the Task Repository.
 *
 * A custom execution context is used here to establish that blocking operations should be
 * executed in a different thread than Play's ExecutionContext, which is used for CPU bound tasks
 * such as rendering.
 */
@Singleton
class TaskRepositoryImpl @Inject() ()(implicit ec: TaskExecutionContext) extends TaskRepository {

  private val logger = Logger(this.getClass)

  private val tasks = scala.collection.mutable.ArrayBuffer(TaskData(TaskId("1"), "Estudar Scala", "Estudar a sintaxe e OO com ela"))

  override def list()(implicit mc: MarkerContext): Future[Iterable[TaskData]] = {
    Future {
      logger.trace(s"list: ")
      tasks
    }
  }

  override def get(id: TaskId)(implicit mc: MarkerContext): Future[Option[TaskData]] = {

    Future {
      logger.trace(s"get: id = $id")
      tasks.find(task => task.id == id)
    }
  }

  override def remove(id: TaskId)(implicit mc: MarkerContext): Future[TaskData] = {
    val task = tasks.find(task => task.id == id).get
    tasks -= task

    Future {
      logger.trace(s"removed: id = $id")
      task
    }
  }

  override def update(data: TaskData)(implicit mc: MarkerContext): Future[TaskId] = {
    
    for (y <- 0 until tasks.length) {
      if (tasks(y).id == data.id)
        tasks(y) = data
    }

    Future {
      logger.trace(s"update: data = $data")
      data.id
    }
  }

  def create(data: TaskData)(implicit mc: MarkerContext): Future[TaskId] = {
    tasks += TaskData(data.id, data.title, data.description)

    Future {
      logger.trace(s"create: data = $data")
      data.id
    }
  }

}
