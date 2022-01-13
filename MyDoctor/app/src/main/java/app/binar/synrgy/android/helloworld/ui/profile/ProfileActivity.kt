package app.binar.synrgy.android.helloworld.ui.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import app.binar.synrgy.android.helloworld.R
import app.binar.synrgy.android.helloworld.constant.Const
import app.binar.synrgy.android.helloworld.databinding.ActivityProfileBinding
import app.binar.synrgy.android.helloworld.model.home.ProfileMenuModel
import app.binar.synrgy.android.helloworld.ui.getStarted.GetStartedActivity
import app.binar.synrgy.android.helloworld.ui.homePage.HomeActivity
import app.binar.synrgy.android.helloworld.ui.signin.SignInActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE)
        viewModel = ProfileViewModel(sharedPreferences)

        binding.imageBack.setOnClickListener {
            onBackPressed()
        }

        //mengambil gambar dari galeri
        val getContent =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {

                    val type = contentResolver.getType(it) //get file type

                    //directory akan terbuat secata otomatis jika valuenya null
                    val tempFile = File.createTempFile("temp-", null, null)
                    val inputstream = contentResolver.openInputStream(uri)

                    tempFile.outputStream().use {
                        inputstream?.copyTo(it)
                    }

                    val requestBody = tempFile.asRequestBody(type?.toMediaType())
                    val body =
                        MultipartBody.Part.createFormData("image", tempFile.name, requestBody)

                    viewModel.uploadImage(body)

//                    viewModel.onChangedImage(uri)
//                    Glide.with(this).load(uri).circleCrop().into(binding.imageProfile)
                }

//                binding.imageProfile.setImageURI(uri)
            }

        binding.imageProfile.setOnClickListener {
            getContent.launch("image/*")
        }

        val profileMenuAdapter =
            AdapterProfileMenu(listOf(), object : AdapterProfileMenu.EventListener {
                override fun click(item: ProfileMenuModel) {
                    Toast.makeText(
                        this@ProfileActivity,
                        getString(item.title),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

        binding.recycleProfileMenu.adapter = profileMenuAdapter

        viewModel.onViewLoaded()

        viewModel.profileModel.observe(this, {
//            binding.imageProfile.setImageResource(it.image)
            // library untuk membuat circle image
            Glide.with(this).load(it.image).circleCrop().into(binding.imageProfile)
            binding.userName.text = it.name
            binding.userJob.text = it.job
        })

        viewModel.homeModel.observe(this, {
            profileMenuAdapter.update(it.profileMenuList)
        })

        //test kotlin coroutines
        viewModel.sample()

        binding.textLogout.setOnClickListener {
            sharedPreferences.edit {
                this.putBoolean(Const.IS_LOGIN, false)
                apply()
            }

            //menampilkan snakbar ketika berhasi login
            Snackbar.make(
                binding.root, "Berhasil Logout",
                Snackbar.LENGTH_LONG
            ).show().also {
                startActivity(Intent(this, GetStartedActivity::class.java).apply {
                    //apply this.addFlags untuk membuat halaman tidak kembali kesign in ketika sudah login
                    this.addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                                Intent.FLAG_ACTIVITY_NEW_TASK
                    )
                })
            }
        }
    }
}
