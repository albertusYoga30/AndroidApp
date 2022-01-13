package app.binar.synrgy.android.helloworld.ui.getStarted

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import app.binar.synrgy.android.helloworld.R
import app.binar.synrgy.android.helloworld.ui.signin.SignInActivity

class GetStartedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        val imageLogo = findViewById<ImageView>(R.id.icon_logo)
        val textConsult = findViewById<TextView>(R.id.text_consult_doctor)
        val buttonGetStarted = findViewById<Button>(R.id.button_getstarted)
        val buttonSignIn = findViewById<Button>(R.id.button_signIn)


        imageLogo.setOnClickListener {
            Toast.makeText(this, "Cuma logo", Toast.LENGTH_SHORT).show()
        }

        textConsult.setOnClickListener {
            Toast.makeText(this, "Cuma Text Doang", Toast.LENGTH_SHORT).show()
        }
        buttonGetStarted.setOnClickListener {
            Toast.makeText(this, "Get Started is Clicked", Toast.LENGTH_SHORT).show()
        }
        buttonSignIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}