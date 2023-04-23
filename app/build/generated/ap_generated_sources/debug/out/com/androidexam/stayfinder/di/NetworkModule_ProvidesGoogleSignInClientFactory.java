// Generated by Dagger (https://dagger.dev).
package com.androidexam.stayfinder.di;

import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NetworkModule_ProvidesGoogleSignInClientFactory implements Factory<GoogleSignInClient> {
  private final Provider<Context> contextProvider;

  private final Provider<GoogleSignInOptions> gsoProvider;

  public NetworkModule_ProvidesGoogleSignInClientFactory(Provider<Context> contextProvider,
      Provider<GoogleSignInOptions> gsoProvider) {
    this.contextProvider = contextProvider;
    this.gsoProvider = gsoProvider;
  }

  @Override
  public GoogleSignInClient get() {
    return providesGoogleSignInClient(contextProvider.get(), gsoProvider.get());
  }

  public static NetworkModule_ProvidesGoogleSignInClientFactory create(
      Provider<Context> contextProvider, Provider<GoogleSignInOptions> gsoProvider) {
    return new NetworkModule_ProvidesGoogleSignInClientFactory(contextProvider, gsoProvider);
  }

  public static GoogleSignInClient providesGoogleSignInClient(Context context,
      GoogleSignInOptions gso) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.providesGoogleSignInClient(context, gso));
  }
}