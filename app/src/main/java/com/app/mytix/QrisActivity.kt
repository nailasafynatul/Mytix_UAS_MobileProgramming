package com.app.mytix

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class QrisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qris)

        val concertName =
            intent.getStringExtra("concertName") ?: ""

        val totalPrice =
            intent.getIntExtra("totalPrice", 0)

        val paymentMethod =
            intent.getStringExtra("paymentMethod") ?: ""

        findViewById<TextView>(R.id.tvPaymentMethod)
            .text = paymentMethod

        findViewById<TextView>(R.id.tvConcert)
            .text = concertName

        findViewById<TextView>(R.id.tvTotal)
            .text =
            "Rp ${String.format("%,d", totalPrice)}"

        val btnBack =
            findViewById<ImageButton>(R.id.btnBack)

        btnBack.setOnClickListener {
            openTicketPage()
        }

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    openTicketPage()
                }
            }
        )
    }

    private fun openTicketPage() {

        val intent = Intent(this, MainActivity::class.java)

        intent.putExtra("openFragment", "ticket")

        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)
        overridePendingTransition(0, 0)
        finish()
        overridePendingTransition(0, 0)
    }
}