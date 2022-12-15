package gonzalo.dev.mychallenge.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import dagger.hilt.android.AndroidEntryPoint
import gonzalo.dev.mychallenge.client.NewClientActivity
import gonzalo.dev.mychallenge.common.mvvm.BaseActivity
import gonzalo.dev.mychallenge.databinding.ActivityLoginBinding

//facebook
//https://mychallenge-f61e8.firebaseapp.com/__/auth/handler
@AndroidEntryPoint
class LoginActivity : BaseActivity<LoginViewModel>() {

    private val callbackManager: CallbackManager by lazy {
        CallbackManager.Factory.create()
    }

    private val loginBinding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getViewModel().checkFacebookAccessToken()

        val loginButton = loginBinding.buttonLogin

        getViewModel().accessTokenState.observe(this) {
            goToNextActivity()
        }

        loginButton.setReadPermissions("email")

        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                goToNextActivity()
            }

            override fun onCancel() {
                // Show to the user a friendly message/screen with the actions to do after cancel.
            }

            override fun onError(exception: FacebookException) {
                // Show to the user a friendly message/screen explaining the error and what can do.
            }
        })
    }

    private fun goToNextActivity() {
        startActivity(Intent(this, NewClientActivity::class.java))
        finish()
    }

    override fun createViewModelFactory(): LoginViewModel {
        val viewModel: LoginViewModel by viewModels()
        return viewModel
    }

    override fun getRootView(): View {
        return loginBinding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}