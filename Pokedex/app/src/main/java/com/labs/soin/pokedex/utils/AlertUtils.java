package com.labs.soin.pokedex.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by chris on 11/14/16.
 * This handles toasters and general alerts
 */

public class AlertUtils {

    /**
     * Show a toaster witht message
     * @param con
     * @param message
     */
    public static void showToaster(Context con, String message) {
        Toast toast = Toast.makeText(con, message, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showAlertError(Context context, String Error){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Error");
        alertDialog.setMessage(Error);
        alertDialog.show();
    }
}
