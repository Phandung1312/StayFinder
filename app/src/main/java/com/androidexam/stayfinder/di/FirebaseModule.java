package com.androidexam.stayfinder.di;

import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public  class FirebaseModule {

    @Provides
    public FirebaseDatabase providesFirebaseDatabase(){
        return FirebaseDatabase.getInstance();
    }
}