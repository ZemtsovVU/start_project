package com.zemtsov.startproject.ui.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.zemtsov.startproject.databinding.FragmentUserListBinding

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
class UserListFragment : Fragment() {

    private var _viewBinding: FragmentUserListBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentUserListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navController = findNavController()
        viewBinding.detailsButton.setOnClickListener(viewModel.onDetailsButtonClickListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}