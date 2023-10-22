package dev.insideyou
package buildinfo
package build

import java.time.*

object Controller:
  def make(boundary: Boundary): Controller =
    new:
      override def run(): Unit =
        println(boundary.info.rendered)

  extension (info: Info)
    private def rendered: String =
      import info.*

      val builtAt =
        builtAtMillis.map: millis =>
          LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.of("UTC"))

      def g(in: Option[String]): String =
        in.getOrElse("not a git repo")

      s"""|appVersion: $appVersion
          |scalaVersion: $scalaVersion
          |sbtVersion: $sbtVersion
          |builtAt: ${builtAt.getOrElse("unavailable")}
          |gitHash: ${g(gitHash)}
          |gitBranch: ${g(gitBranch)}""".stripMargin
