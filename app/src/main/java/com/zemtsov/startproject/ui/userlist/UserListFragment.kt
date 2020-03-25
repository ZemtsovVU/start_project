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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zemtsov.startproject.R
import com.zemtsov.startproject.data.entity.User
import com.zemtsov.startproject.databinding.FragmentUserListBinding
import com.zemtsov.startproject.ui.base.MarginItemDecoration
import com.zemtsov.startproject.ui.base.OnItemClickListener
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

        val userListAdapter = UserListRecyclerAdapter()
        userListAdapter.onItemClickListener = object : OnItemClickListener<User> {
            override fun onItemClick(itemView: View, position: Int, item: User?) {
                Log.d("UserListFragment", item.toString())
                // TODO
            }
        }

        viewBinding.recyclerView.apply {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(MarginItemDecoration(R.dimen.size_xsmall))
        }

        viewModel.navController = findNavController()

        viewModel.usersLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                LOADING -> {
                     setProgressViewEnabled(true) // Need to show progress as a type of recycler cell
                     setEmptyViewEnabled(false)
                }
                SUCCESS -> {
                    if (it.data == null || it.data.isEmpty()) {
                         setProgressViewEnabled(false)
                         setEmptyViewEnabled(true)
                        showMessage(getString(R.string.empty_users))
                    } else {
                         setProgressViewEnabled(false)
                         setEmptyViewEnabled(false)
                        userListAdapter.setItems(it.data)
                    }
                }
                ERROR -> {
                     setProgressViewEnabled(false)
                     setEmptyViewEnabled(true)
                    it.error?.let { showMessage(it.localizedMessage) }
                }
            }
        })
    }

    // Just for example
    private fun setProgressViewEnabled(enabled : Boolean) {
        viewBinding.progressBar.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    // Just for example
    private fun setEmptyViewEnabled(enabled : Boolean) {
        viewBinding.emptyTextView.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    // Just for example
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