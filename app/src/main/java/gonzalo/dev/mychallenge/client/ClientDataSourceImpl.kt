package gonzalo.dev.mychallenge.client

import com.google.firebase.database.DatabaseReference
import gonzalo.dev.core.data.ClientDataSource
import gonzalo.dev.core.domain.model.Client
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class ClientDataSourceImpl constructor(
    private val databaseReference: DatabaseReference,
    private val emitter: PublishSubject<Boolean>
) : ClientDataSource {

    companion object {
        const val DATABASE_NAME = "client"
    }

    override fun saveClient(client: Client): Observable<Boolean> {
        databaseReference.push().setValue(client)
            .addOnCompleteListener {
                println("fata agregado.")
                emitter.onNext(true)
            }
            .addOnFailureListener {
                println("fata   ${it.stackTrace}")
                emitter.onNext(false)
            }
        return emitter
    }
}