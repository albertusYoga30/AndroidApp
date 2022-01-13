package app.binar.synrgy.android.helloworld.ui.signin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.doAfterTextChanged
import app.binar.synrgy.android.helloworld.constant.Const
import app.binar.synrgy.android.helloworld.databinding.ActivitySignInBinding
import app.binar.synrgy.android.helloworld.ui.homePage.HomeActivity
import app.binar.synrgy.android.helloworld.ui.homenavigation.HomeNavigationActivity
import app.binar.synrgy.android.helloworld.ui.signUp.SignUpActivity
import com.google.android.material.snackbar.Snackbar


class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater) //membaca otomatis layout
        setContentView(binding.root)

        //init sharedpreference
        val sharedPreferences: SharedPreferences =
            applicationContext.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE)

        viewModel = SignInViewModel(sharedPreferences)

        viewModel.isButtonEnable.observe(this, {
            binding.buttonSignIn.isEnabled = it
        })

        binding.editTextEmail.doAfterTextChanged {
            val email = it.toString()
            viewModel.onChangeEmail(email)
        }

        binding.editTextPassword.doAfterTextChanged {
            val password = it.toString()
            viewModel.onChangePassword(password)
        }

        binding.buttonSignIn.setOnClickListener {
            viewModel.doSignIn()
        }

        binding.textCreateAccount.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        viewModel.goToHomePage.observe(this, {
            if (it) {
                startActivity(
                    Intent(this, HomeNavigationActivity::class.java).apply {
                        //apply this.addFlags untuk membuat halaman tidak kembali kesign in ketika sudah login
                        this.addFlags(
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                                    Intent.FLAG_ACTIVITY_NEW_TASK
                        )
                    }
                )
            }
        })

        viewModel.showErrorMessage.observe(this,{
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        })
    }

            //            val email = binding.editTextEmail.text.toString()
//            val password = binding.editTextPassword.text.toString()
//            val emailValidate = sharedPreferences.getString(Const.EMAIL, "")
//            val passwordValidate = sharedPreferences.getString(Const.PASSWORD, "")

//            if (validateInput()) {
//                //kondisi ketika user input email dan password dengan benar
//                if (emailValidate.equals(email) && passwordValidate.equals(password)) {
//                    //menambahkan value IS LOGIN menjadi true ketika login
//                    sharedPreferences.edit {
//                        this.putBoolean(Const.IS_LOGIN, true)
//                        apply()
//                    }
//
//                    //menampilkan snakbar ketika berhasi login
//                    Snackbar.make(
//                        binding.root, "Login Berhasil",
//                        Snackbar.LENGTH_LONG
//                    ).show().also {
//                        startActivity(Intent(this, HomeActivity::class.java).apply {
//                            //apply this.addFlags untuk membuat halaman tidak kembali kesign in ketika sudah login
//                            this.addFlags(
//                                Intent.FLAG_ACTIVITY_CLEAR_TOP or
//                                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
//                                        Intent.FLAG_ACTIVITY_NEW_TASK
//                            )
//                        })
//                    }
//                } else if (!emailValidate.equals(email) && passwordValidate.equals(password)) {
//                    Snackbar.make(
//                        binding.root, "Email tidak terdaftar",
//                        Snackbar.LENGTH_LONG
//                    ).show()
//                } else if (emailValidate.equals(email) && !passwordValidate.equals(password)) {
//                    Snackbar.make(
//                        binding.root, "Password Salah",
//                        Snackbar.LENGTH_LONG
//                    ).show()
//                } else {
//                    Snackbar.make(
//                        binding.root, "Password dan email Salah",
//                        Snackbar.LENGTH_LONG
//                    ).show()
//                }
//
//            } else if (email.isEmpty() && password.isNotEmpty()) {
//                Snackbar.make(
//                    binding.root, "Email kosong",
//                    Snackbar.LENGTH_LONG
//                ).show()
//
//            } else if (email.isNotEmpty() && password.isEmpty()) {
//                Snackbar.make(
//                    binding.root, "Password kosong",
//                    Snackbar.LENGTH_LONG
//                ).show()
//
//            } else {
//                Snackbar.make(
//                    binding.root, "Email dan Password kosong",
//                    Snackbar.LENGTH_LONG
//                ).show()
//            }



//    private fun enableButton() {
//        binding.buttonSignIn.isEnabled = true
//    }

//    private fun validateInput(): Boolean {
//        val email = binding.editTextEmail.text.toString()
//        val password = binding.editTextPassword.text.toString()
//
//        return email.isNotEmpty() && password.isNotEmpty()
//    }

//    private fun validateSharedPref(sharedPreferences: SharedPreferences): Boolean {
//        val email = binding.editTextEmail.text.toString()
//        val password = binding.editTextPassword.text.toString()
//        return sharedPreferences.getString(Const.EMAIL, "").equals(email) &&
//                sharedPreferences.getString(Const.PASSWORD, "").equals(password)
//    }

}