package gonzalo.dev.mychallenge.common.di

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import gonzalo.dev.core.data.ClientDataSource
import gonzalo.dev.core.domain.repository.ClientRepository
import gonzalo.dev.core.domain.repository.ClientRepositoryImpl
import gonzalo.dev.mychallenge.data.repository.ClientDataSourceImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepositoryModule {

    @Provides
    fun provideClientRepository(clientDatabase: ClientDataSource): ClientRepository =
        ClientRepositoryImpl(clientDatabase)

    @Provides
    fun provideClientData(): ClientDataSource {
        return ClientDataSourceImpl(
            FirebaseDatabase.getInstance()
                .reference.child(ClientDataSourceImpl.DATABASE_NAME)
        )
    }
}