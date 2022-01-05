package com.finerioconnect.sdk.ui.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class FCDatePickerDialog(
    private val listener: DatePickerDialog.OnDateSetListener
) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    companion object {
        const val FINERIO_DATE_PICKER = "finerioDatePicker"
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener.onDateSet(view, year, month, dayOfMonth)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        val datePicker = DatePickerDialog(requireContext(), this, year, month, day)
        datePicker.datePicker.maxDate = calendar.timeInMillis
        return datePicker
    }

}