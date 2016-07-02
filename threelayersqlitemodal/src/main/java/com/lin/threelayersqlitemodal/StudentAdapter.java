package com.lin.threelayersqlitemodal;

import android.widget.BaseAdapter;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lin.threelayersqlitemodal.models.Student;
import java.util.List;

/**
 * Created by my on 2016/6/30.
 */
public class StudentAdapter extends BaseAdapter {

    private Context context;
    private List<Student> data;

    public StudentAdapter(Context context, List<Student> data) {
        super();
        Log.i("aaa","StudentAdapter构造方法执行了");
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        Log.i("aaa","getCount执行了");
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        Log.i("aaa","getItem执行了");
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.i("aaa","getItemId执行了");
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("aaa","getView执行了");
        ViewHolder holder = null;
        if (convertView == null) {//convertView    转变View，  ListView的每一个条目，用转换View对象来表示。
            convertView = View.inflate(context, R.layout.item, null);
            holder = new ViewHolder();
            holder.tv_stuNo = (TextView) convertView.findViewById(R.id.tv_id);
            holder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            holder.tv_age = (TextView) convertView.findViewById(R.id.tv_age);
            holder.tv_money = (TextView) convertView.findViewById(R.id.tv_money);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //前面的5行，是先把还不是对象的TextView通过findViewById得到TextView的对象。再操作对象，给对象赋值，修改对象属性什么的。
        Student student = data.get(position);
        holder.tv_name.setText(student.getName());
        holder.tv_address.setText(student.getAddress());
        holder.tv_age.setText(student.getAge() + "");
        holder.tv_money.setText(student.getMoney() + "");
        holder.tv_stuNo.setText("" + student.getStuNo());


        return convertView;
    }

    class ViewHolder {
        TextView tv_stuNo;
        TextView tv_name;
        TextView tv_address;
        TextView tv_age;
        TextView tv_money;
    }
}
