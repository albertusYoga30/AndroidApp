package app.binar.synrgy.android.helloworld.ui.signUp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import app.binar.synrgy.android.helloworld.databinding.ActivitySignUpBinding
import app.binar.synrgy.android.helloworld.constant.Const
import app.binar.synrgy.android.helloworld.ui.homenavigation.HomeNavigationActivity
import com.google.android.material.snackbar.Snackbar

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences =
            applicationContext.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE)

        viewModel = SignUpViewModel(sharedPreferences)


        //Observer
        //region Observer
        viewModel.isButtonEnable.observe(this, Observer {
            binding.buttonContinue.isEnabled = it
        })


//        viewModel.goToUploadImage.observe(this, Observer {
//            if (it) {
//                startActivity(
//                    Intent(this, HomeNavigationActivity::class.java).apply {
//                        //apply this.addFlags untuk membuat halaman tidak kembali kesign in ketika sudah login
//                        this.addFlags(
//                            Intent.FLAG_ACTIVITY_CLEAR_TOP or
//                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
//                                    Intent.FLAG_ACTIVITY_NEW_TASK
//                        )
//                    }
//                )
//            }
//        })

        viewModel.goToLogin.observe(this,{
            if (it){
                finish()
            }
        })

        viewModel.showErrorMessage.observe(this, Observer {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        })


        //action view
        binding.inputFullname.doAfterTextChanged {
            viewModel.onChangeName(it.toString())
        }

        binding.inputEmail.doAfterTextChanged {
            viewModel.onChangeEmail(it.toString())
        }
        binding.inputPassword.doAfterTextChanged {
            viewModel.onChangePassword(it.toString())
        }

        binding.inputPekerjaan.doAfterTextChanged {
            viewModel.onChangeJob(it.toString())
        }

        //signUp button
        binding.buttonContinue.setOnClickListener {
            viewModel.doSignUp()
        }

        //back page
        binding.backImage.setOnClickListener {
            onBackPressed() // atau juga bisa menggunakan finish()
        }
    }

}