package com.eduardomaxwell.pokedex.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eduardomaxwell.pokedex.databinding.PokemonItemBinding
import com.eduardomaxwell.pokedex.domain.Pokemon
import java.util.*

class PokemonPagingAdapter :
    PagingDataAdapter<Pokemon, PokemonPagingAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            PokemonItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.viewBind(currentItem)
    }


    class MyViewHolder(
        itemView: PokemonItemBinding
    ) : RecyclerView.ViewHolder(itemView.root) {

        private val image = itemView.ivPokemon
        private val name = itemView.tvName
        private val number = itemView.tvNumber
        private val type = itemView.tvType1
        private val type2 = itemView.tvType2

        fun viewBind(item: Pokemon?) {

            item?.let {

                Glide.with(itemView.context)
                    .load(item.imageURl)
                    .centerCrop()
                    .into(image)

                name.text =
                    item.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                number.text = "Nº ${item.formmatedNumber}"
                type.text = item.types[0].name.capitalize()
                if (item.types.size > 1) {
                    type2.visibility = View.VISIBLE
                    type2.text = item.types[1].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                } else {
                    type2.visibility = View.GONE

                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}