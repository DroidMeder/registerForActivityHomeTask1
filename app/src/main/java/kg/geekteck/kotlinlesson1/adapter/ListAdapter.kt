package kg.geekteck.kotlinlesson1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geekteck.kotlinlesson1.R
import kg.geekteck.kotlinlesson1.databinding.ItemBinding
import kg.geekteck.kotlinlesson1.models.History

class ListAdapter(private var list: ArrayList<History>): RecyclerView.Adapter<ListAdapter.HistoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return HistoryHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.bind(list[position])
        println("001")
    }

    override fun getItemCount()=list.size

    class HistoryHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding=ItemBinding.bind(itemView)

        fun bind(history: History) = with(binding){
            tvTitle.text=history.content
            tvCreatedAt.text=history.createdAt.toString()
        }
    }
}