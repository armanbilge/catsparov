ThisBuild / tlBaseVersion := "0.0"

ThisBuild / organization := "com.armanbilge"
ThisBuild / organizationName := "Arman Bilge"
ThisBuild / developers += tlGitHubDev("armanbilge", "Arman Bilge")
ThisBuild / startYear := Some(2022)
ThisBuild / tlSonatypeUseLegacyHost := false

ThisBuild / crossScalaVersions := Seq("3.2.0", "2.13.8")

val CatsVersion = "2.8.0"
val TestInterfaceVersion = "1.0"

lazy val root = tlCrossRootProject.aggregate(framework)

lazy val framework = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .in(file("framework"))
  .settings(
    name := "catsparov-framework",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core" % CatsVersion
    )
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      "org.scala-sbt" % "test-interface" % TestInterfaceVersion
    )
  )
  .jsSettings(
    libraryDependencies ++= Seq(
      "org.scala-js" %% "scalajs-test-interface" % scalaJSVersion cross CrossVersion.for3Use2_13
    )
  )
  .nativeSettings(
    libraryDependencies ++= Seq(
      "org.scala-native" %%% "test-interface" % nativeVersion
    )
  )
