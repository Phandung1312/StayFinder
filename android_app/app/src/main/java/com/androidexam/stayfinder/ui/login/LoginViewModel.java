package com.androidexam.stayfinder.ui.login;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androidexam.stayfinder.base.viewmodel.BaseViewModel;
import com.androidexam.stayfinder.common.ImageConvertResult;
import com.androidexam.stayfinder.data.apis.AccountAPI;
import com.androidexam.stayfinder.data.models.Account;
import com.androidexam.stayfinder.data.models.request.SignUpRequest;
import com.androidexam.stayfinder.data.repositories.AccountRepository;
import com.androidexam.stayfinder.databinding.LoginClass;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class LoginViewModel extends BaseViewModel {
    AccountRepository accountRepository;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    MutableLiveData<Account> data = new MutableLiveData<>();
    MutableLiveData<Boolean> isNew = new MutableLiveData<>();
    @Inject
    public LoginViewModel(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    public void login(String email, String password){
        compositeDisposable.add(accountRepository.getAccountByLogin(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(acc -> {
                            Log.d("Success","Login");
                            data.postValue(acc);
                        },
                    throwable ->{
                    Log.d("ERROR login",throwable.getMessage());
                    }
                ));
    }
    public void signUp(SignUpRequest signUpRequest){
            compositeDisposable.add(accountRepository.getAccountBySignUp(signUpRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(acc -> {
                                Log.d("Success","Sign up");
                                data.postValue(acc);
                            },
                            throwable ->{
                                Log.d("ERROR signup",throwable.getMessage());
                            }
                    ));
    }
    public LiveData<Account> loadData(){
        return this.data;
    }
    public void firebaseSignInWithGoogle(GoogleSignInAccount googleSignInAccount){
        isNew = accountRepository.firebaseSignInWithGoogle(googleSignInAccount);
    }
    public LiveData<Boolean> isNewAccount(){
        return isNew;
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
