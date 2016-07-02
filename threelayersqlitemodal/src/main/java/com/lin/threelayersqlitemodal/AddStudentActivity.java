package com.lin.threelayersqlitemodal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lin.threelayersqlitemodal.models.Student;
import com.lin.threelayersqlitemodal.service.StudentService;


public class AddStudentActivity extends AppCompatActivity {

    private EditText et_name, et_address, et_money, et_age;
    private StudentService studentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        studentService = new StudentService(this);
        et_address = (EditText) this.findViewById(R.id.et_address);
        et_age = (EditText) this.findViewById(R.id.et_age);
        et_money = (EditText) this.findViewById(R.id.et_money);
        et_name = (EditText) this.findViewById(R.id.et_name);
    }

    public void btn_add(View v) {
        Student student = new Student();
        student.setAddress(et_address.getText().toString().trim());
        student.setMoney(Double.parseDouble(et_money.getText().toString().trim()));
        student.setName(et_name.getText().toString().trim());
        student.setAge(Integer.parseInt(et_age.getText().toString().trim()));
        if (studentService.insert(student)) {
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT);
        } else {
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT);
        }
        finish();
    }

    public void btn_cancel(View view) {
        finish();
    }
}
