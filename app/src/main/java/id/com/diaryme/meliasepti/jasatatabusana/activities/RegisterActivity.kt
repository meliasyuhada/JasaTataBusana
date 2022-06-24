package id.com.diaryme.meliasepti.jasatatabusana.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import id.com.diaryme.meliasepti.jasatatabusana.R
import id.com.diaryme.meliasepti.jasatatabusana.models.DefaultResponse
import id.com.diaryme.meliasepti.jasatatabusana.models.User
import id.com.diaryme.meliasepti.jasatatabusana.services.ServiceBuilder
import id.com.diaryme.meliasepti.jasatatabusana.services.UserService
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnSubmit.setOnClickListener {
            val nama = etNama.text.toString()
            val tanggalLahir = etTanggalLahir.text.toString()
            val jenisKelamin = spJenisKelamin.selectedItem.toString()
            val nomorHP = etNomorHP.text.toString()
            val alamat = etAlamat.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val konfirmasiPassword = etKonfirmasiPassword.text.toString()

            if (TextUtils.isEmpty(nama)) {
                etNama.error = "Nama tidak boleh kosong!"
                etNama.requestFocus()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(tanggalLahir)) {
                etTanggalLahir.error = "Tanggal lahir tidak boleh kosong!"
                etTanggalLahir.requestFocus()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty("Jenis Kelamin")) {
                Toast.makeText(
                    applicationContext,
                    "silahkan pilih jenis kelamin!",
                    Toast.LENGTH_SHORT
                ).show()
                spJenisKelamin.requestFocus()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(nomorHP)) {
                etNomorHP.error = "Nomor HP tidak boleh kosong!"
                etNomorHP.requestFocus()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(alamat)) {
                etAlamat.error = "Alamat tidak boleh kosong!"
                etAlamat.requestFocus()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                etPassword.error = "Password tidak boleh kosong!"
                etPassword.requestFocus()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(konfirmasiPassword)) {
                etKonfirmasiPassword.error = "Konfirmasi Password tidak boleh kosong!"
                etKonfirmasiPassword.requestFocus()
                return@setOnClickListener
            }
            if (!password.equals(konfirmasiPassword)) {
                etKonfirmasiPassword.error = "Password dan Konfirmasi password tidak sama!"
                etKonfirmasiPassword.requestFocus()
                return@setOnClickListener
            }
            val newUser = User(0, nama, tanggalLahir, jenisKelamin, nomorHP, alamat, email, password);
            val userService: UserService = ServiceBuilder.buildService(UserService::class.java)
            val requestCall: Call<DefaultResponse> = userService.registerUser(newUser)

            requestCall.enqueue(object :
                retrofit2.Callback<DefaultResponse>{
                override fun onFailure(call: Call<DefaultResponse>, t:
                Throwable) {
                    Toast.makeText(this@RegisterActivity, "Error terjadi ketika sedang mendaftarkan user: " + t.toString(),
                    Toast.LENGTH_LONG).show()
                }
                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    if(!response.body()?.error!!) {
                        val defaultResponse: DefaultResponse =
                            response.body()!!
                        defaultResponse.let {
                            Toast.makeText(this@RegisterActivity,
                                defaultResponse.message, Toast.LENGTH_LONG).show()
                            val intent = Intent(applicationContext,
                                LoginActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    startActivity(intent)
                        }
                    }else{
                        Toast.makeText(this@RegisterActivity, "Gagal mendaftarkan user: " + response.body()?.message, Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }
}