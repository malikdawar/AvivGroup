package com.example.avivgroup.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avivgroup.core.utils.load
import com.example.avivgroup.data.model.PropertyModel
import com.example.avivgroup.databinding.RowItemPropertyBinding

class PropertyAdapter :
    RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {

    lateinit var onPropertySelected: (PropertyModel) -> Unit
    private val properties: ArrayList<PropertyModel> = arrayListOf()

    fun setItems(propertyList: List<PropertyModel>) {
        properties.clear()
        properties.addAll(propertyList)
        notifyItemRangeInserted(0, propertyList.size)
    }

    fun onPropertySelectionListener(listener: (PropertyModel) -> Unit) {
        onPropertySelected = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val binding = RowItemPropertyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return PropertyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        holder.bind(properties[position], position)
    }

    override fun getItemCount() = properties.size

    inner class PropertyViewHolder(private val rowItemPropertyBinding: RowItemPropertyBinding) :
        RecyclerView.ViewHolder(rowItemPropertyBinding.root) {

        fun bind(propertyModel: PropertyModel, position: Int) {
            rowItemPropertyBinding.apply {
                imgPhoto.load(propertyModel.url)
                cardProperty.setOnClickListener {
                    onPropertySelected(propertyModel)
                }
            }
        }
    }
}
