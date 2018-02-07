package com.pkfest.pemetaan.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.pkfest.pemetaan.R;

/**
 * Created by rifky on 04/06/17.
 */

public class DialogTentang extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        View view = LayoutInflater.from(DialogTentang.this.getActivity()).inflate(R.layout.tentang, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(DialogTentang.this.getActivity());
        builder.setView(view);
        builder.setNegativeButton(R.string.tomboldialog_tutup, mOnClickListener);

        return builder.create();
    }


    DialogInterface.OnClickListener mOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            DialogTentang.this.getDialog().dismiss();

        }
    };

}