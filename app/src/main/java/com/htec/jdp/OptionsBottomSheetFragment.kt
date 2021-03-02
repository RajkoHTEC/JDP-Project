package com.htec.jdp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OptionsBottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_sheet_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews(view)
    }

    private fun setUpViews(view : View) {
        // We can have cross button on the top right corner for providing elemnet to dismiss the bottom sheet
        //iv_close.setOnClickListener { dismissAllowingStateLoss() }
        view.findViewById<AppCompatTextView>(R.id.txt_download).setOnClickListener {
            dismissAllowingStateLoss()
            Toast.makeText(context?.applicationContext, "Download option clicked", Toast.LENGTH_LONG)
                    .show()
        }

        view.findViewById<AppCompatTextView>(R.id.txt_share).setOnClickListener {
            dismissAllowingStateLoss()
            Toast.makeText(context?.applicationContext, "Share option clicked", Toast.LENGTH_LONG)
                    .show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): OptionsBottomSheetFragment {
            val fragment = OptionsBottomSheetFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}