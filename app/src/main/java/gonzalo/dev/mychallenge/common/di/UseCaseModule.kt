package gonzalo.dev.mychallenge.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import gonzalo.dev.core.domain.repository.ClientRepository
import gonzalo.dev.core.domain.usecase.NewClientUseCase

@Module
@InstallIn(ActivityRetainedComponent::class)
class UseCaseModule {

    @Provides
    fun provideNewClientUseCase(clientRepository: ClientRepository): NewClientUseCase {
        return NewClientUseCase(clientRepository)
    }
}