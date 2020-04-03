package com.zemtsov.startproject.ui.userlist

import android.os.Bundle
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
import com.zemtsov.startproject.ui.base.*

/**
 * Developed by Viktor Zemtsov (zemtsovvu@gmail.com)
 * 2020
 *
 * @author Viktor Zemtsov
 */
class UserListFragment : Fragment() {

    private lateinit var viewBinding: FragmentUserListBinding

    private val viewModel: UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentUserListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navController = findNavController()

        val userListAdapter = UserListRecyclerAdapter()
        userListAdapter.onItemClickListener = object : OnItemClickListener<User> {
            override fun onItemClick(itemView: View, position: Int, item: User?) {
                item?.let { viewModel.onUserClick(it) }
            }
        }

        viewBinding.recyclerView.apply {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(MarginItemDecoration(R.dimen.size_xsmall))
        }

        viewModel.usersLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Loading -> {
                    setProgressViewEnabled(true) // Need to show progress as a type of recycler cell
                    setEmptyViewEnabled(false)
                }
                is Success -> {
                    if (it.data.isEmpty()) {
                        setProgressViewEnabled(false)
                        setEmptyViewEnabled(true)
                        showMessage(getString(R.string.empty_users))
                    } else {
                        setProgressViewEnabled(false)
                        setEmptyViewEnabled(false)
                        userListAdapter.setItems(it.data)
                    }
                }
                is Error -> {
                    setProgressViewEnabled(false)
                    setEmptyViewEnabled(true)
                    showMessage(it.error.localizedMessage)
                }
            }
        })
    }

    // Just for example
    private fun setProgressViewEnabled(enabled: Boolean) {
        viewBinding.progressBar.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    // Just for example
    private fun setEmptyViewEnabled(enabled: Boolean) {
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
}