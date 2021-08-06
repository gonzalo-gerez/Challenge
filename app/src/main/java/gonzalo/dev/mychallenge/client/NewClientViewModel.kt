package gonzalo.dev.mychallenge.client

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import gonzalo.dev.core.domain.model.Client
import gonzalo.dev.core.domain.usecase.NewClientUseCase
import gonzalo.dev.mychallenge.R
import gonzalo.dev.mychallenge.common.mvvm.BaseViewModel
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NewClientViewModel @Inject constructor(
    val appContext: Application,
    private val newClientUseCase: NewClientUseCase
) : BaseViewModel() {

    private val _formState = MutableLiveData<Client?>()
    val formState: LiveData<Client?> = _formState

    private val _refreshUIstate = MutableLiveData<Void?>()
    val refreshUIstate: LiveData<Void?> = _refreshUIstate

    fun registerClient(client: Client) {
        println("fata registering")
        newClientUseCase.saveInCloud(client)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(object : DisposableObserver<Boolean>() {
                override fun onNext(t: Boolean?) {
                    val message =
                        if (t!!) appContext.getString(R.string.new_client_success) else appContext.getString(
                            R.string.error_when_save
                        )
                    errorState.postValue(message)
                    _refreshUIstate.postValue(null)
                }

                override fun onError(e: Throwable?) {
                    //Do nothing.
                }

                override fun onComplete() {
                    //Do nothing.
                }
            })
    }

    fun checkForm(name: String?, surname: String?, birthDay: String?, age: String?) {
        if (!name.isNullOrEmpty() && !surname.isNullOrEmpty() && !birthDay.isNullOrEmpty() && !age.isNullOrEmpty()) {
            _formState.postValue(Client(name, surname, age.toInt(), birthDay))
            return
        }

        _formState.postValue(null)
    }

}
