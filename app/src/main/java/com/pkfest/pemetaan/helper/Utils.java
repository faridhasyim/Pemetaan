package com.pkfest.pemetaan.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by rifky on 04/06/17.
 */

public class Utils {
    public static Toast toastLong(Context context, String s){
        Toast toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
        toast.show();
        return toast;
    }

    public static Toast toastShort(Context context,String s){
        Toast toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }

    public static ProgressDialog getWaitDialog(Context context, String message) {
        ProgressDialog waitDialog = new ProgressDialog(context);
        if (!TextUtils.isEmpty(message)) {
            waitDialog.setMessage(message);
        }
        return waitDialog;
    }
}
