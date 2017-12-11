package app

import outwatch.dom._
import outwatch.dom.helpers.InputEvent
import rxscalajs.Observable

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("TutorialApp")
object TutorialApp {

  @JSExport
  def main(args: Array[String]): Unit = {

    val seconds: Observable[Int] = Observable.interval(1000)
      .map(_ + 1)
      .startWith(0)

    val timer = div("Seconds elapsed: ", child <-- seconds)

    val toggleEvents: Handler[InputEvent] = createInputHandler()

    val checkboxe = div(
      label("Hide view"),
      input(tpe := "checkbox", change --> toggleEvents),
      h2(hidden <-- toggleEvents.map(_.target.checked), "Visible!")
    )


    OutWatch.render("#app",
      div(
        h1("TODO"),
        timer,
        checkboxe
      )
    )

  }

}
