package models

import play.api.db._
import play.api.Play.current
import anorm._

import java.util.Date

case class Twee(id: Pk[Long] = NotAssigned, created: Date = new Date(), message: String)

object Twee {
  def all: Seq[Twee] = {
    DB.withConnection {implicit connection =>
      SQL("select id, created, message from Twee")().map {row =>
        new Twee(row[Pk[Long]]("id"), row[Date]("created"), row[String]("message"))
      }
    }
  }
}
