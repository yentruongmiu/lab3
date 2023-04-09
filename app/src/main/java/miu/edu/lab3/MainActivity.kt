package miu.edu.lab3

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import miu.edu.lab3.databinding.LoginPageBinding
import miu.edu.lab3.model.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding : LoginPageBinding

    private val users = arrayListOf<User>().apply {
        add(User("Yen", "Truong", "yt@gmail.com", "123456"))
        add(User("Susan", "Nguyen", "sn@gmail.com", "123"))
        add(User("James", "Kan", "jk@gmail.com", "123"))
        add(User("David", "Lee", "dl@gmail.com", "123"))
        add(User("Kim", "Jong", "kj@gmail.com", "123"))
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = intent.getSerializableExtra("user", User::class.java)
        user?.let { users.add(it) }

        binding = LoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmailAddress.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty() && users.any { it.username == email && it.password == password}) {
                startActivity(Intent(this, CategoriesActivity::class.java).apply {
                    putExtra("username", email)
                })
            } else {
                Toast.makeText(this, "The username or password is invalid.", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnCreateNewAccount.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.tvForgotPassword.setOnClickListener {
            if(binding.etEmailAddress.text.isNotBlank()) {
                users.find { user ->  user.username.equals(binding.etEmailAddress.text.toString())}
                    ?.let {
                        var emailBody = "Your password is: ${it.password}"
                        composeEmail(arrayOf(it.username),
                            "Reset password from MIU application",
                            emailBody)
                    }
            }else{
                Toast.makeText(this, "The username must not be empty.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun composeEmail(addresses: Array<String>, subject: String, content: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, content)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}