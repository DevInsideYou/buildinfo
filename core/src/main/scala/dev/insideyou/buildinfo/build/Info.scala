package dev.insideyou
package buildinfo
package build

final case class Info(
  appVersion: String,
  scalaVersion: String,
  sbtVersion: String,
  builtAtMillis: Option[Long],
  gitHash: Option[String],
  gitBranch: Option[String],
)
