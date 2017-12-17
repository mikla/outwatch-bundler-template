package app.http

import outwatch.dom._
import outwatch.http.Http
import rxscalajs.dom.Request

import scala.concurrent.duration._

object HttpApp {

  def apply() = {
    val root = div(
      input(inputString --> queries),
      div(child <-- responses)
    )
    root
  }

  val queries = createStringHandler()


  val requests = queries
    .map(query =>
      Request(
        s"https://www.google.lv/search?q=$query",
        headers = Map("Access-Control-Allow-Origin" -> "*")
      )
    )
    .debounceTime(300.millis)
    .retry(4)

  val responses = Http.getWithBody(requests)
    .map(_.body)


}
