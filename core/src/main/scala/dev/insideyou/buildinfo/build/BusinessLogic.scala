package dev.insideyou
package buildinfo
package build

object BusinessLogic:
  def make(dependencies: Dependencies): Boundary =
    new:
      override lazy val info: Info =
        dependencies.info.tap(log)

      private def log(info: Info): Unit =
        println:
          s"Retrieved: ${Console.GREEN}$info${Console.RESET}"
