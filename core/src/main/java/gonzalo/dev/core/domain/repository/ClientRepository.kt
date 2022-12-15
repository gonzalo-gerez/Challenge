package gonzalo.dev.core.domain.repository

import gonzalo.dev.core.domain.model.Client
import kotlinx.coroutines.flow.Flow

interface ClientRepository {

    fun saveInCloud(client: Client): Flow<Boolean>
}