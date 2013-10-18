
import scalariform.formatter.preferences._

name := "SoftShake"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.2"

scalacOptions += "-feature"

resolvers ++= Dependencies.resolvers

libraryDependencies ++= Dependencies.deps

scalariformSettings

ScalariformKeys.preferences := FormattingPreferences()
  .setPreference(AlignParameters, true)
  .setPreference( RewriteArrowSymbols,true)
  .setPreference(PreserveDanglingCloseParenthesis, false)

seq(webSettings :_*)
