package com.app.mytix

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mytix.api.PriceResponse
import com.app.mytix.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var recyclerConcert: RecyclerView
    private lateinit var concertList: ArrayList<Concert>

    private lateinit var networkReceiver: NetworkReceiver

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_home,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        networkReceiver = NetworkReceiver()

        requireContext().registerReceiver(
            networkReceiver,
            IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        )

        recyclerConcert =
            view.findViewById(R.id.recyclerConcert)

        concertList = arrayListOf(

            Concert(
                R.drawable.img_amoral,
                "PAGELARAN AMORAL",
                "Mulai Rp 50k"
            ),

            Concert(
                R.drawable.img_pankreas,
                "STUDI PENTAS PANKREAS",
                "Mulai Rp 40k"
            ),

            Concert(
                R.drawable.img_glovery,
                "SEMINAR GLOVERY 2026",
                "Mulai Rp 35k"
            ),

            Concert(
                R.drawable.img_candrakarsa,
                "CANDRAKARSA",
                "Mulai Rp 55k"
            )
        )

        recyclerConcert.layoutManager =
            GridLayoutManager(requireContext(), 2)

        recyclerConcert.adapter =
            ConcertAdapter(concertList)

        RetrofitClient.apiService.getPrices()
            .enqueue(object : Callback<PriceResponse> {

                override fun onResponse(
                    call: Call<PriceResponse>,
                    response: Response<PriceResponse>
                ) {

                    if (response.isSuccessful) {

                        concertList.clear()

                        response.body()?.concerts?.forEach {

                            Log.d(
                                "API_TEST",
                                "${it.name} = ${it.price}"
                            )

                            val imageRes = when (it.name) {

                                "PAGELARAN AMORAL" ->
                                    R.drawable.img_amoral

                                "STUDI PENTAS PANKREAS" ->
                                    R.drawable.img_pankreas

                                "SEMINAR GLOVERY 2026" ->
                                    R.drawable.img_glovery

                                "CANDRAKARSA" ->
                                    R.drawable.img_candrakarsa

                                else ->
                                    R.drawable.img_amoral
                            }

                            concertList.add(
                                Concert(
                                    imageRes,
                                    it.name,
                                    it.price
                                )
                            )
                        }

                        recyclerConcert.adapter =
                            ConcertAdapter(concertList)
                    }

                }

                override fun onFailure(
                    call: Call<PriceResponse>,
                    t: Throwable
                ) {
                    Log.e("API_TEST", t.message ?: "Unknown error")
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        try {
            requireContext().unregisterReceiver(networkReceiver)
        } catch (_: Exception) {
        }
    }
}