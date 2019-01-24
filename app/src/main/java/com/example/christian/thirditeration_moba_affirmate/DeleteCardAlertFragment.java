package com.example.christian.thirditeration_moba_affirmate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DeleteCardAlertFragment extends DialogFragment{

    public static TinyDB tinydb;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

            tinydb = MainActivity.getTinydb();


            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.delete_card_dialog)
                    .setPositiveButton(R.string.yes_delete_card, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {


                        }
                    })
                    .setNegativeButton(R.string.no_keep_card, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();

    }

}
