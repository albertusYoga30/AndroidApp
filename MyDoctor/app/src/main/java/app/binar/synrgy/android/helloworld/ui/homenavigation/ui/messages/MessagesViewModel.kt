package app.binar.synrgy.android.helloworld.ui.homenavigation.ui.messages

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synrgy.android.helloworld.data.api.HomeAPI
import app.binar.synrgy.android.helloworld.data.api.MessageListResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MessagesViewModel : ViewModel() {
    val messageModel: MutableLiveData<MessageListResponse> = MutableLiveData()

    private lateinit var massageAPI: HomeAPI

    fun onViewLoaded() {
        getDataFromAPI()

    }

    fun getDataFromAPI() {
        massageAPI = HomeAPI.getInstanceApiary().create(HomeAPI::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = massageAPI.getPageMessage()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("getPageMessage()", response.body().toString())
                    messageModel.value = response.body()
                    println("API success")
                } else {
                    Log.d("getMessage()", response.message().toString())
                    println("API Failed")
                }
            }
        }
    }
}