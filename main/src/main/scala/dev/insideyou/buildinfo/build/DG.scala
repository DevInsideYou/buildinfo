package dev.insideyou
package buildinfo
package build

lazy val make: Controller =
  Controller.make(
    boundary = BusinessLogic.make(
      buildInfoProvider = BuildInfoProviderImpl.make
    )
  )
