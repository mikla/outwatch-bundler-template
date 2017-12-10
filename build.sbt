name := "scala-js-outwatch-bundler"

version := "0.1"

scalaVersion := "2.12.4"

enablePlugins(ScalaJSPlugin)
enablePlugins(ScalaJSBundlerPlugin)

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

//npmDependencies in Compile += "snabbdom" -> "0.5.3"

libraryDependencies += "io.github.outwatch" %%% "outwatch" % "0.10.2"

webpackDevServerExtraArgs := Seq("--inline", "--content-base=../../")

//webpackMonitoredFiles += baseDirectory.value / "src" / "main" /  "resources" / "index.html"

webpackMonitoredDirectories += baseDirectory.value / "src/main/resources"