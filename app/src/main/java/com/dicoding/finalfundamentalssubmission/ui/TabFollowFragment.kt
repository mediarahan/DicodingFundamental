package com.dicoding.finalfundamentalssubmission.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.finalfundamentalssubmission.remote.response.ItemsItem
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.dicoding.finalfundamentalssubmission.databinding.FragmentTabFollowBinding
import com.dicoding.finalfundamentalssubmission.viewmodel.FollowersViewModel
import com.dicoding.finalfundamentalssubmission.viewmodel.FollowingViewModel

class TabFollowFragment : Fragment() {
    private lateinit var binding: FragmentTabFollowBinding

    private val followingViewModel by viewModels<FollowingViewModel>()
    private val followersViewModel by viewModels<FollowersViewModel>()

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { it ->
            val position = it.getInt(ARG_POSITION)
            val username = it.getString(ARG_USERNAME)

            if (position == 1){
                if (username != null) {
                    followingViewModel.getUserData(username)
                }
                followingViewModel.followData.observe(viewLifecycleOwner) { detailUserData ->
                    setFollowData(detailUserData)
                }
                followingViewModel.isLoading.observe(viewLifecycleOwner) {
                    showLoading(it)
                }

            } else {
                if (username != null) {
                    followersViewModel.getUserData(username)
                }
                followersViewModel.followData.observe(viewLifecycleOwner) { detailUserData ->
                    setFollowData(detailUserData)
                }
                followersViewModel.isLoading.observe(viewLifecycleOwner) {
                    showLoading(it)
                }
            }
        }

    }

    private fun setFollowData(detailedUsersData: List<ItemsItem>) {
        val adapter = UserListAdapter()
        adapter.submitList(detailedUsersData)
        binding.rvUserdata.layoutManager = LinearLayoutManager(requireActivity())
        val layoutManager = LinearLayoutManager(requireActivity())
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvUserdata.addItemDecoration(itemDecoration)
        binding.rvUserdata.adapter = adapter
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


}