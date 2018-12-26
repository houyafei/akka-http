package com.order

import akka.http.scaladsl.server.{HttpApp, Route}
import com.order.models.{GranTotal, Item, Order, OrderJsonSupport}

import scala.util.Random._

object DemoServer extends HttpApp with OrderJsonSupport {


  override protected def routes: Route = {
    pathPrefix("app") {
      path("id" / Segment) {
        id =>
          get {
            println("ok" + id)
            complete("ok")
          } ~
            post {
              entity(as[String]) {
                name => {
                  println(name)
                  complete("ok-post")
                }
              }
            }
      } ~
        path("json") {
          get {
            complete {
              genRandomOrder()
            }
          } ~
            post {
              entity( as [Order]) {
                order =>
                  complete {
                    println(order)
                    calcGrandTotal(order)
                  }
              }
            }
        }
    }
  }

  private def genRandomOrder(): Order = {
    val items = (0 to nextInt(5)).map(i => {
      Item(nextInt(100), "i don't know how to use", List("easy", "ok"), true, Some(0.2f), i * 20.4f)
    }).toList
    Order(nextString(10), System.currentTimeMillis(), items, 100 * nextFloat(), Map("notes" -> "random"))
  }

  private def calcGrandTotal(order: Order): GranTotal = {
    val mount = order.items.map(i => {
      i.percentage.getOrElse(1.0f) * i.price
    }).sum + order.deliveryPrice
    GranTotal(order.id, mount)
  }
}

object DemoServerApp extends App {
  DemoServer.startServer("0.0.0.0", 8099)
}