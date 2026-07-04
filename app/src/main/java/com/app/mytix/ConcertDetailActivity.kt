package com.app.mytix

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.app.mytix.data.ConcertRepository

private var ticketPrice = 50000

class ConcertDetailActivity : AppCompatActivity() {

    private var quantity = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concert_detail)

        val btnBack =
            findViewById<ImageButton>(R.id.btnBack)

        btnBack.setOnClickListener {
            openHomePage()
        }

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    openHomePage()
                }
            }
        )

        val id = intent.getIntExtra("ID", -1)
        val data = ConcertRepository.getConcertById(id)

        val img = findViewById<ImageView>(R.id.imgDetail)
        val name = findViewById<TextView>(R.id.tvName)
        val tvPrice = findViewById<TextView>(R.id.tvPrice)
        val desc = findViewById<TextView>(R.id.tvDesc)
        val location = findViewById<TextView>(R.id.tvLocation)
        val date = findViewById<TextView>(R.id.tvDate)

        val tvQuantity =
            findViewById<TextView>(R.id.tvQuantity)

        val tvTotalPrice =
            findViewById<TextView>(R.id.tvTotalPrice)

        val btnPlus =
            findViewById<Button>(R.id.btnPlus)

        val btnMinus =
            findViewById<Button>(R.id.btnMinus)

        val btnAddToCart =
            findViewById<Button>(R.id.btnAddToCart)

        if (data != null) {

            img.setImageResource(data.imageResId)
            name.text = data.name
            desc.text = data.description

            location.text = "📍 ${data.location}"
            date.text = "🕒 ${data.date}"

            when (data.name) {

                "PAGELARAN AMORAL" -> {
                    tvPrice.text = "Harga : Rp 50k"
                    ticketPrice = 50000
                }

                "STUDI PENTAS PANKREAS" -> {
                    tvPrice.text = "Harga : Rp 40k"
                    ticketPrice = 40000
                }

                "SEMINAR GLOVERY 2026" -> {
                    tvPrice.text = "Harga : Rp 35k"
                    ticketPrice = 35000
                }

                "CANDRAKARSA" -> {
                    tvPrice.text = "Harga : Rp 55k"
                    ticketPrice = 55000
                }
            }
        }

        location.setOnClickListener {

            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=${data?.location}")
            )

            startActivity(intent)
        }

        updateTotal(tvTotalPrice)

        btnPlus.setOnClickListener {

            quantity++
            tvQuantity.text = quantity.toString()

            updateTotal(tvTotalPrice)
        }

        btnMinus.setOnClickListener {

            if (quantity > 1) {

                quantity--
                tvQuantity.text = quantity.toString()

                updateTotal(tvTotalPrice)
            }
        }

        btnAddToCart.setOnClickListener {

            val total =
                ticketPrice * quantity

            val intent =
                Intent(
                    this,
                    PaymentActivity::class.java
                )

            intent.putExtra(
                "concertName",
                data?.name
            )

            intent.putExtra(
                "concertDate",
                data?.date
            )

            intent.putExtra(
                "concertImage",
                data?.imageResId ?: 0
            )

            intent.putExtra(
                "quantity",
                quantity
            )

            intent.putExtra(
                "totalPrice",
                total
            )

            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }

    private fun updateTotal(
        tvTotalPrice: TextView
    ) {

        val total =
            ticketPrice * quantity

        tvTotalPrice.text =
            "Total: Rp ${String.format("%,d", total)}"
    }

    private fun openHomePage() {
        finish()
        overridePendingTransition(0, 0)
    }
}