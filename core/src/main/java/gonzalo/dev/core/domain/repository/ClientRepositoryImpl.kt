package gonzalo.dev.core.domain.repository

import gonzalo.dev.core.data.ClientDataSource
import gonzalo.dev.core.domain.model.Client
import kotlinx.coroutines.flow.Flow

class ClientRepositoryImpl(private val clientDataSource: ClientDataSource) : ClientRepository {

    override fun saveInCloud(client: Client): Flow<Boolean> {
        return clientDataSource.saveClient(client)
    }
}