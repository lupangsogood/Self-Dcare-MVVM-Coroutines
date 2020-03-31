package com.freewillsolutions.True.selfdcare.ui.base

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.bumptech.glide.Glide
import com.freewillsolutions.True.selfdcare.R
import com.freewillsolutions.True.selfdcare.ui.dialog.ProgressDialog
import com.freewillsolutions.True.selfdcare.utility.ERR_CODE_NO_INTERNET_CONNECTION
import com.freewillsolutions.True.selfdcare.utility.extensions.showAlertDialog
import com.freewillsolutions.True.selfdcare.utility.runOnUiThread
import com.google.android.material.snackbar.Snackbar
import com.jozzee.android.core.resource.getStrings
import com.jozzee.android.core.simpleName
import com.jozzee.android.core.utility.Logger
import com.jozzee.android.core.view.hideKeyboard
import com.jozzee.android.core.view.setScreenTouchable
import javax.net.ssl.HttpsURLConnection

open class BaseScreen : Fragment() {
    /**
     * When fragment transition completed update screen touchable.
     */
    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        if (nextAnim > 0) {
            val animation = AnimationUtils.loadAnimation(context, nextAnim)
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {
                }

                override fun onAnimationEnd(anim: Animation?) {
                    activity?.setScreenTouchable(true)
                }

                override fun onAnimationStart(anim: Animation?) {
                    activity?.setScreenTouchable(false)
                }
            })
            return animation
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        view?.hideKeyboard()
    }


    fun showProgressDialog(@StringRes message: Int = -1,
                           @ColorRes progressColor: Int = -1,
                           showDot: Boolean = true) {
        val msg = "${getStrings(message)}${if (showDot) "..." else ""}"
        showProgressDialog(msg, progressColor)
    }

    /**
     * Show and update progress dialog.
     */
    fun showProgressDialog(message: String? = null, @ColorRes progressColor: Int = -1) {
        var progressDialog = childFragmentManager.findFragmentByTag(ProgressDialog::class.java.simpleName)
        if (progressDialog != null && progressDialog is ProgressDialog) {
            progressDialog.setProgressColor(progressColor)
            progressDialog.setMessage(message)
        } else {
            progressDialog = ProgressDialog.newInstance(message, progressColor)
            childFragmentManager.commit {

            }
            childFragmentManager.commit(allowStateLoss = true) {
                add(progressDialog, progressDialog::class.java.simpleName)
            }
            childFragmentManager.executePendingTransactions()
        }
    }

    fun hideProgressDialog() {
        childFragmentManager.findFragmentByTag(ProgressDialog::class.java.simpleName)?.also { fragment ->
            childFragmentManager.commit(allowStateLoss = true) {
                remove(fragment)
            }
            childFragmentManager.executePendingTransactions()
        }
    }

    open fun errorHandler(statusCode: Int, message: String) {
        if (view == null || isAdded.not()) return
        val errorMessage = when (statusCode) {
            ERR_CODE_NO_INTERNET_CONNECTION -> getStrings(R.string.no_internet_connection)
            //HttpsURLConnection.HTTP_CLIENT_TIMEOUT -> getStrings(R.string.connection_timed_out)
            else -> message
        }

        Logger.error(simpleName(), "Error Handler: Status Code: $statusCode, Message: $errorMessage")
        runOnUiThread {
            hideProgressDialog()

            //Show alert dialog if have error message.
            if (errorMessage.isNotBlank()) {
                showAlertDialog(errorMessage)
            }
        }
    }



    /**
     * Hid keyboard before destroy view.
     */
    override fun onDestroyView() {
        view?.hideKeyboard()
        hideProgressDialog()
        super.onDestroyView()
    }

    /**
     * Clear runtime qc when destroy.
     */
    override fun onDestroy() {
        //removeAllChildFragment()
        Runtime.getRuntime().gc()
        Glide.get(requireContext()).bitmapPool.clearMemory()
        Glide.get(requireContext()).clearMemory()
        super.onDestroy()
    }

    fun Fragment.showSnackBarToSettingPermissions(@StringRes message: Int) {
        if (isAdded.not() || view == null) return
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
                .setAction(R.string.setting) { goToSettingPermissions() }
                .show()
    }

    private fun goToSettingPermissions() {
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:${context?.packageName}")).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }.also { intent ->
            startActivity(intent)
        }
    }

}