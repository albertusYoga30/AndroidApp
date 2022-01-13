package app.binar.synrgy.android.helloworld.ui.homenavigation.ui.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.binar.synrgy.android.helloworld.data.api.MessageListResponse
import app.binar.synrgy.android.helloworld.databinding.FragmentMessagesBinding

class MessagesFragment : Fragment() {

    private lateinit var viewModel: MessagesViewModel
    private lateinit var binding: FragmentMessagesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = MessagesViewModel()
        binding = FragmentMessagesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapterMessage = AdapterMessage(listOf(),
            object : AdapterMessage.EventListener {
                override fun click(item: MessageListResponse.MessageResponse) {
                    Toast.makeText(
                        context, item.id,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })

        binding.recycleMessage.adapter = adapterMessage
        viewModel.onViewLoaded()
        viewModel.messageModel.observe(viewLifecycleOwner, {
            adapterMessage.update(it.message)
        })

        return root
    }

}