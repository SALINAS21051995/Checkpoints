package com.appside.checkpoints.util

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.appside.checkpoints.R

class AgreeDenyDialog(private val message: String, private val dListener: IDialogOptions, private val value: Any?): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let {
            val builder =
                AlertDialog.Builder(it, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
                    .setTitle(getString(R.string.dialog_title_text))
                    .setMessage(message)
                    .setPositiveButton(R.string.dialog_button_ok_text) { _, _ ->
                        dListener.onAgreeDeniedPositiveEvent(value)
                    }.setNegativeButton(R.string.event_contact_cancel_text) {_, _ ->
                        dListener.onAgreeDeniedNegativeEvent(value)
                    }
            builder.create()
        } ?: throw IllegalStateException("Hubo pedos al crear dialogo no mms")
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE)
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLUE)
        }
        return dialog    }
}

interface IDialogOptions{
    fun onAgreeDeniedPositiveEvent(value: Any?)
    fun onAgreeDeniedNegativeEvent(value: Any?)
}