package app

import outwatch.dom._
import rxscalajs.Observable

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("TutorialApp")
object TutorialApp {

  @JSExport
  def main(args: Array[String]): Unit = {

    val seconds = Observable.interval(1000)
      .map(_ + 1)
      .startWith(0)

    val timer = div("Seconds elapsed: ", child <-- seconds)

    OutWatch.render("#app",
      div(
        h1("TODO"),
        timer
      )
    )

  }

}
