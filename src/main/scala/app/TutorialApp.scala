package app

import outwatch.dom._

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("TreevioWebUI")
object TutorialApp {

  @JSExport
  def main(args: Array[String]): Unit = {
    OutWatch.render("#app", h1("TODO"))
  }

}
