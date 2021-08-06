package gonzalo.dev.core.domain.repository

import gonzalo.dev.core.data.ClientDataSource
import gonzalo.dev.core.domain.model.Client
import io.reactivex.rxjava3.core.Observable

class ClientRepository (private val clientDataSource: ClientDataSource) {

    fun saveInCloud(client: Client): Observable<Boolean> {
        return clientDataSource.saveClient(client)
    }
}