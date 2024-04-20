import com.softwaremill.SbtSoftwareMillCommon.commonSmlBuildSettings

lazy val commonSettings = commonSmlBuildSettings ++ Seq(
  organization := "org.warski.website",
  scalaVersion := "3.3.3"
)

val jsoniterVersion = "2.26.2"
val scalaTest = "org.scalatest" %% "scalatest" % "3.2.18" % Test

lazy val rootProject = (project in file("."))
  .settings(commonSettings: _*)
  .settings(publishArtifact := false, name := "website")
  .aggregate(datagen)

lazy val datagen: Project = (project in file("datagen"))
  .settings(commonSettings: _*)
  .settings(
    name := "datagen",
    libraryDependencies ++= Seq(
      "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % jsoniterVersion,
      "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % jsoniterVersion % "provided",
      "com.softwaremill.sttp.client4" %% "core" % "4.0.0-M12",
      "com.lihaoyi" %% "os-lib" % "0.9.3",
      "net.ruippeixotog" %% "scala-scraper" % "3.1.1",
      scalaTest
    )
  )
