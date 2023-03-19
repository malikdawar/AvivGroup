package com.example.avivgroup.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avivgroup.core.utils.load
import com.example.avivgroup.data.model.PropertyModel
import com.example.avivgroup.databinding.RowItemPhotoBinding

class PhotosAdapter :
    RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {

    lateinit var onPhotoSelected: (PropertyModel) -> Unit
    private val photoItems: ArrayList<PropertyModel> = arrayListOf()

    fun setItems(photosList: List<PropertyModel>) {
        photoItems.clear()
        photoItems.addAll(photosList)
        notifyDataSetChanged()
    }

    fun onPhotoSelectionListener(listener: (PropertyModel) -> Unit) {
        onPhotoSelected = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = RowItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photoItems[position], position)
    }

    override fun getItemCount() = photoItems.size

    inner class PhotoViewHolder(private val rowItemPhotoBinding: RowItemPhotoBinding) :
        RecyclerView.ViewHolder(rowItemPhotoBinding.root) {

        fun bind(propertyModel: PropertyModel, position: Int) {
            rowItemPhotoBinding.apply {
                imgPhoto.load(propertyModel.url)
                cardPhoto.setOnClickListener {
                    onPhotoSelected(propertyModel)
                }
            }
        }
    }
}
