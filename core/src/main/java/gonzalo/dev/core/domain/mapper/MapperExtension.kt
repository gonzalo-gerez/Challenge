package gonzalo.dev.core.domain.mapper

import gonzalo.dev.core.data.model.ClientModel
import gonzalo.dev.core.domain.model.Client

fun ClientModel.toClient(): Client {
    return Client(this.name, this.surname, this.age, this.birthDate)
}