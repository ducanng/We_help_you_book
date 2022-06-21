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
    fun connectDB(): Connection? {
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
    fun isCheckExistPhoneNumber(phone : String, connection : Connection):Boolean {
        var statement: Statement? = null
        try {
            statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT so_dien_thoai FROM test WHERE so_dien_thoai = $phone;")
            if (resultSet.next()) {
                return true
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return false
    }
    //0 : table
    //
//    fun Insert( column: List<String> = listOf<String>(), values: List<String> = listOf<String>(), connection: Connection){
//        try {
//            for (i in column.indices) {
//
//            }
//            val query = "INSERT INTO account(so_dien_thoai) VALUES ('$phone')"
//            val stmt: Statement = connection.createStatement()
//            stmt.executeUpdate(query)
//        } catch (se: SQLException) {
//            Log.e("Error from SQLException", se.message!!)
//        }
//    }
}