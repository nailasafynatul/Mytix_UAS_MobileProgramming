package com.app.mytix.data.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.app.mytix.Ticket

class TicketDbHelper(context: Context)
    : SQLiteOpenHelper(
    context,
    "mytix.db",
    null,
    3
) {

    companion object {

        private const val TABLE_TICKET = "ticket"

        private const val COL_ID = "id"
        private const val COL_IMAGE = "image"
        private const val COL_CONCERT = "concert"
        private const val COL_CATEGORY = "category"
        private const val COL_DATE = "date"
        private const val COL_PRICE = "price"
        private const val COL_PAYMENT = "paymentMethod"
        private const val COL_STATUS = "status"
        private const val COL_TIME = "checkoutTime"
    }

    override fun onCreate(db: SQLiteDatabase) {

        val query = """
            CREATE TABLE $TABLE_TICKET(
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_IMAGE INTEGER,
                $COL_CONCERT TEXT,
                $COL_CATEGORY TEXT,
                $COL_DATE TEXT,
                $COL_PRICE TEXT,
                $COL_PAYMENT TEXT,
                $COL_STATUS TEXT,
                $COL_TIME LONG
            )
        """.trimIndent()

        db.execSQL(query)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL(
            "DROP TABLE IF EXISTS $TABLE_TICKET"
        )

        onCreate(db)
    }

    fun insertTicket(ticket: Ticket): Boolean {

        val db = writableDatabase

        val values = ContentValues().apply {

            put(COL_IMAGE, ticket.image)
            put(COL_CONCERT, ticket.concert)
            put(COL_CATEGORY, ticket.category)
            put(COL_DATE, ticket.date)
            put(COL_PRICE, ticket.price)
            put(COL_PAYMENT, ticket.paymentMethod)
            put(COL_STATUS, ticket.status)
            put(COL_TIME, ticket.checkoutTime)
        }

        val result =
            db.insert(TABLE_TICKET, null, values)

        db.close()

        return result != -1L
    }

    fun getAllTickets(): List<Ticket> {

        val list = mutableListOf<Ticket>()

        val db = readableDatabase

        val cursor =
            db.rawQuery(
                "SELECT * FROM ticket ORDER BY id DESC",
                null
            )

        if (cursor.moveToFirst()) {

            do {

                list.add(
                    Ticket(
                        id = cursor.getInt(
                            cursor.getColumnIndexOrThrow(COL_ID)
                        ),
                        image = cursor.getInt(
                            cursor.getColumnIndexOrThrow(COL_IMAGE)
                        ),
                        concert = cursor.getString(
                            cursor.getColumnIndexOrThrow(COL_CONCERT)
                        ),
                        category = cursor.getString(
                            cursor.getColumnIndexOrThrow(COL_CATEGORY)
                        ),
                        date = cursor.getString(
                            cursor.getColumnIndexOrThrow(COL_DATE)
                        ),
                        price = cursor.getString(
                            cursor.getColumnIndexOrThrow(COL_PRICE)
                        ),
                        paymentMethod = cursor.getString(
                            cursor.getColumnIndexOrThrow(COL_PAYMENT)
                        ),
                        status = cursor.getString(
                            cursor.getColumnIndexOrThrow(COL_STATUS)
                        ),
                        checkoutTime = cursor.getLong(
                            cursor.getColumnIndexOrThrow(COL_TIME)
                        )
                    )
                )

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return list
    }

    fun deleteTicket(id: Int): Boolean {

        val db = writableDatabase

        val result =
            db.delete(
                TABLE_TICKET,
                "$COL_ID=?",
                arrayOf(id.toString())
            )

        db.close()

        return result > 0
    }

    fun updatePaymentMethod(
        id: Int,
        paymentMethod: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues().apply {

            put(
                "paymentMethod",
                paymentMethod
            )
        }

        val result =
            db.update(
                "ticket",
                values,
                "id=?",
                arrayOf(id.toString())
            )

        db.close()

        return result > 0
    }

    fun updateStatus(
        id: Int,
        status: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues().apply {

            put(
                COL_STATUS,
                status
            )
        }

        val result =
            db.update(
                TABLE_TICKET,
                values,
                "$COL_ID=?",
                arrayOf(id.toString())
            )

        db.close()

        return result > 0
    }
}