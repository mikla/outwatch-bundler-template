package app

import app.http.HttpApp
import app.todo.TodoPage
import outwatch.dom._

object TutorialApp {
  def main(args: Array[String]): Unit = {

    OutWatch.render("#app", div(
      h1("Todo component"),
      TodoPage(),
      h1("Http app"),
      HttpApp()
    ))

  }
}
