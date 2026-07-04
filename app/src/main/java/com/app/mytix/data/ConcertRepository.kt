package com.app.mytix.data

import com.app.mytix.R
import com.app.mytix.model.Concert

object ConcertRepository {

    fun getConcertById(id: Int): Concert? {
        return when (id) {
            1 -> Concert(
                1,
                "PAGELARAN 2026: AMORAL",
                "Saksikan perjalanan menegangkan menembus belantara amoralitas, saat secercah kebenaran menjadi nyala terakhir harapan.",
                "Concert Hall Taman Budaya Yogyakarta, Kota Yogyakarta, Daerah Istimewa Yogyakarta",
                "06 Juli 2026 17:00 WIB",
                R.drawable.img_amoral
            )
            2 -> Concert(
                2,
                "STUDI PENTAS PANKREAS",
                "STUPEN (Studi Pentas) adalah acara tahunan yang diselenggarakan oleh UKM PANKREAS PNJ sebagai wadah bagi calon anggota UKM PANKREAS untuk menampilkan kreativitas, bakat, dan ekspresi diri melalui berbagai bentuk seni pertunjukan.",
                "Auditorium PNJ, Kota Depok, Jawa Barat",
                "20 Juni 2026 09:00 WIB",
                R.drawable.img_pankreas
            )

            3 -> Concert(
                3,
                "SEMINAR GLOVERY 2026",
                "Glovery (Glory Event For Nutrition Anniversary) merupakan perayaan Dies Natalis Jurusan Gizi Poltekkes Kemenkes Surabaya.",
                "AUDITORIUM POLTEKKES KEMENKES SURABAYA, Kota Surabaya, Jawa Timur",
                "11 Mei 2026 07:30 WIB",
                R.drawable.img_glovery
            )

            4 -> Concert(
                4,
                "CANDRAKRASA",
                "Berpadunya antara sebuah pentas dan seni, lahirlah kebebasan berekspresi dalam Pentas Seni \"Candrakarsa\" yang menghadirkan guest star, band lokal, dan juga penampilan bakat murid.",
                "Gymnasium Sekolah Vokasi IPB, Kota Bogor, Jawa Barat",
                "23 Agustus 2026 12:00 WIB",
                R.drawable.img_candrakarsa
            )

            else -> null
        }
    }
}