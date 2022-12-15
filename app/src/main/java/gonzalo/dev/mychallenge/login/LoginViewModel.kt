package gonzalo.dev.mychallenge.login

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.facebook.AccessToken
import dagger.hilt.android.lifecycle.HiltViewModel
import gonzalo.dev.mychallenge.common.mvvm.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val facebookAccessToken: AccessToken?
) : BaseViewModel() {

    @VisibleForTesting
    val mutableAccessTokenState = MutableLiveData<Void?>()
    val accessTokenState: LiveData<Void?> = mutableAccessTokenState

    fun checkFacebookAccessToken() {
        if (facebookAccessToken != null && !facebookAccessToken.isExpired) {
            mutableAccessTokenState.postValue(null)
        }
    }
}