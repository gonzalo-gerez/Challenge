package gonzalo.dev.mychallenge.common

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ChallengeApp : Application() {

    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        super.onCreate()

        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}