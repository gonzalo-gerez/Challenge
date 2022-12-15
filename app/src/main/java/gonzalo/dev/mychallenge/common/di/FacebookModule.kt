package gonzalo.dev.mychallenge.common.di

import com.facebook.AccessToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FacebookModule {

    @Provides
    fun provideAccessToken(): AccessToken? {
        return AccessToken.getCurrentAccessToken()
    }
}
