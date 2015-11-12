version       := "SNAPSHOT"

scalaVersion  := "2.11.6"

resolvers ++= Seq(
  "Sonatype Nexus Releases" at "https://oss.sonatype.org/content/repositories/releases",
  "Sonatype Nexus Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "Typesafe" at "http://repo.typesafe.com/typesafe/releases",
  "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"
)

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "com.typesafe.slick"  %%  "slick"           % "3.1.0-RC2",
    "org.xerial"          %   "sqlite-jdbc"       % "3.7.2",
    "com.zaxxer"          %   "HikariCP"    % "2.4.1"
  )
}
