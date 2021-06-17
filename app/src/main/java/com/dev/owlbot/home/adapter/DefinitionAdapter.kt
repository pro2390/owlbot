package com.dev.owlbot.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.GONE
import com.dev.owlbot.R
import com.dev.owlbot.home.model.Definition
import com.squareup.picasso.Picasso

//Adapter for list of definition got from api for word searched
class DefinitionAdapter(private val definitions: ArrayList<Definition>?) :
    Adapter<DefinitionAdapter.DefinitionViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DefinitionAdapter.DefinitionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_definition, parent, false)
        return DefinitionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DefinitionAdapter.DefinitionViewHolder, position: Int) {
        val definition: Definition? = definitions?.get(position)
        holder.type.text = definition?.type
        holder.definition.text =definition?.definition
        holder.example.text=definition?.example
        if (definition?.imageUrl != null) {
            Picasso.get().load(definition.imageUrl).into(holder.image)
        }else {
            holder.image.visibility = GONE
        }
    }

    override fun getItemCount(): Int = definitions?.size!!
    //to clear the list from activity when to search a new word
    fun clear(){
        definitions?.clear()
    }

    inner class DefinitionViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var definition: TextView = v.findViewById(R.id.tvDefinition)
        var type: TextView = v.findViewById(R.id.tvType)
        var example: TextView = v.findViewById(R.id.tvExample)
        var image: ImageView = v.findViewById(R.id.ivDefinitionImage)
    }
}