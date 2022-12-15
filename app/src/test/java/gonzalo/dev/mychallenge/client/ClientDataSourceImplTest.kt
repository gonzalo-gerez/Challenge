package gonzalo.dev.mychallenge.client

import com.google.firebase.database.DatabaseReference
import gonzalo.dev.mychallenge.data.repository.ClientDataSourceImpl
import gonzalo.dev.core.domain.model.Client
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ClientDataSourceImplTest {

    @Test
    fun `Given a new client, push it to firebase cloud`() {
        runBlocking {
            val client = mockk<Client>(relaxed = true)
            val databaseReference = mockk<DatabaseReference>(relaxed = true)

            val dataSourceImpl = ClientDataSourceImpl(databaseReference)

            dataSourceImpl.saveClient(client)

            verify {
                databaseReference.push().setValue(client)
            }
        }
    }
}