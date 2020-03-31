package com.freewillsolutions.True.selfdcare.utility.extensions

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.provider.Settings
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.freewillsolutions.True.selfdcare.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.jozzee.android.core.resource.getStrings
import com.jozzee.android.core.view.hideKeyboard

fun showAlertDialog(context: Context?,
                    message: String,
                    title: String? = null,
                    positiveText: String? = null,
                    negativeText: String? = null,
                    onPositiveClick: (() -> Unit)? = null,
                    onNegativeClick: (() -> Unit)? = null,
                    onDialogDismiss: (() -> Unit)? = null,
                    cancelable: Boolean = true) {

    if (context == null) return

    val builder = MaterialAlertDialogBuilder(context)

    //Set message
    builder.setMessage(message)

    //Set title (if have).
    if (title?.isNotBlank() == true) {
        builder.setTitle(title)
    }

    //Default positive button (OK Button)
    builder.setPositiveButton(positiveText ?: context.getString(R.string.ok)) { dialog, _ ->
        onPositiveClick?.invoke()
        dialog.dismiss()
    }

    //Set negative button (if have)
    if (negativeText?.isNotBlank() == true) {
        builder.setNegativeButton(negativeText) { dialog, _ ->
            onNegativeClick?.invoke()
            dialog.dismiss()
        }
    }

    //Set cancel able
    builder.setCancelable(cancelable)

    builder.create().apply {
        setOnDismissListener {
            onDialogDismiss?.invoke()
        }
    }.show()
}

/**
 * Full options to show alert dialog with activity
 * and use text character for [title], [message], [positiveText], [negativeText]
 */
fun FragmentActivity.showAlertDialog(message: String,
                                     title: String? = null,
                                     positiveText: String? = null,
                                     negativeText: String? = null,
                                     onPositiveClick: (() -> Unit)? = null,
                                     onNegativeClick: (() -> Unit)? = null,
                                     onDialogDismiss: (() -> Unit)? = null,
                                     cancelable: Boolean = true) {
    if (isDestroyed) return
    hideKeyboard()
    showAlertDialog(this, message, title
            ?: getString(R.string.app_name),
            positiveText, negativeText, onPositiveClick, onNegativeClick,
            onDialogDismiss, cancelable)
}

/**
 * Full options to show alert dialog with activity
 * and use string resource id for [title], [message], [positiveText], [negativeText]
 */
fun FragmentActivity.showAlertDialog(@StringRes message: Int,
                                     @StringRes title: Int? = R.string.app_name,
                                     @StringRes positiveText: Int? = null,
                                     @StringRes negativeText: Int? = null,
                                     onPositiveClick: (() -> Unit)? = null,
                                     onNegativeClick: (() -> Unit)? = null,
                                     onDialogDismiss: (() -> Unit)? = null,
                                     cancelable: Boolean = true) {

    if (isDestroyed) return
    try {
        hideKeyboard()
        showAlertDialog(this,
                getString(message),
                if (title != null) getString(title) else null,
                if (positiveText != null) getString(positiveText) else null,
                if (negativeText != null) getString(negativeText) else null,
                onPositiveClick,
                onNegativeClick,
                onDialogDismiss,
                cancelable)
    } catch (e: Resources.NotFoundException) {
        e.printStackTrace()
    }
}

/**
 * Show normal alert dialog with activity.
 * [title] text character
 * [message] text character
 * [onPositiveClick] event when click positive button (OK Button)
 */
fun FragmentActivity.showAlertDialog(message: String,
                                     onPositiveClick: (() -> Unit)? = null,
                                     title: String? = null) {
    if (isDestroyed) return
    hideKeyboard()
    showAlertDialog(this, message, title
            ?: getString(R.string.app_name),
            null, null, onPositiveClick,
            null, null, true)
}

/**
 * Show normal alert dialog with activity.
 * [title] string resource id
 * [message]  string resource id
 * [onPositiveClick] event when click positive button (OK Button)
 */
fun FragmentActivity.showAlertDialog(@StringRes message: Int,
                                     onPositiveClick: (() -> Unit)? = null,
                                     @StringRes title: Int? = R.string.app_name) {
    if (isDestroyed) return
    hideKeyboard()
    showAlertDialog(this, getString(message), if (title != null) getString(title) else null,
            null, null, onPositiveClick,
            null, null, true)
}

/**
 * Full options to show alert dialog with fragment
 * and use text character for [title], [message], [positiveText], [negativeText]
 */
