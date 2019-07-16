package ru.gdgkazan.simpleweather.screen.general

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.gdgkazan.simpleweather.R
import java.lang.ref.Reference
import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicBoolean

class LoadingDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, theme)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity!!)
            .setView(View.inflate(activity, R.layout.dialog_loading, null))
            .create()
    }

    private class HideTask(fm: FragmentManager) : Runnable {

        private val mFmRef: Reference<FragmentManager>

        private var mAttempts = 10

        init {
            mFmRef = WeakReference(fm)
        }

        override fun run() {
            HANDLER.removeCallbacks(this)
            val fm = mFmRef.get()
            if (fm != null) {
                val dialog = fm.findFragmentByTag(LoadingDialog::class.java!!.getName()) as LoadingDialog
                if (dialog != null) {
                    dialog.dismissAllowingStateLoss()
                } else if (--mAttempts >= 0) {
                    HANDLER.postDelayed(this, 300)
                }
            }
        }
    }

    companion object {

        private val HANDLER = Handler(Looper.getMainLooper())

        fun view(fm: FragmentManager): LoadingView {
            return object : LoadingView {

                private val mWaitForHide = AtomicBoolean()

                override fun showLoadingIndicator() {
                    if (mWaitForHide.compareAndSet(false, true)) {
                        if (fm.findFragmentByTag(LoadingDialog::class.java!!.getName()) == null) {
                            val dialog = LoadingDialog()
                            dialog.show(fm, LoadingDialog::class.java!!.getName())
                        }
                    }
                }

                override fun hideLoadingIndicator() {
                    if (mWaitForHide.compareAndSet(true, false)) {
                        HANDLER.post(HideTask(fm))
                    }
                }
            }
        }

        fun view(fragment: Fragment): LoadingView {
            return view(fragment.fragmentManager!!)
        }
    }

}
