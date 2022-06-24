package id.com.diaryme.meliasepti.jasatatabusana.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.com.diaryme.meliasepti.jasatatabusana.R
import id.com.diaryme.meliasepti.jasatatabusana.helpers.Config
import id.com.diaryme.meliasepti.jasatatabusana.models.Jasa
import kotlinx.android.synthetic.main.jasa_list.view.*

class JasaAdapter (private val jasaList: List<Jasa>, private val adapterOnClick: (Jasa) -> Unit) : RecyclerView.Adapter<JasaAdapter.ServiceHolder> () {

    inner class ServiceHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindHero(jasa: Jasa) {
            itemView.apply {
                tvNamaJasa.text = jasa.namaJasa
                tvDeskripsiSingkat.text = jasa.deskripsiSingkat
                tvRating.text = jasa.rating.toString()

                Glide.with(context)
                    .load(Config.IMAGE_URL + jasa.gambar)
                    .into(imageView)
                setOnClickListener {
                    adapterOnClick(jasa)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JasaAdapter.ServiceHolder {
        return ServiceHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.jasa_list, parent,false))
    }

    override fun onBindViewHolder(holder: JasaAdapter.ServiceHolder, position: Int) {
        holder.bindHero(jasaList[position])
    }

    override fun getItemCount(): Int {
        return jasaList.size
    }

}