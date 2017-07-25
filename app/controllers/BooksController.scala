package controllers

import javax.inject.Inject

import models.Book
import play.api.data.Form
import play.api.data.Forms._
//import play.api.data._
import play.api.mvc._
import repository.BookRepository

import scala.concurrent.ExecutionContext.Implicits.global

class BooksController @Inject()(cc: ControllerComponents, bookRepository: BookRepository)
                      extends AbstractController(cc) with play.api.i18n.I18nSupport {

  def getAll() = Action.async {
    bookRepository.getAll().map(books => Ok(views.html.books(books)))
  }

  def getBookById(id: Int) = Action.async {
    bookRepository.getSingle(id).map {
      case Some(book) => Ok(views.html.books(Seq(book)))
      case None => NotFound(s"There is no book with ID = $id")
    }
  }

  def delete(id: Int) = TODO

  def edit(book: Book) = TODO

  def addNewBook(book: Book) = TODO

  val bookForm = Form(
    mapping(
      "Id" -> number,
      "Title" -> nonEmptyText,
      "Author" -> nonEmptyText
    )(Book.apply)(Book.unapply))
//
//
//  def index() = Action { implicit request =>
//    Ok(views.html.newBook(bookForm))
//  }
//
//  def addNewBook = Action { implicit request =>
//    bookForm.bindFromRequest().fold(
//      formWithErrors => BadRequest,
//      bookData => {
//        /* binding success, you get the actual value. */
//        val newBook = models.Book(-1, bookData.title, bookData.author)
//        Redirect(routes.BooksController.getAll())
//      }
//    )
//  }
//    Redirect(routes.BooksController.getAll())
//  }

}
