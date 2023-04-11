package miu.edu.lab3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import miu.edu.lab3.databinding.CategoriesPageBinding

class CategoriesActivity : AppCompatActivity() {
    private lateinit var binding : CategoriesPageBinding
    val books = arrayOf<String>("Maharishi", "Dr Tony Nader", "Scientific Research Consciousness, Knowledge and Enlightenment ", "Modern Science and Verdic Science", "Book Series", "Other Series", "Clearance")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CategoriesPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username")
        binding.tvEmail.text = "Welcome $username"
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.imageView1 -> {
                val intent = Intent(this, BookActivity::class.java)
                intent.putExtra("books", books)
                startActivity(intent)
                //Toast.makeText(this, "You have chosen the Books category of shopping", Toast.LENGTH_SHORT).show()
            }
            R.id.imageView2 -> Toast.makeText(this, "You have chosen the Clothing category of shopping", Toast.LENGTH_SHORT).show()
            R.id.imageView3 -> {
                startActivity(Intent(this, ProductListActivity::class.java))
                //Toast.makeText(this, "You have chosen the Organic Food category of shopping", Toast.LENGTH_SHORT).show()
            }
            R.id.imageView4 -> Toast.makeText(this, "You have chosen the Medicines category of shopping", Toast.LENGTH_SHORT).show()
        }
    }
}