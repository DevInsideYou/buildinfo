package dev.insideyou
package buildinfo
package build

object BuildInfoProviderImpl:
  lazy val make: BuildInfoProvider =
    new:
      override lazy val info: Info =
        Info(
          appVersion = BuildInfo.version,
          scalaVersion = BuildInfo.scalaVersion,
          sbtVersion = BuildInfo.sbtVersion,
          builtAtMillis = BuildInfo.toMap.get("builtAtMillis").map(_.asInstanceOf[Long]),
          gitHash = BuildInfo.gitHash,
          gitBranch = BuildInfo.gitBranch,
        )
