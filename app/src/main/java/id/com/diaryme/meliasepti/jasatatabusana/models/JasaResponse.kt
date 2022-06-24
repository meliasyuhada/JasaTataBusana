package id.com.diaryme.meliasepti.jasatatabusana.models

data class JasaResponse(
    val message: String,
    val error: Boolean,
    val datas: List<Jasa>
)
