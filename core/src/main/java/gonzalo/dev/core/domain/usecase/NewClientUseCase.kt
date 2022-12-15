package gonzalo.dev.core.domain.usecase

import gonzalo.dev.core.domain.model.Client
import gonzalo.dev.core.domain.repository.ClientRepository
import kotlinx.coroutines.flow.Flow


class NewClientUseCase(private val clientRepository: ClientRepository) {

    fun execute(client: Client): Flow<Boolean> {
        return clientRepository.saveInCloud(client)
    }
}