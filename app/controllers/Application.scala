package controllers

import play.api._
import play.api.mvc._
import models.Twee

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index(
      "Your new application is ready.",
      Twee.all.map{ _.message } 
    ))
  }
  
}
