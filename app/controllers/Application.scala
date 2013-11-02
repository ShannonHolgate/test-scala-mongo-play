package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._

object Application extends Controller {

  val userForm = Form(
    "username" -> nonEmptyText
  )

  def index = Action {
    Redirect(routes.Application.users)
  }

  def users = Action {
    Ok(views.html.index(User.all(), userForm))
  }

  def newUser = Action { implicit request =>
    userForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(User.all(), errors)),
      username => {
        User.create(username)
        Redirect(routes.Application.users)
      }
    )
  }

  def deleteUser(username: String) = Action {
    User.delete(username)
    Redirect(routes.Application.users)
  }

}