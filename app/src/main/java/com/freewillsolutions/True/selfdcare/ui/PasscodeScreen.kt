package com.freewillsolutions.True.selfdcare.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrognito.pinlockview.PinLockListener
import com.andrognito.pinlockview.PinLockView
import com.freewillsolutions.True.selfdcare.R
import com.freewillsolutions.True.selfdcare.ui.base.BaseScreen
import com.freewillsolutions.True.selfdcare.utility.extensions.fadeIn
import com.jozzee.android.core.fragment.replaceFragment
import com.jozzee.android.core.resource.getDrawable
import com.jozzee.android.core.view.showToast
import kotlinx.android.synthetic.main.screen_passcode.*


class PasscodeScreen : BaseScreen() {

    private val TAG = PasscodeScreen::class.java.simpleName
    lateinit var rootView: View
    lateinit var mPinLockView: PinLockView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.screen_passcode, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
    }

    private fun subscribeUi() {
        setupPinLockView()
    }

    private fun setupPinLockView() {
        val mPinLockListener: PinLockListener = object : PinLockListener {
            override fun onComplete(pin: String) {
                Log.d(TAG, "Pin complete: $pin")
                setNumberToIndicator(pin)
            }

            override fun onEmpty() {
                Log.d(TAG, "Pin empty")
                setNumberToIndicator("")

            }

            override fun onPinChange(pinLength: Int, intermediatePin: String) {
                Log.d(
                    TAG,
                    "Pin changed, new length $pinLength with intermediate pin $intermediatePin"
                )
                setNumberToIndicator(intermediatePin)
            }
        }
        mPinLockView = pinLockView as PinLockView
        mPinLockView.let {
            it.setPinLockListener(mPinLockListener)
            it.buttonBackgroundDrawable = getDrawable(R.drawable.passcode_btn_bg)
            it.setBackgroundColor(resources.getColor(R.color.white))
            it.textColor = resources.getColor(R.color.text_primary)
            it.deleteButtonDrawable = getDrawable(R.drawable.ic_delete)
            it.deleteButtonSize = 52
            it.textSize = 52
        }
    }

    private fun setNumberToIndicator(pin: String) {
        when (pin.length) {
            0 -> {
                numberIndicator1.text = ""
                numberIndicator1.background = getDrawable(R.drawable.circle_gray)
            }
            1 -> {
                numberIndicator1.text = pin[1 - 1].toString()
                numberIndicator1.background = getDrawable(R.drawable.circle_blue)
                if (pin.length == 1) {
                    numberIndicator2.text = ""
                    numberIndicator2.background = getDrawable(R.drawable.circle_gray)
                }
            }
            2 -> {
                numberIndicator2.text = pin[2 - 1].toString()
                numberIndicator2.background = getDrawable(R.drawable.circle_blue)
                if (pin.length == 2) {
                    numberIndicator3.text = ""
                    numberIndicator3.background = getDrawable(R.drawable.circle_gray)
                }
            }
            3 -> {
                numberIndicator3.text = pin[3 - 1].toString()
                numberIndicator3.background = getDrawable(R.drawable.circle_blue)
                if (pin.length == 3) {
                    numberIndicator4.text = ""
                    numberIndicator4.background = getDrawable(R.drawable.circle_gray)
                }
            }
            4 -> {
                numberIndicator4.text = pin[4 - 1].toString()
                numberIndicator4.background = getDrawable(R.drawable.circle_blue)
                checkOtp(pin)
            }
        }
    }

    private fun checkOtp(pin:String){
        if (pin == "1234")
        {
            replaceFragment(MainScreen(), fadeIn(),clearStack = true,addToBackStack = false)
        }else{
            showToast("หมายเลข OTP ไม่ถูกต้อง")
        }
    }

}