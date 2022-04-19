package kg.geekteck.kotlinlesson1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kg.geekteck.kotlinlesson1.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        sendData()
    }

    private fun sendData() {
        binding.btnSendTo.setOnClickListener{
            if (binding.etSomeText.text.isNullOrEmpty()){
                Toast.makeText(this, R.string.there_is_nothing,
                    Toast.LENGTH_LONG).show()
            }else {
                sendMessage(binding.etSomeText.text.toString())
            }
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
        finish()
    }

    override fun onBackPressed() {
        sendMessage(binding.etSomeText.text.toString())
        super.onBackPressed()
    }
}