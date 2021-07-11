package com.codingwithjks.assignment.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codingwithjks.assignment.data.model.AssociatedDrug
import com.codingwithjks.assignment.databinding.EachRowBinding
import com.codingwithjks.assignment.utils.Listener

class MedicineAdapter constructor(private val listener: Listener) :
    ListAdapter<AssociatedDrug, MedicineAdapter.MedicineViewHolder>(Diff) {

    inner class MedicineViewHolder(private val binding: EachRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(associatedDrug: AssociatedDrug) {
            binding.apply {
                name.text = "Name : ${associatedDrug.name}"
                dose.text = "Dose : ${associatedDrug.dose}"
                strength.text = "Strength : ${associatedDrug.strength}"
                root.setOnClickListener {
                    listener.onClick(
                        associatedDrug.name,
                        associatedDrug.dose,
                        associatedDrug.strength
                    )
                }
            }
        }

    }

    object Diff : DiffUtil.ItemCallback<AssociatedDrug>() {
        override fun areItemsTheSame(oldItem: AssociatedDrug, newItem: AssociatedDrug): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: AssociatedDrug, newItem: AssociatedDrug): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        return MedicineViewHolder(
            EachRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }
}