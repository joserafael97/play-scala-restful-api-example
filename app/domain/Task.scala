package domain

import play.api.libs.json.Writes
import play.api.libs.json.JsValue
import play.api.libs.json.Json

case class Task(id: String, title: String, description: String)

object Task {

  /**
   * Mapping to write a PostResource out as a JSON value.
   */
  implicit val implicitWrites = new Writes[Task] {
    def writes(task: Task): JsValue = {
      Json.obj(
        "id" -> task.id,
        "title" -> task.title,
        "description" -> task.description)
    }
  }
}