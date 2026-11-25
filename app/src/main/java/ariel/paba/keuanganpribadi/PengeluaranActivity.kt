package ariel.paba.keuanganpribadi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PengeluaranActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etKategori: EditText
    private lateinit var etJumlah: EditText
    private lateinit var etTanggal: EditText
    private lateinit var btnSimpan: Button
    private lateinit var rvPengeluaran: RecyclerView

    private val daftarPengeluaran = ArrayList<Pengeluaran>()
    private lateinit var adapter: PengeluaranAdapter
    private var posisiEdit = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengeluaran)

        etNama = findViewById(R.id.etNamaPengeluaran)
        etKategori = findViewById(R.id.etKategoriPengeluaran)
        etJumlah = findViewById(R.id.etJumlahPengeluaran)
        etTanggal = findViewById(R.id.etTanggalPengeluaran)
        btnSimpan = findViewById(R.id.btnSimpanPengeluaran)
        rvPengeluaran = findViewById(R.id.rvPengeluaran)
        daftarPengeluaran.addAll(DataStorage.loadPengeluaran(this))

        adapter = PengeluaranAdapter(
            daftarPengeluaran,
            { posisi -> editPengeluaran(posisi) },
            { posisi -> hapusPengeluaran(posisi) }
        )

        rvPengeluaran.layoutManager = LinearLayoutManager(this)
        rvPengeluaran.adapter = adapter

        btnSimpan.setOnClickListener {
            simpanPengeluaran()
        }
    }

    private fun simpanPengeluaran() {
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
            val pengeluaran = Pengeluaran(
                System.currentTimeMillis(),
                nama,
                kategori,
                jumlah,
                tanggal
            )

            daftarPengeluaran.add(pengeluaran)
            adapter.notifyItemInserted(daftarPengeluaran.size - 1)

            Toast.makeText(this, "Pengeluaran berhasil ditambahkan", Toast.LENGTH_SHORT).show()
        } else {
            val pengeluaran = daftarPengeluaran[posisiEdit]

            pengeluaran.nama = nama
            pengeluaran.kategori = kategori
            pengeluaran.jumlah = jumlah
            pengeluaran.tanggal = tanggal

            adapter.notifyItemChanged(posisiEdit)

            Toast.makeText(this, "Pengeluaran berhasil diubah", Toast.LENGTH_SHORT).show()
        }
        DataStorage.savePengeluaran(this, daftarPengeluaran)
        resetForm()
    }

    private fun editPengeluaran(posisi: Int) {
        if (posisi !in daftarPengeluaran.indices) return

        val pengeluaran = daftarPengeluaran[posisi]

        etNama.setText(pengeluaran.nama)
        etKategori.setText(pengeluaran.kategori)
        etJumlah.setText(pengeluaran.jumlah.toString())
        etTanggal.setText(pengeluaran.tanggal)

        posisiEdit = posisi
        btnSimpan.text = "Ubah Pengeluaran"
    }

    private fun hapusPengeluaran(posisi: Int) {
        if (posisi !in daftarPengeluaran.indices) return

        AlertDialog.Builder(this)
            .setTitle("Hapus Pengeluaran")
            .setMessage("Apakah Anda yakin ingin menghapus pengeluaran ini?")
            .setPositiveButton("Ya") { _, _ ->
                daftarPengeluaran.removeAt(posisi)
                adapter.notifyItemRemoved(posisi)
                DataStorage.savePengeluaran(this, daftarPengeluaran)

                if (posisiEdit == posisi) {
                    resetForm()
                } else if (posisiEdit > posisi) {
                    posisiEdit--
                }

                Toast.makeText(this, "Pengeluaran berhasil dihapus", Toast.LENGTH_SHORT).show()
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
        btnSimpan.text = "Tambah Pengeluaran"
    }
}