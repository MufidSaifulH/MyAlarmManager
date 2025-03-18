package com.example.myalarmmanager

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar


class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private var mListener : DiaTimeListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = context as DiaTimeListener?
    }

    override fun onDetach() {
        super.onDetach()
        if (mListener != null) {
            mListener = null
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val formatHour24 = true
        return TimePickerDialog(activity,this,hour, minute,formatHour24)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        Log.d("TimePickerFragment", "Time selected: $hourOfDay:$minute")
        mListener?.onDialogTimeSet(tag, hourOfDay, minute)
    }

    interface DiaTimeListener {
        fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_picker, container, false)
    }


}