package dev.insideyou
package buildinfo
package build

type Dependencies = BuildInfoProvider

trait BuildInfoProvider:
  def info: Info
