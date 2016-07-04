package com.lin.threelayersqlitemodal;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lin.threelayersqlitemodal.models.Student;
import com.lin.threelayersqlitemodal.service.StudentService;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private StudentService studentService;
    private List<Student> data;
    private StudentAdapter adapter;
    //    private List<HashMap<String,Object>> data;
    private int pageSize = 15;
    private int pageIndex = 1;
    private final int edititemid = 0x111, deleteitemid = 0x112;
    private int selectPostion = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.i("aaa", "onCreate执行了");
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        studentService = new StudentService(this);
        data = studentService.getAllStudentListBypage(pageSize, pageIndex);
        adapter = new StudentAdapter(this, data);
        lv.setAdapter(adapter);

        //试试别的方法    1
//        data = studentService.getAllStudentmaplist();
//        SimpleAdapter adapter = new SimpleAdapter(this,data,R.layout.item,new String[]{"stuNo","stuName","address","money","age"},new int[]{R.id.tv_item_stuNo,R.id.tv_item_name,R.id.tv_item_address,R.id.tv_item_money,R.id.tv_item_age});
//        lv.setAdapter(adapter);
        //试试别的方法    2
//        Cursor cursor = studentService.getAllStudentCursor();
//        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,R.layout.item,cursor,new String[]{"_id","stuName","address","money","age"},new int[]{R.id.tv_item_stuNo,R.id.tv_item_name,R.id.tv_item_money,R.id.tv_item_age});
//        lv.setAdapter(cursorAdapter);
        Log.i("aaa", "setAdapter执行完了");
        //还有两种方式，两种适配器填充。最后再添加！！！
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            private boolean isBottom = false;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.i("aaa", "onScrollStateChanged执行了");
                if (isBottom && scrollState == 0) {
                    pageIndex++;
                    updateListView();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    isBottom = true;
                } else {
                    isBottom = false;
                }
            }
        });
        registerForContextMenu(lv);
    }

    public void updateListView() {
        data.clear();
        List<Student> list = studentService.getAllStudentListBypage(pageSize, pageIndex);
        if (list.size() == 0) {
            pageIndex = 1;
            updateListView();
        }
        data.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        updateListView();
    }

    public void btn_tranfermoney(View view) {
        if (studentService.studentTranferMoney(2, 4, 100)) {
            Toast.makeText(getApplicationContext(), "转账成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "转账失败", Toast.LENGTH_SHORT).show();
        }
        updateListView();
    }

    public void btn_addStudent(View view) {
        Intent intent = new Intent(this, AddStudentActivity.class);
        startActivity(intent);
    }

    /**
     * 添加上下文菜单，得到点击的哪个位置。
     * 【这里要注意，添加的是上下文菜单，并没有对点击添加监听，也不是长按监听！】
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //已经注册了上下文菜单，就有了上下文菜单的对象，就是传来的menu对象。用这个对象来操作想要的效果。
        menu.add(Menu.NONE, edititemid, Menu.NONE, "编辑");
        menu.add(Menu.NONE, deleteitemid, Menu.NONE, "删除");//这里的ID必须给，为了区分给后面添加监听做准备。就自己自定义了俩定值。
        Log.i("aaa", "onCreateContextMenu执行了");
        //菜单有了，得到点击的哪个位置↓在上下文菜单信息里可以得到。
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        selectPostion = info.position;
    }

    /**
     * 对上下文菜单添加监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //点击哪个都出来对话框。
            case edititemid:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //创建对话框构建起对象，它就是整体，分3层，标题中间的，下面的按钮。
                builder.setTitle("更新学生");
                View view = View.inflate(this, R.layout.edit, null);
                final TextView tv_stuNo = (TextView) view.findViewById(R.id.tv_ID);
                final EditText et_address = (EditText) view.findViewById(R.id.et_address);
                final EditText et_name = (EditText) view.findViewById(R.id.et_name);
                final EditText et_age = (EditText) view.findViewById(R.id.et_age);
                final EditText et_money = (EditText) view.findViewById(R.id.et_money);
                final Student student = data.get(selectPostion);//学生的List集合可以通过位置来得到对象，就是List的ID,List是有序的。
                et_address.setText(student.getAddress());
                et_name.setText(student.getName());
                et_money.setText(student.getMoney() + "");
                et_age.setText(student.getAge() + "");
                tv_stuNo.setText(student.getStuNo() + "");
                builder.setView(view);//中间的View对象，把其中的需要设定的对象都拿出来，设定好值，就可以设置中间的View了。
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //上面只是把数据显示到对应editText,这里才是再得到修改后的数据，设置到数据库里面。
                        int age = Integer.parseInt(et_age.getText().toString().trim());
                        String name = et_name.getText().toString().trim();
                        String address = et_address.getText().toString().trim();
                        double money = Double.parseDouble(et_money.getText().toString().trim());
                        if (TextUtils.isEmpty(name)) {
                            Toast.makeText(MainActivity.this, "姓名不能为空", Toast.LENGTH_SHORT);
                            return;
                        }
                        student.setName(name);
                        student.setAge(age);
                        student.setMoney(money);
                        student.setAddress(address);
                        if (studentService.update(student)) {
                            Toast.makeText(MainActivity.this, "跟新成功", Toast.LENGTH_SHORT);
                        } else {
                            Toast.makeText(MainActivity.this, "更新失败", Toast.LENGTH_SHORT);
                        }
                        updateListView();//更新ListView，只要学生更新成功了，ListView才需要跟新，所以放在这里。
                    }
                });
                builder.create().show();
                break;
            case deleteitemid:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("提示");
                builder1.setMessage("确定要删除吗？");
                builder1.setNegativeButton("取消", null);
                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Student student1 = data.get(selectPostion);
                        int stuNo = student1.getStuNo();
                        if (studentService.delete(stuNo)) {
                            Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT);
                        } else {
                            Toast.makeText(MainActivity.this, "删除失败", Toast.LENGTH_SHORT);
                        }
                        updateListView();
                    }
                });
                builder1.create().show();
                break;
        }
        return super.onContextItemSelected(item);
    }


}
