package tuonbondol.soapapi.android.home

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import tuonbondol.soapapi.android.BaseActivity
import tuonbondol.soapapi.android.BR
import tuonbondol.soapapi.android.R
import tuonbondol.soapapi.android.databinding.ActivityHomeBinding
import tuonbondol.soapapi.android.sale.SaleFragment

/****
 *
 * Created by TUON BONDOL on 12/12/17.
 *
 */

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    override val mLayoutId = R.layout.activity_home
    override val mVariable = BR.mViewModel
    override val mViewModel by lazy { HomeViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onReplaceFragment(this, SaleFragment())
    }

    private fun onReplaceFragment(context: Context, fragment: Fragment) {
        (context as HomeActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.flMainContainer, fragment)
                .commit()
    }
}

