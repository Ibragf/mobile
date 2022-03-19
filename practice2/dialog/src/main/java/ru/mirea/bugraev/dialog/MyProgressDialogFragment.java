package ru.mirea.bugraev.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyProgressDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getActivity(), getTheme());
        dialog.setTitle("ждать");
        dialog.setMessage("что-то");
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return dialog;
    }


}
