package tuonbondol.soapapi.android.sale

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import tuonbondol.soapapi.android.BaseFragment
import kotlinx.android.synthetic.main.fragment_sale.*
import tuonbondol.soapapi.android.BR
import tuonbondol.soapapi.android.R
import tuonbondol.soapapi.android.databinding.FragmentSaleBinding
import java.text.DecimalFormat

/****
 *
 * Created by TUON BONDOL on 12/5/17.
 *
 */

class SaleFragment : BaseFragment<FragmentSaleBinding, SaleViewModel>() {

    override val mLayoutId = R.layout.fragment_sale
    override val mVariable = BR.mViewModel
    override val mViewModel by lazy { SaleViewModel(activity!!) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etAmount.addTextChangedListener(object : TextWatcher {
            @SuppressLint("SetTextI18n")
            override fun afterTextChanged(arg0: Editable) {
                var s = arg0.toString().replace("\u17DB ", "")
                s = s.replace("$", "")
                s = s.replace(" ".toRegex(), "")
                s = s.replace(",", "")
                etAmount.removeTextChangedListener(this)

                val userInput = "" + s.replace("[^\\d]".toRegex(), "")
                if (userInput.isNotEmpty()) {
                    val mDouble = userInput.toDouble()
                    val percent = mDouble / 100

                    val formattedString = if (percent >= 1) {
                        val formatter = DecimalFormat("#,###.00")
                        formatter.format(percent)
                    } else {
                        String.format("%.2f", percent)
                    }
                    etAmount.setText(formattedString)
                    etAmount.setSelection(etAmount.text.length)
                    etAmount.addTextChangedListener(this)
                }

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })
        etAmount.setText(resources.getString(R.string.zero_dot_zero))
        etAmount.setSelection(etAmount.text.length)
        etAmount.requestFocus()

        etAmount.setOnKeyListener({ _, keyCode, event -> onKeyDown(keyCode, event) })
    }

    override fun onKeyOk() {
        mViewModel.onRequestAlipayQrCode()
    }
}