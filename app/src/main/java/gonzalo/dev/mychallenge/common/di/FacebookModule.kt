package gonzalo.dev.mychallenge.common.di

import android.app.Application
import com.facebook.AccessToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FacebookModule {

    @Provides
    fun provideAccessToken(): AccessToken? {
        return AccessToken.getCurrentAccessToken()
    }
}
