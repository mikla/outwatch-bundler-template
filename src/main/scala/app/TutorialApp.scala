package app

import outwatch.Sink
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

    val firstNameEvents: Handler[String] = createStringHandler()
    val lastNameEvents: Handler[String] = createStringHandler()

    val firstNames: Observable[String] = firstNameEvents.startWith("")
    val lastNames: Observable[String] = lastNameEvents.startWith("")

    val texts: Sink[String] = createStringHandler()

    val fullNames: Observable[String] = firstNames
      .combineLatestWith(lastNames)((first, last) => s"$first $last")

    val disableEvents = fullNames.map(_.length < 4)

    val names = div(
      input(inputString --> firstNameEvents),
      input(inputString --> lastNameEvents),
      h3("Hello, ", child <-- fullNames),
      button(
        click(fullNames) --> texts,
        disabled <-- disableEvents,
        "Submit"
      )
    )

    val sink: Handler[String] = createStringHandler()

    OutWatch.render("#app",
      div(
        h1("TODO"),
        timer,
        checkboxe,
        names,
        inputComponent("Sink!", sink),
        h3("Hello from Sink", child <-- sink),
        h6("-------- Managing state ---------------"),
        StateManagementComponent()
      )
    )

  }

  def headerComponent(title: String, hideSecond: Boolean): VNode = {
    div(
      h1(title),
      h2(hidden := hideSecond, "This is the second title")
    )
  }

  def inputComponent(labelText: String, textValues: Sink[String]) = {
    div(
      label(labelText),
      input(inputString --> textValues)
    )
  }

}
