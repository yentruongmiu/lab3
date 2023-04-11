package miu.edu.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import miu.edu.lab3.databinding.ActivityBookBinding

class BookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val books = intent.getStringArrayExtra("books")
        books?.let {
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, books)
            binding.lvBook.adapter = adapter
            binding.lvBook.setOnItemClickListener{_, _, position, _ ->
                Toast.makeText(this, books[position].toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}