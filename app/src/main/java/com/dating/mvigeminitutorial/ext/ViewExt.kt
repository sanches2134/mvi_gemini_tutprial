package com.dating.mvigeminitutorial.ext

import android.content.DialogInterface
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.dating.mvigeminitutorial.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Fragment.showDialog(
    @StringRes titleResId: Int,
    @StringRes messageResId: Int,
    isCancelable: Boolean = true,
) {
    MaterialAlertDialogBuilder(requireContext())
        .setTitle(titleResId)
        .setMessage(messageResId)
        .setCancelable(isCancelable)
        .show()
}
