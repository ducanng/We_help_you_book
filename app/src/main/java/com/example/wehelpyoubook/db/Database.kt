package com.example.wehelpyoubook.db

import android.os.StrictMode
import android.util.Log
import java.sql.*


class Database {
    private var ip = "192.168.100.9"
    private val port = "1433"
    private val classes = "net.sourceforge.jtds.jdbc.Driver"
    private val database = "test"
    private val username = "sa"
    private val password = "svcntt"
    private val url = "jdbc:jtds:sqlserver://$ip:$port/$database"
    private var connection: Connection? = null
    private fun connectDB(): Connection? {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        try {
            Class.forName(classes)
            connection = DriverManager.getConnection(url, username, password)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            Log.e("Error from class", e.message!!)
        } catch (e: SQLException) {
            Log.e("Error from sqlexception", e.message!!)
            e.printStackTrace()
        } catch (e: Exception) {
            Log.e("Error from exception : ", e.message!!)
            e.printStackTrace()
        }
        return connection
    }
    fun isCheckExist(table: String, column: String ,value: String):Boolean {
        try {
            val con: Connection? = this.connectDB()
            if (con == null) {
                Log.e("Error", "Can't connect database")
            } else {
                val query = "SELECT * FROM $table WHERE $column='$value'"
                val ps: Statement = con.createStatement()
                val rs: ResultSet = ps.executeQuery(query)
                if (rs.next()) {
                    if (value == rs.getString(column)) {
                        return true
                    }
                }
                con.close()
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return false
    }
    fun insert(table: String, column: List<String> = listOf(), values: List<String> = listOf()):Boolean{
        try {
            val connection: Connection? = this.connectDB()
            var query = "INSERT INTO $table(" //account(so_dien_thoai) VALUES ('$phone')"
            for (i in column.indices) {
                if (i == column.size - 1) {
                    query += column[i] + ")"
                    break
                }
                query += column[i] + ","
            }
            query += "VALUES ("
            for (i in values.indices) {
                if (i == values.size - 1) {
                    query += "'" + values[i] + "')"
                    break
                }
                query += "'" + values[i] + "',"
            }
            if (connection == null) {
                return false
            } else  {
                val stmt: Statement = connection.createStatement()
                stmt.executeUpdate(query)
            }
        } catch (se: SQLException) {
            Log.e("Error from SQLException", se.message!!)
        }
        return true
    }
}
