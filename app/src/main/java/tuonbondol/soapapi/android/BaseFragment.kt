package tuonbondol.soapapi.android

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/****
 *
 * Created by TUON BONDOL on 11/23/17.
 *
 */

abstract class BaseFragment<B : ViewDataBinding, out VM> : Fragment() {

    protected abstract val mLayoutId: Int @LayoutRes get

    protected abstract val mVariable: Int @IdRes get

    protected abstract val mViewModel: VM

    protected open val variables @SuppressLint("SupportAnnotationUsage") @IdRes get() = hashMapOf<Int, Any>()

    private var mBinding: B? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, mLayoutId, container, false)
        mBinding?.let {
            it.setVariable(mVariable, mViewModel)
            variables.keys.forEach { key ->
                it.setVariable(key, variables[key])
            }
            it.executePendingBindings()
        }
        return mBinding?.root
    }


    fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        when {
            event.action == KeyEvent.ACTION_DOWN -> if (keyCode == KeyEvent.KEYCODE_ESCAPE || keyCode == KeyEvent.KEYCODE_BACK) {
                onBack()
                return true
            } else if (keyCode == KeyEvent.KEYCODE_ENTER) {
                onKeyOk()
                return true
            }
        }
        return false
    }

    open fun onKeyOk() {}

    open fun onBack() {}
}