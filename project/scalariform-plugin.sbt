resolvers += Resolver.url( "sbt-plugin-releases", url( "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases" ))( Resolver.ivyStylePatterns )

addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.1.0")
