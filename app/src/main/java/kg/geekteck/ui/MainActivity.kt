package kg.geekteck.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kg.geekteck.kotlinlesson1.App
import kg.geekteck.kotlinlesson1.constants.Constants.KEY_FIRST_ACTIVITY
import kg.geekteck.kotlinlesson1.constants.Constants.KEY_SECOND_ACTIVITY
import kg.geekteck.kotlinlesson1.R
import kg.geekteck.kotlinlesson1.databinding.ActivityMainBinding
import kg.geekteck.kotlinlesson1.models.History


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sendData()
    }

    private fun sendData() {
        binding.btnSendTo.setOnClickListener {
            if (binding.etSomeText.text.isNullOrEmpty()) {
                Toast.makeText(
                    this, getString(R.string.there_is_nothing),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                // скрывает клавиатуру чтобы не мешало каждый раз
                binding.etSomeText.onEditorAction(EditorInfo.IME_ACTION_DONE)
                openSomeActivityForResult()
            }
        }
    }

    private fun openSomeActivityForResult() {
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra(KEY_FIRST_ACTIVITY, binding.etSomeText.text.toString())
        saveForHistory()
        resultLauncher.launch(intent)
    }

    private fun saveForHistory() {
        val history = History(
            createdAt = System.currentTimeMillis(),
            content = binding.etSomeText.text.toString()
        )
        App.database!!.historyDao().insertHistory(history)
        println("==="+App.database!!.historyDao().getAllHistory().size)
    }

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts
            .StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent: Intent? = result.data
            val accessMessage = intent!!.getStringExtra(KEY_SECOND_ACTIVITY)
            binding.etSomeText.setText(accessMessage.toString())
            //курсор помешает в конец строки
            binding.etSomeText.setSelection(binding.etSomeText.text.length)
        }
    }
}