package kg.geekteck.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kg.geekteck.kotlinlesson1.App
import kg.geekteck.kotlinlesson1.adapter.ListAdapter
import kg.geekteck.kotlinlesson1.databinding.ActivityListBinding
import kg.geekteck.kotlinlesson1.models.History

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getAndSetData()
        cleanCash()
    }

    private fun cleanCash() {
        binding.fab.setOnClickListener {
            App.database!!.historyDao().deleteAll(App.database!!.historyDao().getAllHistory())
            getAndSetData()
        }
    }

    private fun getAndSetData() {
        linearLayoutManager = LinearLayoutManager(this)
        binding.recView.layoutManager = linearLayoutManager
        val list = App.database!!.historyDao().getSortedHistory()
        binding.recView.adapter=ListAdapter(list as ArrayList<History>)
    }
}