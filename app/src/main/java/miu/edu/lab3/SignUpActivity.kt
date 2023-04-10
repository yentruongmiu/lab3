package miu.edu.lab3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import miu.edu.lab3.databinding.SignUpPageBinding
import miu.edu.lab3.model.User

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : SignUpPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btnCreateAccount.setOnClickListener {
            var user : User? = null
            if(binding.etFirstName.text.isNotBlank() && binding.etLastName.text.isNotBlank() &&
                binding.etEmailAddress.text.isNotBlank() && binding.etPassword.text.isNotBlank()){
                user = User(binding.etFirstName.text.toString(),
                    binding.etLastName.text.toString(),
                    binding.etEmailAddress.text.toString(),
                    binding.etPassword.text.toString())
            }

            user?.let {
                startActivity(Intent(this, MainActivity::class.java).apply {
                    putExtra("user", it)
                })
            }
        }


    }
}