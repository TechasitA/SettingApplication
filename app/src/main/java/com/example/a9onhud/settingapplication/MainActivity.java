package com.example.a9onhud.settingapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView userID;
    private CheckBox preferenceStatusCb;
    private Switch firstSwitch, secondSwitch;
    private int preferenceCheckedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userID = findViewById(R.id.userIDTv);
        preferenceStatusCb = findViewById(R.id.preferenceCb);
        firstSwitch = findViewById(R.id.firstPreferenceSw);
        secondSwitch = findViewById(R.id.secondPreferenceSw);

        // set 2 switches default.
        firstSwitch.setEnabled(false);
        secondSwitch.setEnabled(false);
    }

    /**
     * This method make dialog to change userID when clicking in UI.
     * @param view
     */
    public void changeUserID(View view) {
        // get layout for make dialog.
        final View dialog = getLayoutInflater().inflate(R.layout.change_user_id_dialog, null);

        // just make default value to editText.
        final EditText newUserID = dialog.findViewById(R.id.editText);
        newUserID.setText(userID.getText());


        // build dialog with layout by using setView().
        // setPositiveButton() is method for set button on dialog.
        new AlertDialog.Builder(MainActivity.this).setView(dialog)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                userID.setText(newUserID.getText().toString());
            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        }).create().show();
    }

    /**
     * This method make dialog with radiobutton when clicking in UI.
     * @param view
     */
    public void choosePreference(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // make CharSequence for making radiobutton.
        final CharSequence[] temperatures = {"Hot", "Warm", "Cold"};

        // setSingleChoiceItems() is method making radiobutton. ** second parameter is default checked button index.
        // setCancelable() is method setting dialog cancelable by click out of dialog.
        builder.setSingleChoiceItems(temperatures, preferenceCheckedIndex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Toast.makeText(MainActivity.this, "Temperature is 40.", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "Temperature is 20.", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "Temperature is 0.", Toast.LENGTH_SHORT).show();
                        break;
                }

                preferenceCheckedIndex = which;

                // dismiss() is method setting dialog to close.
                dialog.dismiss();
            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setCancelable(false).setTitle("List preference");

        builder.create().show();
    }

    /**
     * this method change switch by click checkbox in UI.
     * @param view
     */
    public void changeSwitchStatus(View view) {
        if (preferenceStatusCb.isChecked()) {
            firstSwitch.setEnabled(true);
            secondSwitch.setEnabled(true);
        } else {
            firstSwitch.setEnabled(false);
            secondSwitch.setEnabled(false);
        }
    }
}
