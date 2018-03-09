package tuonbondol.soapapi.android

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import java.io.Serializable

/****
 *
 * Created by TUON BONDOL on 11/18/17.
 *
 */

abstract class BaseActivity<B : ViewDataBinding, out VM : BaseViewModel> : AppCompatActivity() {

    protected abstract val mLayoutId: Int @LayoutRes get
    protected abstract val mVariable: Int @IdRes get
    protected abstract val mViewModel: VM
    private var mBinding: B? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, mLayoutId)
        mBinding?.setVariable(mVariable, mViewModel)
    }

    fun onReplaceFragment(containerViewId: Int, fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                //.setCustomAnimations(R.anim.activity_right_in, R.anim.activity_right_out)
                .replace(containerViewId, fragment)
                .commit()
    }

    open fun onKeyOk() {

    }

    open fun onBack() {
        onBackPressed()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
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

    fun getBundleData() : Serializable{
        return intent.getSerializableExtra("data")
    }
}