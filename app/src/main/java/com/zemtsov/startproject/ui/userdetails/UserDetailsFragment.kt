package com.zemtsov.startproject.ui.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.zemtsov.startproject.databinding.FragmentUserDetailsBinding

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
class UserDetailsFragment : Fragment() {

    private var _viewBinding: FragmentUserDetailsBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: UserDetailsViewModel by viewModels()

    private val args: UserDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navController = findNavController()
        viewModel.setUser(args.user)

        viewModel.userLiveData.observe(viewLifecycleOwner, Observer {
            Glide.with(this)
                .load(it.avatarUrl)
                .into(viewBinding.avatarImageView)

            viewBinding.nameTextView.text = it.login
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}