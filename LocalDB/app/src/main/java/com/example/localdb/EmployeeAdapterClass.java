package com.example.localdb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class EmployeeAdapterClass extends RecyclerView.Adapter<EmployeeAdapterClass.ViewHolder> {

    List<EmployeeModelClass> employee;
    Context context;
    DatabaseHelperClass databaseHelperClass;

    public EmployeeAdapterClass(List<EmployeeModelClass> employee, Context context) {
        this.employee = employee;
        this.context = context;
        databaseHelperClass = new DatabaseHelperClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.employee_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final EmployeeModelClass employeeModelClass = employee.get(position);
        Integer ids = employeeModelClass.getId();
        holder.textViewID.setText(Integer.toString(ids));
        holder.editText_Name.setText(employeeModelClass.getName());
        holder.editText_Email.setText(employeeModelClass.getEmail());
        holder.editText_Pesan.setText(employeeModelClass.getPesan());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                               // variabel ke detail activity

                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("id_edit", ids);

                context.startActivity(intent);
            }
        });

        holder.button_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = holder.editText_Name.getText().toString();
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("id_edit", ids);

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return employee.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        TextView editText_Name;
        TextView editText_Email;
        TextView editText_Pesan;
        Button button_View;
        LinearLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_Name = itemView.findViewById(R.id.viewtext_name);
            editText_Email = itemView.findViewById(R.id.viewtext_email);
            editText_Pesan = itemView.findViewById(R.id.viewtext_pesan);
            button_View = itemView.findViewById(R.id.button_view);
//            button_Delete = itemView.findViewById(R.id.button_delete);
          constraintLayout = itemView.findViewById(R.id.constraintSayout);

        }
    }
}
