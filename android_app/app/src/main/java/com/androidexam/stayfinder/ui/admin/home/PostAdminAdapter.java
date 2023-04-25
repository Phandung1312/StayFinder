package com.androidexam.stayfinder.ui.admin.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.androidexam.stayfinder.R;
import com.androidexam.stayfinder.common.BindingAdapters;
import com.androidexam.stayfinder.data.models.Hostel;
import com.androidexam.stayfinder.data.models.Post;
import com.androidexam.stayfinder.databinding.ItemsAdminPostBinding;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostAdminAdapter extends RecyclerView.Adapter<PostAdminAdapter.ViewHolder>  {
    private ArrayList<Post> postList;
    private AdminHomeViewModel adminHomeViewModel;
    private LifecycleOwner lifecycleOwner;

    public PostAdminAdapter( ArrayList<Post> postList, AdminHomeViewModel adminHomeViewModel, LifecycleOwner lifecycleOwner){
        this.postList = postList;
        this.lifecycleOwner = lifecycleOwner;
        this.adminHomeViewModel = adminHomeViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsAdminPostBinding binding =
                ItemsAdminPostBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdminAdapter.ViewHolder holder, int position) {
        Post post = postList.get(position);

        if(post.getHostel() == null){
            MutableLiveData<Hostel> hostelLiveData = new MutableLiveData<>();
            adminHomeViewModel.getHostelById(post.getHostelId())
                    .observe(lifecycleOwner,hostel -> {
                        hostelLiveData.setValue(hostel);
                    });
            hostelLiveData.observe(lifecycleOwner, hostel -> {
                post.setHostel(hostel);
                holder.binding.setPost(post);
                holder.binding.cardViewPostAdmin.setOnClickListener(view ->{
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("post",post);
                    Navigation.findNavController(holder.binding.getRoot()).navigate(R.id.postDetailAdminFragment,bundle);
                });
                holder.binding.executePendingBindings();
            });
        }else{
            holder.binding.setPost(post);
            holder.binding.cardViewPostAdmin.setOnClickListener(view ->{
                Bundle bundle = new Bundle();
                bundle.putSerializable("post",post);
                Navigation.findNavController(holder.binding.getRoot()).navigate(R.id.postDetailAdminFragment,bundle);
            });
            holder.binding.executePendingBindings();
        }

        int postId = postList.get(position).getId();
        holder.binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminHomeViewModel.changeStatusPost(postId,0)
                        .observe(lifecycleOwner, check ->{
                            if(check == true){
                                Toast.makeText(v.getContext().getApplicationContext(), "Change status post success", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(v.getContext().getApplicationContext(), "Change status post failure", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        holder.binding.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminHomeViewModel.changeStatusPost(postId,1)
                        .observe(lifecycleOwner, check ->{
                            if(check == true){
                                Toast.makeText(v.getContext().getApplicationContext(), "Change status post success", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(v.getContext().getApplicationContext(), "Change status post failure", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private  ItemsAdminPostBinding binding;

        public ViewHolder( ItemsAdminPostBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
