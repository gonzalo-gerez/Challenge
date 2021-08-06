package gonzalo.dev.mychallenge.login

import com.facebook.AccessToken
import gonzalo.dev.mychallenge.robolectric.AbstractRobolectricTest
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

internal class LoginViewModelTest : AbstractRobolectricTest() {

    @Test
    fun `Given an available accessToken, emmit success to activity`() {
        val accessToken = mockk<AccessToken>(relaxed = true)
        val viewModel = LoginViewModel(accessToken)

        viewModel.accessTokenState.observeForever {}

        every { accessToken.isExpired } returns false

        viewModel.checkFacebookAccessToken()

        Assert.assertEquals(viewModel.accessTokenState.value, null)

        viewModel.accessTokenState.removeObserver { }
    }
}