package app

import outwatch.dom._

object TutorialApp {
  def main(args: Array[String]): Unit = {

    OutWatch.render("#app", h1("Hello - World"))

  }
}
