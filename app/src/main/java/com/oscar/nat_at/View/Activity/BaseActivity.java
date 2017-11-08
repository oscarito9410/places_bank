package com.oscar.nat_at.View.Activity;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.oscar.nat_at.MVPCore.View.BaseViewServer;
import com.oscar.nat_at.R;

/**
 * Created by oemy9 on 07/11/2017.
 */

public class BaseActivity extends AppCompatActivity{


    protected void showErrorMensaje(@StringRes int mensaje){
        showErrorMensaje(getString(mensaje));
    }
    protected void showErrorMensaje(String mensaje){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(R.string.ocurrio_error)
                .setMessage(mensaje)
                .setPositiveButton(android.R.string.ok,null)
        .create().show();

    }

}
