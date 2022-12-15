package gonzalo.dev.mychallenge.client

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import gonzalo.dev.mychallenge.R
import gonzalo.dev.mychallenge.common.mvvm.BaseActivity
import gonzalo.dev.mychallenge.databinding.ActivityNewClientAtivityBinding
import java.util.*


@AndroidEntryPoint
class NewClientActivity : BaseActivity<NewClientViewModel>() {

    private val binding: ActivityNewClientAtivityBinding by lazy {
        ActivityNewClientAtivityBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.calendarImage.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = 1990
            val month: Int = calendar.get(Calendar.MONTH)
            val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)
            var selectedBirthday = ""
            val pickerDialog = DatePickerDialog(
                this,
                { datePicker: DatePicker?, year1: Int, month1: Int, day: Int ->
                    selectedBirthday = day.toString() + "-" + (month1 + 1) + "-" + year1
                    binding.birthDayTil.editText?.setText(selectedBirthday)
                    binding.ageTil.editText?.setText(calculateAge(year1).toString())
                }, year, month, dayOfMonth
            )
            pickerDialog.show()
        }

        getViewModel().formState.observe(this) {
            it?.let {
                getViewModel().registerClient(it)
            } ?: run {
                Snackbar.make(
                    binding.root,
                    getString(R.string.check_form),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        getViewModel().refreshUIstate.observe(this) {
            binding.nameTil.editText?.setText("")
            binding.surnameTil.editText?.setText("")
            binding.birthDayTil.editText?.setText("")
            binding.ageTil.editText?.setText("")
        }

        binding.clientConfirmButton.setOnClickListener {
            getViewModel().checkForm(
                binding.nameTil.editText?.text.toString(),
                binding.surnameTil.editText?.text.toString(),
                binding.birthDayTil.editText?.text.toString(),
                binding.ageTil.editText?.text.toString()
            )
        }

    }

    private fun calculateAge(yearBirth: Int): Int {
        val currentYear = GregorianCalendar.getInstance()[Calendar.YEAR]
        return currentYear - yearBirth
    }

    override fun createViewModelFactory(): NewClientViewModel {
        val viewModel: NewClientViewModel by viewModels()
        return viewModel
    }

    override fun getRootView(): View = binding.root
}