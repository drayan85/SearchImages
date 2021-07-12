package com.image.search;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

/**
 * @author Paramanathan Ilandrayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
public class MessageAlertDialog extends DialogFragment {

    /**
     * MessageAlertDialog Dialogue Fragment show() TAG.
     */
    public static final String TAG = MessageAlertDialog.class.getSimpleName();

    private MessageAlertDialogCallback mMessageAlertDialogCallback;
    private String mMessage;

    public static MessageAlertDialog getInstance(final String message, final MessageAlertDialogCallback dialogCallback) {
        MessageAlertDialog messageAlertDialog = new MessageAlertDialog();
        messageAlertDialog.mMessage = message;
        messageAlertDialog.mMessageAlertDialogCallback = dialogCallback;
        return messageAlertDialog;
    }

    /**
     * MessageAlertDialog dialogue Fragment onClick callback interface.
     */
    public interface MessageAlertDialogCallback {

        void userClickedConfirmed();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(requireActivity());
        dlgAlert.setMessage(mMessage);
        dlgAlert.setTitle(getResources().getString(R.string.app_name));
        dlgAlert.setCancelable(false);
        dlgAlert.setPositiveButton(getResources().getString(android.R.string.ok),
                (dialog, which) -> mMessageAlertDialogCallback.userClickedConfirmed());
        return dlgAlert.create();
    }
}