fun Fragment.showAlertDialog(message: String,
                             title: String? = null,
                             positiveText: String? = null,
                             negativeText: String? = null,
                             onPositiveClick: (() -> Unit)? = null,
                             onNegativeClick: (() -> Unit)? = null,
                             onDialogDismiss: (() -> Unit)? = null,
                             cancelable: Boolean = true) {
    if (isAdded.not() || view == null) return
    view?.hideKeyboard()
    showAlertDialog(context, message, title
            ?: getString(R.string.app_name),
            positiveText, negativeText, onPositiveClick, onNegativeClick,
            onDialogDismiss, cancelable)
}

/**
 * Full options to show alert dialog with fragment
 * and use string resource id for [title], [message], [positiveText], [negativeText]
 */
fun Fragment.showAlertDialog(@StringRes message: Int,
                             @StringRes title: Int? = R.string.app_name,
                             @StringRes positiveText: Int? = null,
                             @StringRes negativeText: Int? = null,
                             onPositiveClick: (() -> Unit)? = null,
                             onNegativeClick: (() -> Unit)? = null,
                             onDialogDismiss: (() -> Unit)? = null,
                             cancelable: Boolean = true) {

    if (isAdded.not() || view == null) return
    try {
        view?.hideKeyboard()
        showAlertDialog(context,
                getString(message),
                if (title != null) getString(title) else null,
                if (positiveText != null) getString(positiveText) else null,
                if (negativeText != null) getString(negativeText) else null,
                onPositiveClick,
                onNegativeClick,
                onDialogDismiss,
                cancelable)
    } catch (e: Resources.NotFoundException) {
        e.printStackTrace()
    }
}

/**
 * Show normal alert dialog with fragment.
 * [title] text character
 * [message] text character
 * [onPositiveClick] event when click positive button (OK Button)
 */
fun Fragment.showAlertDialog(message: String,
                             onPositiveClick: (() -> Unit)? = null,
                             title: String? = null) {
    if (isAdded.not() || view == null) return
    view?.hideKeyboard()
    showAlertDialog(context!!, message, title
            ?: getStrings(R.string.app_name),
            null, null, onPositiveClick,
            null, null, true)
}

/**
 * Show normal alert dialog with fragment.
 * [title] string resource id
 * [message]  string resource id
 * [onPositiveClick] event when click positive button (OK Button)
 */
fun Fragment.showAlertDialog(@StringRes message: Int,
                             onPositiveClick: (() -> Unit)? = null,
                             @StringRes title: Int? = R.string.app_name) {
    if (isAdded.not() || view == null) return
    view?.hideKeyboard()
    showAlertDialog(context!!, getString(message), if (title != null) getString(title) else null,
            null, null, onPositiveClick,
            null, null, true)
}


fun Fragment.showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT, v: View? = view) {
    if (isAdded.not() || v == null) return
    Snackbar.make(v, message, duration).show()
}

fun Fragment.showSnackBar(@StringRes message: Int, duration: Int = Snackbar.LENGTH_SHORT, v: View? = view) {
    if (isAdded.not() || v == null) return
    Snackbar.make(v, message, duration).show()
}

fun Fragment.showSnackBarToInternetSetting() {
    if (isAdded.not() || view == null) return
    Snackbar.make(view!!, R.string.no_internet_connection, Snackbar.LENGTH_LONG)
            .setAction(R.string.setting) {
                startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
            }
            .show()
}

fun Fragment.showSnackBarToPermissionsSetting(@StringRes message: Int) {
    if (isAdded.not() || view == null) return
    Snackbar.make(view!!, message, Snackbar.LENGTH_LONG)
            .setAction(R.string.setting) {
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:${context?.packageName}")).apply {
                    addCategory(Intent.CATEGORY_DEFAULT)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.also { intent ->
                    startActivity(intent)
                }
            }
            .show()
}

fun FragmentActivity.showSnackBarToPermissionsSetting(view: View, @StringRes message: Int) {
    if (isDestroyed) return
    Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction(R.string.setting) {
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:${packageName}")).apply {
                    addCategory(Intent.CATEGORY_DEFAULT)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.also { intent ->
                    startActivity(intent)
                }
            }
            .show()
}

fun Fragment.showBottomSheet(bottomSheet: BottomSheetDialogFragment) {
    if (isAdded.not() || view == null) return
    try {
        bottomSheet.show(childFragmentManager, "${bottomSheet::class.java.simpleName}${System.currentTimeMillis()}")
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
    }
}