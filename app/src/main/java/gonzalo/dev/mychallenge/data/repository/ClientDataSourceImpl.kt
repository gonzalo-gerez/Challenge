package gonzalo.dev.mychallenge.data.repository

import com.google.firebase.database.DatabaseReference
import gonzalo.dev.core.data.ClientDataSource
import gonzalo.dev.core.domain.model.Client
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * This class should be in the data module. Due some limitations of the firebase library it must be
 * used in the android module.
 */
class ClientDataSourceImpl constructor(
    private val databaseReference: DatabaseReference,
) : ClientDataSource {

    companion object {
        const val DATABASE_NAME = "client"
    }

    override fun saveClient(client: Client): Flow<Boolean> {
        return callbackFlow {
            databaseReference.push().setValue(client)
                .addOnCompleteListener {
                    trySendBlocking(true)
                }
                .addOnFailureListener {
                    trySendBlocking(false)
                }

            awaitClose()
        }
    }
}