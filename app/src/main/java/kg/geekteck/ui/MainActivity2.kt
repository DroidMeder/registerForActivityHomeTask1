package kg.geekteck.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kg.geekteck.kotlinlesson1.App
import kg.geekteck.kotlinlesson1.constants.Constants.KEY_FIRST_ACTIVITY
import kg.geekteck.kotlinlesson1.constants.Constants.KEY_SECOND_ACTIVITY
import kg.geekteck.kotlinlesson1.R
import kg.geekteck.kotlinlesson1.databinding.ActivityMain2Binding
import kg.geekteck.kotlinlesson1.models.History

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        sendData()
    }

    private fun sendData() = with(binding){
        btnSendTo.setOnClickListener{
            if (etSomeText.text.isNullOrEmpty()){
                Toast.makeText(this@MainActivity2, R.string.there_is_nothing,
                    Toast.LENGTH_LONG).show()
            }else {
                sendMessage(etSomeText.text.toString())
            }
        }

        btnThirdFragment.setOnClickListener{
            val intent = Intent(this@MainActivity2, ListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupViews() {
        val extras:Bundle? = intent.extras
        if (extras != null) {
            binding.etSomeText.setText(extras.getString(KEY_FIRST_ACTIVITY).toString())
            //курсор помешает в конец строки
            binding.etSomeText.setSelection(binding.etSomeText.text.length)
        }
    }

    private fun sendMessage(message: String) {
        val data = Intent()
        data.putExtra(KEY_SECOND_ACTIVITY, message)
        setResult(RESULT_OK, data)
        saveForHistory(message)
        finish()
    }

    override fun onBackPressed() {
        sendMessage(binding.etSomeText.text.toString())
        super.onBackPressed()
    }

    private fun saveForHistory(message: String) {
        val history = History(
            createdAt = System.currentTimeMillis(),
            content = message
        )
        App.database!!.historyDao().insertHistory(history)
    }
}