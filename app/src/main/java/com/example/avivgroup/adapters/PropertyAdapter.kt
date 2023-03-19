package com.example.avivgroup.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avivgroup.core.utils.load
import com.example.avivgroup.data.model.PropertyModel
import com.example.avivgroup.databinding.RowItemPropertyBinding

class PropertyAdapter :
    RecyclerView.Adapter<PropertyAdapter.PhotoViewHolder>() {

    lateinit var onPropertySelected: (PropertyModel) -> Unit
    private val properties: ArrayList<PropertyModel> = arrayListOf()

    fun setItems(photosList: List<PropertyModel>) {
        properties.clear()
        properties.addAll(photosList)
        notifyItemRangeInserted(0, photosList.size)
    }

    fun onPropertySelectionListener(listener: (PropertyModel) -> Unit) {
        onPropertySelected = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = RowItemPropertyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(properties[position], position)
    }

    override fun getItemCount() = properties.size

    inner class PhotoViewHolder(private val rowItemPhotoBinding: RowItemPropertyBinding) :
        RecyclerView.ViewHolder(rowItemPhotoBinding.root) {

        fun bind(propertyModel: PropertyModel, position: Int) {
            rowItemPhotoBinding.apply {
                imgPhoto.load(propertyModel.url)
                cardPhoto.setOnClickListener {
                    onPropertySelected(propertyModel)
                }
            }
        }
    }
}
