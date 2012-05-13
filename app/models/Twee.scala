package models

import java.util.Date

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._


case class Twee(id: Pk[Long] = NotAssigned, created: Date = new Date(), message: String)

object Twee {
  def all: Seq[Twee] = {
    DB.withConnection {implicit connection =>
      // the below mapping creates a lazy row parser
      SQL("select id, created, message from Twee order by created desc")().map {row =>
        new Twee(row[Pk[Long]]("id"), row[Date]("created"), row[String]("message"))
      }.toList // we convert the lazy row parser to a list so the DB connection closes
    }
  }

  def create(twee: Twee): Twee = {
    DB.withConnection { implicit connection =>
      val id: Long = twee.id.getOrElse {
          SQL("select next value for twee_sequence").as(scalar[Long].single)

      }

      SQL(
        """insert into Twee (id, created, message) values (
            {id}, {created}, {message})"""
      ).on(
        'id -> id,
        'created -> twee.created,
        'message -> twee.message
      ).executeUpdate()

      twee.copy(id = Id(id))
    }
  }
}
