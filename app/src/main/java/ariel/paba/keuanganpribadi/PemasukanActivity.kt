package ariel.paba.keuanganpribadi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PemasukanActivity : AppCompatActivity() {
    private lateinit var etNama: EditText
    private lateinit var etKategori: EditText
    private lateinit var etJumlah: EditText
    private lateinit var etTanggal: EditText
    private lateinit var btnSimpan: Button
    private lateinit var rvPemasukan: RecyclerView
    private val daftarPemasukan = ArrayList<Pemasukan>()
    private lateinit var adapter: PemasukanAdapter
    private var posisiEdit = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemasukan)
        etNama = findViewById(R.id.etNamaPemasukan)
        etKategori = findViewById(R.id.etKategoriPemasukan)
        etJumlah = findViewById(R.id.etJumlahPemasukan)
        etTanggal = findViewById(R.id.etTanggalPemasukan)
        btnSimpan = findViewById(R.id.btnSimpanPemasukan)
        rvPemasukan = findViewById(R.id.rvPemasukan)
        adapter = PemasukanAdapter(
            daftarPemasukan,
            { posisi -> editPemasukan(posisi) },
            { posisi -> hapusPemasukan(posisi) }
        )
        rvPemasukan.layoutManager = LinearLayoutManager(this)
        rvPemasukan.adapter = adapter
        btnSimpan.setOnClickListener {
            simpanPemasukan()
        }
    }
    private fun simpanPemasukan() {
        val nama = etNama.text.toString().trim()
        val kategori = etKategori.text.toString().trim()
        val jumlah = etJumlah.text.toString().toDoubleOrNull()
        val tanggal = etTanggal.text.toString().trim()
        if (nama.isEmpty() || kategori.isEmpty() || jumlah == null || tanggal.isEmpty()) {
            Toast.makeText(this, "Lengkapi semua data dengan benar", Toast.LENGTH_SHORT).show()
            return
        }
        if (jumlah <= 0) {
            Toast.makeText(this, "Jumlah harus lebih dari 0", Toast.LENGTH_SHORT).show()
            return
        }
        if (posisiEdit == -1) {
            val pemasukan = Pemasukan(
                System.currentTimeMillis(),
                nama,
                kategori,
                jumlah,
                tanggal
            )
            daftarPemasukan.add(pemasukan)
            adapter.notifyItemInserted(daftarPemasukan.size - 1)
            Toast.makeText(this, "Pemasukan berhasil ditambahkan", Toast.LENGTH_SHORT).show()
        } else {
            val pemasukan = daftarPemasukan[posisiEdit]
            pemasukan.nama = nama
            pemasukan.kategori = kategori
            pemasukan.jumlah = jumlah
            pemasukan.tanggal = tanggal
            adapter.notifyItemChanged(posisiEdit)
            Toast.makeText(this, "Pemasukan berhasil diubah", Toast.LENGTH_SHORT).show()
        }
        resetForm()
    }
    private fun editPemasukan(posisi: Int) {
        if (posisi !in daftarPemasukan.indices) return
        val pemasukan = daftarPemasukan[posisi]
        etNama.setText(pemasukan.nama)
        etKategori.setText(pemasukan.kategori)
        etJumlah.setText(pemasukan.jumlah.toString())
        etTanggal.setText(pemasukan.tanggal)
        posisiEdit = posisi
        btnSimpan.text = "Ubah Pemasukan"
    }
    private fun hapusPemasukan(posisi: Int) {
        if (posisi !in daftarPemasukan.indices) return
        AlertDialog.Builder(this)
            .setTitle("Hapus Pemasukan")
            .setMessage("Apakah Anda yakin ingin menghapus pemasukan ini?")
            .setPositiveButton("Ya") { _, _ ->
                daftarPemasukan.removeAt(posisi)
                adapter.notifyItemRemoved(posisi)
                if (posisiEdit == posisi) {
                    resetForm()
                } else if (posisiEdit > posisi) {
                    posisiEdit--
                }
                Toast.makeText(this, "Pemasukan berhasil dihapus", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Batal", null)
            .show()
    }
    private fun resetForm() {
        etNama.text.clear()
        etKategori.text.clear()
        etJumlah.text.clear()
        etTanggal.text.clear()
        posisiEdit = -1
        btnSimpan.text = "Tambah Pemasukan"
    }
}