package app

import outwatch.dom._

object StateManagementComponent {

  def apply() = {
    val additions = createHandler[Int]()
    val subtractions = createHandler[Int]()

    val state = subtractions.merge(additions)
      .scan(0)((acc, cur) => acc + cur)

    val root = div(
      button(click(1) --> additions, "+"),
      button(click(-1) --> subtractions, "-"),
      span("Count: ", child <-- state)
    )

    root

  }

}
