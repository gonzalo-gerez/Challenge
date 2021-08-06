package gonzalo.dev.mychallenge.client

import com.google.firebase.database.DatabaseReference
import gonzalo.dev.core.domain.model.Client
import gonzalo.dev.mychallenge.robolectric.AbstractRobolectricTest
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test

class ClientDataSourceImplTest {

    @Test
    fun `Given a new client, push it to firebase cloud`() {
        val client = mockk<Client>(relaxed = true)
        val databaseReference = mockk<DatabaseReference>(relaxed = true)

        val dataSourceImpl = ClientDataSourceImpl(databaseReference, mockk(relaxed = true))

        dataSourceImpl.saveClient(client)

        verify {
            databaseReference.push().setValue(client)
        }
    }
}