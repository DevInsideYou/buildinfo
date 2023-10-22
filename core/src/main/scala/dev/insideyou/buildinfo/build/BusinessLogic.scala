package dev.insideyou
package buildinfo
package build

object BusinessLogic:
  def make(dependencies: Dependencies): Boundary =
    new:
      override lazy val info: Info =
        dependencies.info

  def make(buildInfoProvider: BuildInfoProvider): Boundary =
    make:
      new Dependencies:
        export buildInfoProvider.*
