// Generated by Dagger (https://dagger.dev).
package com.androidexam.stayfinder.di;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NetworkModule_ProvidesGoogleSignInOptionsFactory implements Factory<GoogleSignInOptions> {
  private final NetworkModule module;

  public NetworkModule_ProvidesGoogleSignInOptionsFactory(NetworkModule module) {
    this.module = module;
  }

  @Override
  public GoogleSignInOptions get() {
    return providesGoogleSignInOptions(module);
  }

  public static NetworkModule_ProvidesGoogleSignInOptionsFactory create(NetworkModule module) {
    return new NetworkModule_ProvidesGoogleSignInOptionsFactory(module);
  }

  public static GoogleSignInOptions providesGoogleSignInOptions(NetworkModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.providesGoogleSignInOptions());
  }
}