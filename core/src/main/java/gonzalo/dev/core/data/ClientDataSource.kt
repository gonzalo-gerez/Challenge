package gonzalo.dev.core.data

import gonzalo.dev.core.domain.model.Client
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter

interface ClientDataSource {
    fun saveClient(client: Client): Observable<Boolean>
}