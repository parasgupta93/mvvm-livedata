package com.paras.mostviewednews.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import com.paras.mostviewednews.R;
import com.paras.mostviewednews.data.local.NewsModelLocal;
import com.paras.mostviewednews.databinding.FragmentMasterBinding;
import com.paras.mostviewednews.viewmodels.NewsViewModel;
import com.paras.mostviewednews.views.adapters.NewsRecAdapter;

import java.util.List;

public class MasterFragment extends Fragment {

    NewsViewModel newsViewModel;
    NewsRecAdapter newsRecAdapter;
    FragmentMasterBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        binding = FragmentMasterBinding.inflate(inflater, container, false);
        newsViewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);
        newsRecAdapter = new NewsRecAdapter(newsViewModel);
        binding.setViewmodel(newsViewModel);
        binding.setAdapter(newsRecAdapter);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setUpNavigation();
    }

    private void setUpNavigation(){

        newsViewModel.navigationLiveData.observe(requireActivity(), typeEvent -> {
            if(!typeEvent.hasBeenHandeled()){
                typeEvent.getContent();
                NavHostFragment.findNavController(MasterFragment.this)
                        .navigate(R.id.action_MasterFragment_to_DetailFragment);
            }
        });
    }

    private void init(){

        newsViewModel.masterFragmentViewModelObserver.setToolbarTitle("Most Viewed News");
        newsViewModel.getNewsList().observe(MasterFragment.this.requireActivity(), new Observer<List<NewsModelLocal>>() {
            @Override
            public void onChanged(List<NewsModelLocal> newsModelLocals) {
                if(newsModelLocals != null){
                    Log.d("Paras","Size: "+newsModelLocals.size());
                    newsRecAdapter.updateData(newsModelLocals);
                }

                newsViewModel.masterFragmentViewModelObserver.setLoading(false);
            }
        });


    }

}