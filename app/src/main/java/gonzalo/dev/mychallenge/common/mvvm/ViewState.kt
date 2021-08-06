package gonzalo.dev.mychallenge.common.mvvm

class ViewState private constructor(viewState: State, statusCode: Int?) {

    val state: State = viewState
    val statusCode: Int? = statusCode

    companion object {

        fun of(state: State): ViewState {
            return ViewState(state, null)
        }

        fun of(statusCode: Int): ViewState {
            return ViewState(State.ERROR, statusCode)
        }

    }

    enum class State {
        LAYOUT, ERROR, LOADING;
    }

    fun getViewState(): State {
        return state
    }

}