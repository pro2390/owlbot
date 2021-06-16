package com.dev.owlbot.common
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.inputmethod.InputMethodManager
import com.dev.owlbot.BuildConfig

class Utils {
    companion object{
        fun getHeader(): HashMap<String, String>? {
            val header = HashMap<String, String>()
            header["Authorization"] =
                "Token " + BuildConfig.API_TOKEN
                        return header
        }

        fun isNetworkAvailable(context: Context?): Any {
            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
            return isConnected
        }

        fun hideSoftInput(activity: Activity?) {
            if (activity != null && activity.currentFocus != null) {
                val inputMethodManager = activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager?.hideSoftInputFromWindow(
                    activity.currentFocus!!.windowToken,
                    InputMethodManager.RESULT_UNCHANGED_SHOWN
                )
            }
        }
    }
}