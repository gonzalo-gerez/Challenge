package gonzalo.dev.core.domain.usecase

import gonzalo.dev.core.domain.model.Client
import gonzalo.dev.core.domain.repository.ClientRepository
import io.reactivex.rxjava3.core.Observable


class NewClientUseCase(private val clientRepository: ClientRepository) {

    fun saveInCloud(client: Client): Observable<Boolean> {
        return clientRepository.saveInCloud(client)
    }
}