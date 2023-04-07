// Generated by Dagger (https://dagger.dev).
package com.androidexam.stayfinder.di;

import com.androidexam.stayfinder.data.apis.AccountAPI;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NetworkModule_ProvidesAccountAPIFactory implements Factory<AccountAPI> {
  private final NetworkModule module;

  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvidesAccountAPIFactory(NetworkModule module,
      Provider<Retrofit> retrofitProvider) {
    this.module = module;
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public AccountAPI get() {
    return providesAccountAPI(module, retrofitProvider.get());
  }

  public static NetworkModule_ProvidesAccountAPIFactory create(NetworkModule module,
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvidesAccountAPIFactory(module, retrofitProvider);
  }

  public static AccountAPI providesAccountAPI(NetworkModule instance, Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(instance.providesAccountAPI(retrofit));
  }
}