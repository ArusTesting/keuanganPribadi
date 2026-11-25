package ariel.paba.keuanganpribadi

data class Pemasukan(
    var id: Long,
    var nama: String,
    var kategori: String,
    var jumlah: Double,
    var tanggal: String
)