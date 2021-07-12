package com.image.search;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.image.search.di.component.ApplicationComponent;
import com.image.search.utils.NetworkUtils;

/**
 * @author Paramanathan Ilandrayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    /**
     * This method will be called after the View ready to resolve the dagger dependency.
     *
     * @param savedInstanceState Bundle
     * @param intent             Intent
     */
    @CallSuper
    protected void onViewReady(final Bundle savedInstanceState, final Intent intent) {
        resolveDaggerDependency(); //To be used by child activities
    }

    /**
     * This method will have implementation of the Dagger injection with necessary modules
     * to inject the class.
     */
    protected void resolveDaggerDependency() {
    }

    /**
     * Will display the back arrow in the Action bar.
     */
    protected final void showBackArrow() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
    }

    /**
     * Will Display the Progress Dialogue.
     *
     * @param message Message
     */
    public void showDialog(final String message) {
        if (!isFinishing()) {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setCancelable(true);
            }
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }
    }

    /**
     * Will Hide the Displaying Progress Dialogue.
     */
    public void hideDialog() {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * Will return ApplicationComponent which has been received from Application class.
     *
     * @return ApplicationComponent
     */
    public ApplicationComponent getApplicationComponent() {
        return ((ImageSearchApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Will return the Boolean value whether Internet Connection Available or Not.
     *
     * @return Boolean
     */
    public boolean isInternetAvailable() {
        return NetworkUtils.isNetAvailable(this);
    }

    /**
     * Will Display the Dialogue Box with the message passed as argument.
     *
     * @param message Message
     */
    public void displayErrorAlertBox(final String message) {
        hideDialog();
        MessageAlertDialog.getInstance(message, () -> {
            //TODO
        }).show(getSupportFragmentManager(), MessageAlertDialog.TAG);
    }

    /**
     * Will Display the Toast Message.
     *
     * @param message Message
     */
    public void displayToastMessage(final String message) {
        hideDialog();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    /**
     * Will Return Application class.
     *
     * @return ImageSearchApplication
     */
    public ImageSearchApplication getImageSearchApplication() {
        return ((ImageSearchApplication) getApplication());
    }
}
