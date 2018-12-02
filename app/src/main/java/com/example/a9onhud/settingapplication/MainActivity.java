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

        // set 2 switches default
        firstSwitch.setEnabled(false);
        secondSwitch.setEnabled(false);
    }

    public void changeUserID(View view) {
        final View dialog = getLayoutInflater().inflate(R.layout.change_user_id_dialog, null);

        final EditText newUserID = dialog.findViewById(R.id.editText);
        newUserID.setText(userID.getText());

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

    public void choosePreference(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        final CharSequence[] temperatures = {"Hot", "Warm", "Cold"};

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
