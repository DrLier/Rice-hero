package com.example.capstone.data

import com.example.capstone.R
import com.example.capstone.model.PlantDisease

object ListPlantDisease {
    private val dataPlantDisease = arrayOf(
        arrayOf(
            "Hawar Daun Bakteri",
            "Bakteri",
            R.drawable.pic_hawar_daun_bakteri,
            "- Luka pada tepi daun yang berair\n" +
                    "- Daun mengering keriput dengan warna hijau keputihan\n" +
                    "- Cairan bakteri berwarna putih menetes dari daun\n" +
            "Gejala awal yang disebabkan adalah berubahnya warna daun dan mengering keriput. Luka pada tepi daun yang berarir muncul pertama kali pada daun. Luka ini akan membentuk luka yang semakin besar apabila dibiarkan. Pada tahap akhir penyakit, cairan bakteri yang berwarna putih layaknya susu akan menetes dari daun, dimana tetesan ini akan meninggalkan kerak putih.",
            "Penyakit ini disebabkan oleh bakteri Xanthomonas campestris pv. Oryzae yang dapat bertahan hidup di gulma. Bakteri dapat menyerang pada luka daun, dipicu penggunaan pupuk N berlebihan, kurangnya pupuk kalium, dan kelembaban tinggi. Semakin lama penyakit menyerang tanaman, semakin tinggi kerugian panen yang disebabkan. Ketika tanaman padi terinfeksi, sebagian besar bulir padi akan menjadi rusak. Penyakit ini biasanya terjadi di lingkungan beriklim tropis.",
            "Serangan yang terjadi pada tanaman padi akan menyebabkan tanaman menjadi layu dan mati.",
            "Pencegahan dan Penanganan : \n" +
                    "- Gunakan benih yang sehat dan bersertifikat\n" +
                    "- Menanam varietas yang tahan penyakit\n" +
                    "- Cek dan pastikan drainase dalam kondisi yang baik\n" +
                    "- Atur pemberian pupuk N supaya tidak berlebihan dan memunculkan penyakit\n" +
                    "- Singkirkan gulma dan inang dari saluran air dan lingkungan sekitar\n" +
                    "- Mengurangi genangan air\n" +
                    "- Menambah Dolomit\n"
        ),
    )

    val listDataPlantDisease : ArrayList<PlantDisease>
    get() {
        val list = ArrayList<PlantDisease>()
        for (data in dataPlantDisease) {
            val plantDisease = PlantDisease(data[0].toString(), data[1].toString(), data[2].hashCode(), data[3].toString(), data[4].toString(),
                data[5].toString(), data[6].toString())
            list.add(plantDisease)
        }
        return list
    }
}