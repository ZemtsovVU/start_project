package com.zemtsov.startproject.ui.userlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.zemtsov.startproject.databinding.FragmentUserListBinding
import com.zemtsov.startproject.ui.base.Resource.Status.*

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

        viewModel.usersLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                LOADING -> {
                    // Show progress as a type of recycler cell
                    // setProgressViewEnabled(true)
                    // setEmptyViewEnabled(false)
                }
                SUCCESS -> {
                    Log.d("UserListFragment", it.data.toString())
                }
                ERROR -> {
                    // setProgressViewEnabled(false)
                    // setEmptyViewEnabled(true)
                    it.error?.let { showMessage(it.localizedMessage) }
                }
            }
        })

        viewBinding.detailsButton.setOnClickListener(viewModel.onDetailsButtonClickListener)
    }

    private fun showMessage(message: String?) {
        view?.let { v ->
            message?.let { msg ->
                Snackbar
                    .make(v, msg, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok) { /*Do nothing. Dismiss by default*/ }
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}