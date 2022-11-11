package com.example.runningtrackerapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.runningtrackerapp.databinding.ItemRunBinding
import com.example.runningtrackerapp.domain.model.Run
import com.example.runningtrackerapp.util.CustomTimeFormat
import java.text.SimpleDateFormat
import java.util.*

class RunAdapter : RecyclerView.Adapter<RunAdapter.RunViewHolder>() {

    inner class RunViewHolder(val binding: ItemRunBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Run>() {
        override fun areItemsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Run>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
        val binding = ItemRunBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RunViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        val run = differ.currentList[position]
         with(holder.binding){
             Glide.with(this.root).load(run.img).into(ivRunImage)

             val calendar = Calendar.getInstance().apply {
                 timeInMillis = run.timestamp
             }

             val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
             tvDate.text = dateFormat.format(calendar.time)

             val avgSpeed = "${run.avgSpeedInKMH}km/h"
             tvAvgSpeed.text = avgSpeed

             val distanceInKm = "${run.distanceInMeters / 1000f}km"
             tvDistance.text = distanceInKm

             tvTime.text = CustomTimeFormat.getFormattedStopWatchTime(run.timeInMillis)

             val caloriesBurned = "${run.caloriesBurned}kcal"
             tvCalories.text = caloriesBurned
         }

    }
}














