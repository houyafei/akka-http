name := "akkaOrder"

version := "0.1"

scalaVersion := "2.12.8"

lazy val akkaHttpVersion = "10.1.6"

lazy val akkaVersion = "2.5.19"

libraryDependencies ++= Seq(
//  akka需要的依赖
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-xml" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
//  akka-http用来编解码使用的json依赖
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,



  // akka使用的测试工具包
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)