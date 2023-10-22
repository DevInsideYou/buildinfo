package dev.insideyou
package buildinfo
package build

trait Dependencies extends BuildInfoProvider

trait BuildInfoProvider:
  def info: Info
