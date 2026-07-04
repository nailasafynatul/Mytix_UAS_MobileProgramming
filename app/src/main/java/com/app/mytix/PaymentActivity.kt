package com.app.mytix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.mytix.data.local.TicketDbHelper
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback

class PaymentActivity : AppCompatActivity() {

    private lateinit var dbHelper: TicketDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val btnBack =
            findViewById<ImageButton>(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
            overridePendingTransition(0, 0)
        }

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    finish()
                    overridePendingTransition(0, 0)
                }
            }
        )

        dbHelper = TicketDbHelper(this)

        val concertName =
            intent.getStringExtra("concertName") ?: ""

        val concertDate =
            intent.getStringExtra("concertDate") ?: ""

        val concertImage =
            intent.getIntExtra("concertImage", 0)

        val category =
            intent.getStringExtra("category") ?: ""

        val quantity =
            intent.getIntExtra("quantity", 1)

        val totalPrice =
            intent.getIntExtra("totalPrice", 0)

        val imgConcert =
            findViewById<ImageView>(R.id.imgConcert)

        imgConcert.setImageResource(concertImage)

        findViewById<TextView>(R.id.tvConcertName)
            .text = concertName

        findViewById<TextView>(R.id.tvQuantity)
            .text = "Jumlah : $quantity Tiket"

        findViewById<TextView>(R.id.tvTotal)
            .text = "Rp ${String.format("%,d", totalPrice)}"

        val radioGroup =
            findViewById<RadioGroup>(R.id.radioGroupPayment)

        val btnOrder =
            findViewById<Button>(R.id.btnOrderNow)

        btnOrder.setOnClickListener {

            if (radioGroup.checkedRadioButtonId == -1) {

                Toast.makeText(
                    this,
                    "Pilih metode pembayaran",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val paymentMethod = when (
                radioGroup.checkedRadioButtonId
            ) {
                R.id.rbDana -> "Dana"
                R.id.rbShopeePay -> "ShopeePay"
                R.id.rbBca -> "BCA VA"
                else -> "QRIS"
            }

            val ticket = Ticket(
                image = concertImage,
                concert = concertName,
                category = "$category • $quantity Tiket",
                date = concertDate,
                price = "Rp ${String.format("%,d", totalPrice)}",
                paymentMethod = paymentMethod,
                status = "Menunggu Pembayaran",
                checkoutTime = System.currentTimeMillis()
            )

            dbHelper.insertTicket(ticket)

            val intent =
                Intent(
                    this,
                    QrisActivity::class.java
                )

            intent.putExtra(
                "paymentMethod",
                paymentMethod
            )

            intent.putExtra(
                "concertName",
                concertName
            )

            intent.putExtra(
                "totalPrice",
                totalPrice
            )

            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }
}