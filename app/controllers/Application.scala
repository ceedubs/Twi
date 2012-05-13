package controllers

import java.util.Date

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

import anorm._

import models.Twee

object Application extends Controller {

  val tweeForm: Form[Twee] = Form(
    mapping(
      "id" -> ignored(NotAssigned: Pk[Long]),
      "created" -> ignored(new Date()),
      "message" -> text
    )(Twee.apply)(Twee.unapply)
  )
  
  def index = Action {
    Ok(views.html.index(
      tweeForm,
      Twee.all
    ))
  }

  def createTwee() = Action { implicit request =>
    tweeForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.index(tweeForm, Twee.all)),
      twee => {
        Twee.create(twee)
        Redirect(routes.Application.index)
      }
    )
  }
  
}
