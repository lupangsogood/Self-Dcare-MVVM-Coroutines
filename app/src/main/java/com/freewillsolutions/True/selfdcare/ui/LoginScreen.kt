package com.freewillsolutions.True.selfdcare.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freewillsolutions.True.selfdcare.R
import com.freewillsolutions.True.selfdcare.ui.base.BaseScreen
import com.freewillsolutions.True.selfdcare.utility.extensions.setStatusBarColor
import com.freewillsolutions.True.selfdcare.utility.extensions.showSnackBar
import com.freewillsolutions.True.selfdcare.utility.extensions.slideRightToLeft
import com.jozzee.android.core.fragment.replaceFragment
import com.jozzee.android.core.text.isPhoneNumber
import com.jozzee.android.core.view.content
import com.jozzee.android.core.view.showToast
import kotlinx.android.synthetic.main.screen_login.*

class LoginScreen : BaseScreen() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarColor(Color.TRANSPARENT, true)
        subscribeUi()
    }

    private fun subscribeUi() {
        next_button.setOnClickListener {
            if (isDataValid()) {
                replaceFragment(PasscodeScreen(), slideRightToLeft())
            }
        }
    }

    private fun isDataValid(): Boolean {
        if (telephone_number_input.content().isPhoneNumber()) {
            return true
        }
        showSnackBar(R.string.please_enter_correct_phone_number)
        return false
    }
}