package com.example.android.courtcounter;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class CustomDialog extends Activity{
    public void showDialog(Activity activity, String msg){
        // Create custom dialog object
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        // Include dialog.xml file
        dialog.setContentView(R.layout.dialog);

        // set values for custom dialog components - text, image and button
        TextView winnerText = (TextView) dialog.findViewById(R.id.winner);
        winnerText.setText(msg);
        winnerText.setAllCaps(true);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
