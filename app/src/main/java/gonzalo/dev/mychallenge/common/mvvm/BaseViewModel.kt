package gonzalo.dev.mychallenge.common.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    private val _viewState: MutableLiveData<ViewState> = MutableLiveData()
    val viewState: LiveData<ViewState> = _viewState

    val errorState = MutableLiveData<String>()

    fun setViewStateAsError() {
        _viewState.postValue(ViewState.of(ViewState.State.ERROR))
    }

    fun setViewStateAsLoading() {
        _viewState.postValue(ViewState.of(ViewState.State.LOADING))
    }

    fun setViewStateAsLayout() {
        _viewState.postValue(ViewState.of(ViewState.State.LAYOUT))
    }
}