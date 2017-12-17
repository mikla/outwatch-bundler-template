package app.todo

import outwatch.Sink
import outwatch.dom._

object TodoPage {

  def apply() = {
    div(
      textFieldComponent(addEvents),
      ul(children <-- listViews)
    )
  }

  val addEvents = createStringHandler()
  val deleteEvents = createStringHandler()

  def addToList(todo: String) = {
    (list: List[String]) => list :+ todo
  }

  def removeFromList(todo: String) = {
    (list: List[String]) => list.filterNot(_ == todo)
  }

  val additions = addEvents.map(addToList _)
  val deletions = deleteEvents.map(removeFromList _)

  val merged = additions.merge(deletions)

  val state = additions.merge(deletions)
    .scan(List.empty[String])((list, fn) => fn.apply(list))

  val listViews = state
    .map(_.map(todo => todoComponent(todo, deleteEvents)))

  private def textFieldComponent(outputEvents: Sink[String]): VNode = {
    val textValues = createStringHandler()

    val disabledValues = textValues
      .map(_.length < 4)
      .startWith(true)

    div(
      label("Todo: "),
      input(inputString --> textValues),
      button(
        click(textValues) --> outputEvents,
        disabled <-- disabledValues,
        "Submit"
      )
    )
  }

  private def todoComponent(todo: String, deleteEvents: Sink[String]) = {
    li(
      span(todo),
      button(click(todo) --> deleteEvents, "Delete")
    )
  }

}
