package gonzalo.dev.mychallenge.client

import gonzalo.dev.core.domain.model.Client
import gonzalo.dev.core.domain.usecase.NewClientUseCase
import gonzalo.dev.mychallenge.robolectric.AbstractRobolectricTest
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

class NewClientViewModelTest : AbstractRobolectricTest() {

    @Test
    fun `Given a new client, the use case method saveInCloud must be called`() {
        val client = mockk<Client>(relaxed = true)
        val clientUseCase = mockk<NewClientUseCase>(relaxed = true)

        val viewModel = NewClientViewModel(getAppContext(), clientUseCase)

        viewModel.registerClient(client)

        verify {
            clientUseCase.execute(client)
        }
    }

    @Test
    fun `Given a correct client data, emitter must emit it`(){
        val clientUseCase = mockk<NewClientUseCase>(relaxed = true)

        val viewModel = NewClientViewModel(getAppContext(), clientUseCase)

        viewModel.formState.observeForever {  }

        viewModel.checkForm("name","surname","birthdayt","25")

        Assert.assertEquals(viewModel.formState.value, Client("name","surname",25,"birthdayt"))

        viewModel.formState.removeObserver {  }
    }

    @Test
    fun `Given a incorrect client data, emitter must emit null`(){
        val clientUseCase = mockk<NewClientUseCase>(relaxed = true)

        val viewModel = NewClientViewModel(getAppContext(), clientUseCase)

        viewModel.formState.observeForever {  }

        viewModel.checkForm("name","surname",null,"")

        Assert.assertEquals(viewModel.formState.value, null)

    }
}