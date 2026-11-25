package ariel.paba.keuanganpribadi

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataStorage {

    private const val PREF_NAME = "dataSP"

    private val gson = Gson()

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveKategori(context: Context, daftar: ArrayList<Kategori>) {
        val sp = getPreferences(context)
        val editor = sp.edit()

        editor.putString("spKategori", gson.toJson(daftar))
        editor.apply()
    }

    fun loadKategori(context: Context): ArrayList<Kategori> {
        val sp = getPreferences(context)
        val isiSP = sp.getString("spKategori", null)

        val type = object : TypeToken<ArrayList<Kategori>>() {}.type

        return if (isiSP != null) {
            gson.fromJson(isiSP, type)
        } else {
            arrayListOf()
        }
    }

    fun savePemasukan(context: Context, daftar: ArrayList<Pemasukan>) {
        val sp = getPreferences(context)
        val editor = sp.edit()

        editor.putString("spPemasukan", gson.toJson(daftar))
        editor.apply()
    }

    fun loadPemasukan(context: Context): ArrayList<Pemasukan> {
        val sp = getPreferences(context)
        val isiSP = sp.getString("spPemasukan", null)

        val type = object : TypeToken<ArrayList<Pemasukan>>() {}.type

        return if (isiSP != null) {
            gson.fromJson(isiSP, type)
        } else {
            arrayListOf()
        }
    }

    fun savePengeluaran(context: Context, daftar: ArrayList<Pengeluaran>) {
        val sp = getPreferences(context)
        val editor = sp.edit()

        editor.putString("spPengeluaran", gson.toJson(daftar))
        editor.apply()
    }

    fun loadPengeluaran(context: Context): ArrayList<Pengeluaran> {
        val sp = getPreferences(context)
        val isiSP = sp.getString("spPengeluaran", null)

        val type = object : TypeToken<ArrayList<Pengeluaran>>() {}.type

        return if (isiSP != null) {
            gson.fromJson(isiSP, type)
        } else {
            arrayListOf()
        }
    }
}