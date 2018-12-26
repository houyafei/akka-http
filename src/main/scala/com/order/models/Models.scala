package com.order.models

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol


case class Item(id: Int,
                description: String,
                tags: List[String],
                isVip: Boolean,
                percentage: Option[Float],
                price: Float)

case class Order(id: String,
                 timeStamp: Long,
                 items: List[Item],
                 deliveryPrice: Float,
                 metaData: Map[String, String])

case class GranTotal(id: String, amount: Float)

trait OrderJsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val itemFormat = jsonFormat6(Item)
  implicit val orderFormat = jsonFormat5(Order)
  implicit val granTotal = jsonFormat2(GranTotal)
}