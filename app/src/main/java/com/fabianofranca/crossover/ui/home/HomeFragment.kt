package com.fabianofranca.crossover.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fabianofranca.crossover.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding?.apply {
            btnViewBinding.setOnClickListener {
                findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToUsersWithViewBinding())
            }
            btnCompose.setOnClickListener {
                findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToUsersWithComposeFragment())
            }
            btnComposeAndGroupie.setOnClickListener {
                findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToUsersWithComposeAndGroupieFragment())
            }
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
