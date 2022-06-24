package id.com.diaryme.meliasepti.jasatatabusana.services

import id.com.diaryme.meliasepti.jasatatabusana.models.JasaResponse
import retrofit2.Call
import retrofit2.http.GET

interface JasaService {
    @GET("services")
    fun getJasa() : Call<JasaResponse>
}