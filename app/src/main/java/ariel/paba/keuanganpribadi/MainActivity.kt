package ariel.paba.keuanganpribadi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var etNamaKategori: EditText
    private lateinit var rgJenisKategori: RadioGroup
    private lateinit var btnTambahKategori: Button
    private lateinit var rvKategori: RecyclerView
    private val daftarKategori = ArrayList<Kategori>()
    private lateinit var adapter: KategoriAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etNamaKategori = findViewById(R.id.etNamaKategori)
        rgJenisKategori = findViewById(R.id.rgJenisKategori)
        btnTambahKategori = findViewById(R.id.btnTambahKategori)
        rvKategori = findViewById(R.id.rvKategori)
        adapter = KategoriAdapter(daftarKategori)
        rvKategori.layoutManager = LinearLayoutManager(this)
        rvKategori.adapter = adapter
        btnTambahKategori.setOnClickListener {
            tambahKategori()
        }
    }
    private fun tambahKategori() {
        val nama = etNamaKategori.text.toString().trim()

        if (nama.isEmpty()) {
            Toast.makeText(this, "Nama kategori harus diisi", Toast.LENGTH_SHORT).show()
            return
        }
        val jenis = when (rgJenisKategori.checkedRadioButtonId) {
            R.id.rbPemasukan -> "Pemasukan"
            R.id.rbPengeluaran -> "Pengeluaran"
            else -> ""
        }
        if (jenis.isEmpty()) {
            Toast.makeText(this, "Pilih jenis kategori", Toast.LENGTH_SHORT).show()
            return
        }
        daftarKategori.add(Kategori(nama, jenis))
        adapter.notifyItemInserted(daftarKategori.size - 1)

        etNamaKategori.text.clear()
        Toast.makeText(this, "Kategori berhasil ditambahkan", Toast.LENGTH_SHORT).show()
    }
}