package com.androidexam.stayfinder.ui.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androidexam.stayfinder.base.viewmodel.BaseViewModel;
import com.androidexam.stayfinder.data.models.Account;
import com.androidexam.stayfinder.data.models.Post;
import com.androidexam.stayfinder.data.models.Schedule;
import com.androidexam.stayfinder.data.models.request.PostRequest;
import com.androidexam.stayfinder.data.models.request.ScheduleRequest;
import com.androidexam.stayfinder.data.repositories.AccountRepository;
import com.androidexam.stayfinder.data.repositories.PostReposity;
import com.androidexam.stayfinder.data.repositories.ScheduleReposity;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class ProfileViewModel extends BaseViewModel {
    AccountRepository accountRepository;
    PostReposity postReposity;
    ScheduleReposity scheduleReposity;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    MutableLiveData<List<Post>> listPost = new MutableLiveData<>();
    MutableLiveData<List<PostRequest>> listWaitApprovalPost = new MutableLiveData<>();
    MutableLiveData<List<PostRequest>> listApprovedPost = new MutableLiveData<>();
    MutableLiveData<List<PostRequest>> listNotApprovedPost = new MutableLiveData<>();

    MutableLiveData<List<ScheduleRequest>> listOwnerSchedule = new MutableLiveData<>();
    MutableLiveData<List<ScheduleRequest>> listRenterSchedule = new MutableLiveData<>();

    @Inject
    public ProfileViewModel(AccountRepository accountRepository, PostReposity postReposity, ScheduleReposity scheduleReposity) {
        this.accountRepository = accountRepository;
        this.postReposity = postReposity;
        this.scheduleReposity = scheduleReposity;
    }
    public void GetAllScheduleByAccountName(String accountName){
        compositeDisposable.add(scheduleReposity.getScheduleByOwnerAccountName(accountName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lst -> {
                            listOwnerSchedule.postValue(lst);
                        },
                        throwable ->{
                            Log.d("ERROR get all owner schedules of user (ProfileViewModel class)",throwable.getMessage());
                        }
                ));
        compositeDisposable.add(scheduleReposity.getScheduleByRenterAccountName(accountName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lst -> {
                            listRenterSchedule.postValue(lst);
                        },
                        throwable ->{
                            Log.d("ERROR get all renter schedules of user (ProfileViewModel class)",throwable.getMessage());
                        }
                ));
    }
    public LiveData<List<ScheduleRequest>> loadListOwnerSchedule(){
        return this.listOwnerSchedule;
    }
    public LiveData<List<ScheduleRequest>> loadListRenterSchedule(){
        return this.listRenterSchedule;
    }
    public void GetAllPostByAccountName(String accountName){
        compositeDisposable.add(postReposity.getPostByAccountName(accountName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lst -> {
                            listPost.postValue(lst);
                        },
                        throwable ->{
                            Log.d("ERROR get all posts of user (ProfileViewModel class)",throwable.getMessage());
                        }
                ));
    }
    public LiveData<List<PostRequest>> GetWaitApprovalPost(String accountName){
        compositeDisposable.add(postReposity.getPostByAccountNameAndStatus(accountName, 2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lst -> {
                            listWaitApprovalPost.postValue(lst);
                        },
                        throwable ->{
                            Log.d("ERROR get wait approval post of user (ProfileViewModel class)",throwable.getMessage());
                        }
                ));
        return this.listWaitApprovalPost;
    }

    public LiveData<List<PostRequest>> GetApprovedPost(String accountName){
        compositeDisposable.add(postReposity.getPostByAccountNameAndStatus(accountName, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lst -> {
                            listApprovedPost.postValue(lst);
                        },
                        throwable ->{
                            Log.d("ERROR get approved post by accountName and status of user (ProfileViewModel class)",throwable.getMessage());
                        }
                ));
        return this.listApprovedPost;
    }

    public LiveData<List<PostRequest>> GetNotApprovedPost(String accountName){
        compositeDisposable.add(postReposity.getPostByAccountNameAndStatus(accountName, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lst -> {
                            listNotApprovedPost.postValue(lst);
                        },
                        throwable ->{
                            Log.d("ERROR get not approved post by accountName and status of user (ProfileViewModel class)",throwable.getMessage());
                        }
                ));
        return this.listNotApprovedPost;
    }

    public LiveData<List<Post>> loadListPost(){
        return this.listPost;
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
    public LiveData<Account> GetAccountByLogin(String accountName, String password){
        MutableLiveData<Account> account = new MutableLiveData<>();
        compositeDisposable.add(accountRepository.getAccountByLogin(accountName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(acc -> {
                            account.postValue(acc);
                        },
                        throwable ->{
                            Log.d("ERROR get account of user (ProfileViewModel class)",throwable.getMessage());
                        }
                ));
        return account;
    }

}
