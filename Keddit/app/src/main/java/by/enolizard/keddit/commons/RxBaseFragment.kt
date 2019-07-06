package by.enolizard.keddit.commons

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

open class RxBaseFragment() : Fragment() {
    protected var subscriptions = CompositeDisposable()

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeDisposable()
    }

    override fun onPause() {
        super.onPause()
//        if (!subscriptions.isDisposed) {
//            subscriptions.dispose()
//        }
        subscriptions.clear()
    }
}