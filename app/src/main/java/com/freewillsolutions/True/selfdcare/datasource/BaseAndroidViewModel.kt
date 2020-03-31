package com.freewillsolutions.True.selfdcare.datasource

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jozzee.android.core.simpleName
import com.jozzee.android.core.utility.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

open class BaseAndroidViewModel(application: Application) : AndroidViewModel(application) {

    private var handleError: ((statusCode: Int, message: String) -> Unit)? = null

    fun setOnHandleError(block: (statusCode: Int, message: String) -> Unit) {
        handleError = block
    }

    protected fun onHandleError(statusCode: Int, message: String?) {
        handleError?.run {
            viewModelScope.launch(Dispatchers.Main) {
                invoke(statusCode, message ?: "")
            }
        }
    }

    fun cancelJob() {
        viewModelScope.coroutineContext.cancelChildren()
    }

    override fun onCleared() {
        cancelJob()
        Logger.warning(simpleName(), "$this is Cleared")
        super.onCleared()
    }
}