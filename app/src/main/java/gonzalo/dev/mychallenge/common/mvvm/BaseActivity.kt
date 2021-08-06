package gonzalo.dev.mychallenge.common.mvvm

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import gonzalo.dev.mychallenge.R
import gonzalo.dev.mychallenge.common.animation.AnimationUtil

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    companion object {
        private val TAG = "fata BaseActivity"
    }

    private lateinit var vModel: T

    /** Layout States **/
    private lateinit var layoutView: ViewGroup
    private lateinit var loadingView: ViewGroup
    private lateinit var errorView: ViewGroup
    private var viewState: ViewState.State = ViewState.State.LAYOUT


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        vModel = createViewModelFactory()

        layoutView = findViewById(R.id.base_layout_view)
        layoutView.addView(getRootView())

        loadingView = findViewById(R.id.base_layout_loading_view)
        loadingView.addView(layoutInflater.inflate(R.layout.layout_loading_view, null))

        errorView = findViewById(R.id.base_layout_error_view)

        vModel.viewState.observe(this, { viewState ->
            this.viewState = viewState!!.getViewState()
            when (viewState.getViewState()) {
                ViewState.State.LAYOUT -> showLayoutView()
                ViewState.State.LOADING -> showLoadingView()
                ViewState.State.ERROR -> showErrorView()
            }
        })

        getViewModel().errorState.observe(this, {
            Snackbar.make(findViewById(R.id.baseContainer), it, Snackbar.LENGTH_SHORT).show()
        })

        vModel.setViewStateAsLayout()
    }

    protected fun getViewModel(): T {
        return vModel
    }

    private fun showLayoutView() {
        layoutView.visibility = View.VISIBLE
        loadingView.visibility = View.GONE
        errorView.visibility = View.GONE
    }

    private fun showErrorView() {
        //Create an error screen in order to be able to explain to the user about some error.
    }

    private fun showLoadingView() {
        if (loadingView.visibility != View.VISIBLE) {
            AnimationUtil.viewSlideLeft(this, loadingView)
            loadingView.visibility = View.VISIBLE
            errorView.visibility = View.GONE
        }
    }

    abstract fun createViewModelFactory(): T
    abstract fun getRootView(): View

}
