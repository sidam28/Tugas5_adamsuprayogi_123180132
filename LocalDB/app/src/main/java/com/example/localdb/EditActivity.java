package com.example.localdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EditActivity extends AppCompatActivity {
    DatabaseHelperClass dbHelper;
    Integer id_data;
    TextView text_id;
    EditText editText_name, editText_email, editText_pesan;
    Button button_edit, button_delete, button_back;
    String nama, email, pesan;
    List<EmployeeModelClass> employee;
    DatabaseHelperClass helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        text_id = findViewById(R.id.text_id);
        editText_name = findViewById(R.id.edittext_name);
        editText_email = findViewById(R.id.edittext_email);
        editText_pesan = findViewById(R.id.edittext_pesan);
        button_edit = findViewById(R.id.button_edit_detail);
        button_delete = findViewById(R.id.button_delete_detail);
        button_back = findViewById(R.id.button_back_detail);
        getIncomingExtra();

        helper = new DatabaseHelperClass(this);

        getdata();
        setDataActivity(id_data);

        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = editText_name.getText().toString();
                String stringEmail = editText_email.getText().toString();
                String stringPesan = editText_pesan.getText().toString();
                if (stringName.length() <= 0 || stringEmail.length() <= 0) {
                    Toast.makeText(EditActivity.this, "Enter All Data", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(EditActivity.this);
                    databaseHelperClass.updateEmployee(new EmployeeModelClass(id_data,stringName,stringEmail,stringPesan));
                    Toast.makeText(EditActivity.this, "Edit Employee Successfully", Toast.LENGTH_SHORT).show();
//                    finish();
//                    startActivity(getIntent());
                }
                Intent intent = new Intent(EditActivity.this, ViewEmployeeActivity.class);
                startActivity(intent);

            }
        });


        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                builder.setMessage("This note will be deleted.");
                builder.setCancelable(true);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(EditActivity.this);
                        databaseHelperClass.deleteEmployee(id_data);
                        Toast.makeText(EditActivity.this, "Delete Employee Successfully", Toast.LENGTH_SHORT).show();
//                        finish();
//                        startActivity(getIntent());

                        Intent intent = new Intent(EditActivity.this, ViewEmployeeActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, ViewEmployeeActivity.class);
                startActivity(intent);
            }
        });


    }

    private void getIncomingExtra() {
        if (getIntent().hasExtra("id_edit")) {
            Integer id_edit = getIntent().getIntExtra("id_edit", 0);
            id_data=id_edit;

        }

    }

    private void setDataActivity(Integer id){

        text_id.setText(Integer.toString(id));
        editText_email.setText(email);
        editText_name.setText(nama);
        editText_pesan.setText(pesan);


    }
//    private void getdata(){
//
//        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(EditActivity.this);
//        List<EmployeeModelClass> employeeModelClasses = databaseHelperClass.getEmployee(id_data);
//        employeeModelClasses.
//
//        if (employeeModelClasses.size() > 0){
//            final EmployeeModelClass employeeModelClass = employee.get(0);
//            holder.editText_Name.setText(employeeModelClass.getName());
//            holder.editText_Email.setText(employeeModelClass.getEmail());
//
//        }else {
//            nama = "tidak ada";
//            email = "noo";
//        }
//    }

    private void getdata() {
        Cursor cursor = helper.oneData(id_data);
        if(cursor.moveToFirst()){
            String title = cursor.getString(cursor.getColumnIndex(DatabaseHelperClass.NAME));
            String detail = cursor.getString(cursor.getColumnIndex(DatabaseHelperClass.EMAIL));
            String pesanc = cursor.getString(cursor.getColumnIndex(DatabaseHelperClass.PESAN));

            nama = title;
            email = detail;
            pesan =  pesanc;
        }
    }

    public void onBindViewHolder(@NonNull final EmployeeAdapterClass.ViewHolder holder, final int position) {
        final EmployeeModelClass employeeModelClass = employee.get(0);
        holder.editText_Name.setText(employeeModelClass.getName());
        holder.editText_Email.setText(employeeModelClass.getEmail());
        holder.editText_Pesan.setText(employeeModelClass.getPesan());
    }

}

