package com.eduardomaxwell.pokedex.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eduardomaxwell.pokedex.R
import com.eduardomaxwell.pokedex.databinding.PokemonItemBinding
import com.eduardomaxwell.pokedex.domain.Pokemon

class PokemonAdapter(
    private val items: List<Pokemon?>,
    private val onItemClickListener: ((pokemon: Pokemon) -> Unit)
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewBind(items[position])
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.right_to_left_anim)
    }

    override fun getItemCount() = items.size

    class ViewHolder(
        itemView: PokemonItemBinding?,
        private val onItemClickListener: ((pokemon: Pokemon) -> Unit)
    ) : RecyclerView.ViewHolder(itemView!!.root) {


        private val image = itemView?.ivPokemon
        private val name = itemView?.tvName
        private val number = itemView?.tvNumber
        private val type = itemView?.tvType1
        private val type2 = itemView?.tvType2

        fun viewBind(item: Pokemon?) {

            item?.let {

                if (image != null) {
                    Glide.with(itemView.context)
                        .load(item.imageURl)
                        .centerCrop()
                        .into(image)
                }

                name?.text = item.name.capitalize()
                number?.text = "Nº ${item.formmatedNumber}"
                type?.text = item.types[0].name.capitalize()
                if (item.types.size > 1) {
                    type2?.visibility = View.VISIBLE
                    type2?.text = item.types[1].name.capitalize()
                } else {
                    type2?.visibility = View.GONE

                }
            }

            itemView.rootView.setOnClickListener {
                item?.let { pokemon ->
                    onItemClickListener.invoke(pokemon)
                }
            }


        }
    }
}