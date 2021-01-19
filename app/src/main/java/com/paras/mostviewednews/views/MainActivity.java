package com.paras.mostviewednews.views;

import android.os.Bundle;

import com.paras.mostviewednews.R;
import com.paras.mostviewednews.databinding.ActivityMainBinding;
import com.paras.mostviewednews.viewmodels.NewsViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import android.view.View;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NewsViewModel newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewmodel(newsViewModel);
        View view = binding.getRoot();
        setContentView(view);
    }
}