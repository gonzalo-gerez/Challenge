package gonzalo.dev.core.data

import gonzalo.dev.core.domain.model.Client
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import kotlinx.coroutines.flow.Flow

interface ClientDataSource {
    fun saveClient(client: Client): Flow<Boolean>
}