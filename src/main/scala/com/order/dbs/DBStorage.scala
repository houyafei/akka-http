package com.order.dbs


import java.util.Date

import com.order.models.Task

object DBStorage {

  def main(args: Array[String]): Unit = {
    createTable(null)
    insert(Task(12, "good good study", 12, new Date()))


  }


  private def createTable(createTableSql: String) = {
    val conn = JDBCConnectionUtil.getConnection()
    if (conn != null) {
      val stat = JDBCConnectionUtil.getConnection().createStatement()
      val sql = if (createTableSql != null) createTableSql else
        "create table  if not exists task (" +
          "ID integer primary key  autoincrement, " +
          "task text not null, " +
          "status int ," +
          "create_time text)"
      val result = stat.execute(sql)
      println(result)
      stat.close()
      conn.close()
    }
  }

  def insert(task: Task) = {
    val conn = JDBCConnectionUtil.getConnection()
    if (conn != null) {
      val sql =
        "insert into task(task,status,create_time) values(?,?,?)"
      val stat = conn.prepareStatement(sql)
      stat.setString(1, task.task)
      stat.setInt(2, task.status)
      stat.setString(3, task.createTime.toString)
      stat.execute()
      stat.close()
      conn.close()
    }
  }

}
