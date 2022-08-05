package com.example.parkinglotmanagement;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class enterNumber extends AppCompatActivity {
    Dialog dialog;
    String vehicleNumber = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_number);
        dialog = new Dialog(this);
        TextInputEditText numberPlate = (TextInputEditText) findViewById(R.id.vehicle_plate);
        ImageView logoutBtn = (ImageView) findViewById(R.id.logoutBtn);
        Button addBtn = (Button) findViewById(R.id.btnAdd);
        numberPlate.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if ((numberPlate.getText().length() + 1 == 3 || numberPlate.getText().length() + 1 == 6 || numberPlate.getText().length() + 1 == 9)) {
                    if(before-count<0){
                        numberPlate.setText(numberPlate.getText() + "-");
                        numberPlate.setSelection(numberPlate.getText().length());
                    }
                }
                vehicleNumber = numberPlate.getText().toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVehicleDialog();
                Log.d("number_plate",vehicleNumber);
                Toast.makeText(enterNumber.this,vehicleNumber, Toast.LENGTH_SHORT).show();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignoutDialog();
            }
        });


    }

    private void openVehicleDialog() {
        dialog.setContentView(R.layout.activity_dialog_alert_vehicle);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button checkOut = (Button) dialog.findViewById(R.id.check_out_btn);
        Button cancel = (Button) dialog.findViewById(R.id.cancel_btn);
        cancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        checkOut.setOnClickListener(view -> {
                    //db_code
                    recreate();
                }
        );
        dialog.show();
    }

    private void openSignoutDialog() {
        dialog.setContentView(R.layout.activity_dialog_alert_signout);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button logOut = (Button) dialog.findViewById(R.id.logout_btn);
        Button cancel = (Button) dialog.findViewById(R.id.cancel_btn);
        cancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        logOut.setOnClickListener(view -> {
                    startActivity(new Intent(enterNumber.this, login.class));
                }
        );
        dialog.show();
    }
}