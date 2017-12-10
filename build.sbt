name := "scala-js-outwatch-bundler"

version := "0.1"

scalaVersion := "2.12.4"

enablePlugins(ScalaJSPlugin)
enablePlugins(ScalaJSBundlerPlugin)
enablePlugins(WorkbenchPlugin)

libraryDependencies += "io.github.outwatch" %%% "outwatch" % "0.10.2"

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

workbenchDefaultRootObject := Some(("target/scala-2.12/classes/index.html", "target/scala-2.12/"))