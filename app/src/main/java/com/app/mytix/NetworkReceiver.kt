package com.app.mytix

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class NetworkReceiver : BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {

        val connectivityManager =
            context.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager

        val network =
            connectivityManager.activeNetworkInfo

        if (network != null && network.isConnected) {

            Toast.makeText(
                context,
                "Internet terhubung",
                Toast.LENGTH_SHORT
            ).show()

        } else {

            Toast.makeText(
                context,
                "Internet terputus",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}