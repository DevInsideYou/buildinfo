import Dependencies._
import MyUtil._

ThisBuild / organization := "dev.insideyou"
ThisBuild / scalaVersion := "3.3.1"

lazy val buildinfo =
  project
    .in(file("."))
    .settings(commonSettings)
    .aggregate(core, `delivery-cli`, main)

lazy val core =
  project
    .in(file("core"))
    .settings(commonSettings)
    .settings(autoImportSettings)
    .settings(dependencies)

lazy val `delivery-cli` =
  project
    .in(file("delivery-cli"))
    .dependsOn(`core` % Cctt)
    .settings(commonSettings)
    .settings(autoImportSettings)

lazy val main =
  project
    .in(file("main"))
    .dependsOn(`delivery-cli` % Cctt)
    .settings(commonSettings)
    .settings(autoImportSettings)
    .enablePlugins(BuildInfoPlugin)
    .settings(
      buildInfoPackage := "dev.insideyou.buildinfo.build",
      buildInfoKeys :=
        Seq[BuildInfoKey](
          version,
          scalaVersion,
          sbtVersion,
          "gitHash" -> fullGitHash,
          "gitBranch" -> branch,
        ),
      buildInfoOptions ++=
        Seq(
          Some(BuildInfoOption.ToMap),
          if (insideCI.value) Some(BuildInfoOption.BuildTime) else None,
        ).flatten,
    )

lazy val commonSettings = {
  lazy val commonScalacOptions = Seq(
    Compile / console / scalacOptions := {
      (Compile / console / scalacOptions)
        .value
        .filterNot(_.contains("wartremover"))
        .filterNot(Scalac.Lint.toSet)
        .filterNot(Scalac.FatalWarnings.toSet) :+ "-Wconf:any:silent"
    },
    Test / console / scalacOptions :=
      (Compile / console / scalacOptions).value,
  )

  lazy val otherCommonSettings = Seq(
    update / evictionWarningOptions := EvictionWarningOptions.empty
  )

  Seq(
    commonScalacOptions,
    otherCommonSettings,
  ).reduceLeft(_ ++ _)
}

lazy val autoImportSettings = Seq(
  scalacOptions +=
    Seq(
      "java.lang",
      "scala",
      "scala.Predef",
      "scala.annotation",
      "scala.util.chaining",
    ).mkString(start = "-Yimports:", sep = ",", end = ""),
  Test / scalacOptions +=
    Seq(
      "org.scalacheck",
      "org.scalacheck.Prop",
    ).mkString(start = "-Yimports:", sep = ",", end = ""),
)

lazy val dependencies = Seq(
  libraryDependencies ++= Seq(
    // main dependencies
  ),
  libraryDependencies ++= Seq(
    com.eed3si9n.expecty.expecty,
    org.scalacheck.scalacheck,
    org.scalameta.`munit-scalacheck`,
    org.scalameta.munit,
    org.typelevel.`discipline-munit`,
  ).map(_ % Test),
)
