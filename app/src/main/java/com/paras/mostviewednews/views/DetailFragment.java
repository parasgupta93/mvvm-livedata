package com.paras.mostviewednews.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.paras.mostviewednews.databinding.FragmentDetailBinding;
import com.paras.mostviewednews.viewmodels.NewsViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailFragment extends Fragment {

    private NewsViewModel newsViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        FragmentDetailBinding binding = FragmentDetailBinding.inflate(inflater, container, false);
        newsViewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);
        binding.setViewmodel(newsViewModel);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsViewModel.masterFragmentViewModelObserver.setToolbarTitle("Article Detail");

    }
}