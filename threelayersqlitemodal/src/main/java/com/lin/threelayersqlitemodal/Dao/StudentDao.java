package com.lin.threelayersqlitemodal.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.lin.threelayersqlitemodal.commom.StudentSqliteOpenHelper;
import com.lin.threelayersqlitemodal.models.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 学生数据库操作类
 * Created by my on 2016/6/27.
 */
public class StudentDao {

    private StudentSqliteOpenHelper helper;

    public StudentDao(Context context) {
        helper = new StudentSqliteOpenHelper(context);
    }

    public boolean insert(Student student) {
        SQLiteDatabase db = null;
        try {
            db = helper.getReadableDatabase();
            db.execSQL("insert into student (stuName,address,money,age) values(?,?,?,?)", new Object[]{student.getName(), student.getAddress(), student.getMoney(), student.getAge()});
            return true;
        } catch (Exception e) {
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return false;
    }

    public boolean delete(int stuNo) {
        SQLiteDatabase db = null;
        try {
            db = helper.getReadableDatabase();
            db.execSQL("delete from student where stuNo =?", new Object[]{String.valueOf(stuNo)});
        } catch (Exception e) {
        } finally {
            if (db.isOpen() && db != null) {
                db.close();
            }
        }
        return false;
    }

    public boolean update(Student student) {
        SQLiteDatabase db = null;
        try {
            db = helper.getReadableDatabase();
            db.execSQL("update student set stuName =? ,address = ?, money = ? ,age=? where stuNo =?", new Object[]{student.getName(), student.getAddress(), student.getMoney(), student.getAge(), student.getStuNo()});
        } catch (Exception e) {
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return false;
    }

    public List<Student> getAllStudentList() {
        List<Student> data = null;
        SQLiteDatabase db = null;
        try {
            data = new ArrayList<Student>();
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from student", null);
            while (cursor.moveToNext()) {
                int stuNo = cursor.getInt(cursor.getColumnIndex("stuNo"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                double money = cursor.getDouble(cursor.getColumnIndex("money"));
                Student student = new Student();
                student.setAddress(address);
                student.setAge(age);
                student.setName(name);
                student.setMoney(money);
                student.setStuNo(stuNo);
                data.add(student);
            }
        } catch (Exception e) {
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return data;
    }

    public Cursor getAllStudentCursor() {
        Cursor cursor = null;
        SQLiteDatabase db = null;
        try {
            cursor = db.rawQuery("select * from student", null);
            return cursor;
        } catch (Exception e) {
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return cursor;
    }


    public List<HashMap<String, Object>> getAllStudentmap() {
        Cursor cursor = getAllStudentCursor();
        return cursorTolist(cursor);
    }

    /**
     * 把Cursor 转换成 List集合
     */

    public List<HashMap<String, Object>> cursorTolist(Cursor cursor) {
        List<HashMap<String, Object>> data = null;
        try {
            data = new ArrayList<HashMap<String, Object>>();
            while (cursor.moveToNext()) {//这个while循环虽然学号只有一个，但也可能没有，出错有多个。也可以用if代替。
                HashMap<String, Object> map = new HashMap<String, Object>();
                String[] columns = cursor.getColumnNames();
                for (int i = 0; i < columns.length; i++) {
//       String name = cursor.getString(cursor.getColumnIndex("name"));
                    int type = cursor.getType(i);
                    Object value = null;
                    switch (type) {
                        case Cursor.FIELD_TYPE_STRING:
                            String s = cursor.getString(i);
                            break;
                        case Cursor.FIELD_TYPE_NULL:
                            break;
                        case Cursor.FIELD_TYPE_FLOAT:
                            value = cursor.getFloat(i);
                            break;
                        case Cursor.FIELD_TYPE_BLOB:
                            value = cursor.getBlob(i);
                            break;
                        case Cursor.FIELD_TYPE_INTEGER:
                            value = cursor.getInt(i);
                            break;
                    }
                    map.put(columns[i], value);
                }
                data.add(map);
            }
        } catch (Exception e) {
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return data;
    }

    public Student getstudentBystuNo(int stuNo) {
        Student student = null;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = helper.getReadableDatabase();
            cursor = db.rawQuery("select * from student where stuNo =?", new String[]{stuNo + ""});
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                double money = cursor.getDouble(cursor.getColumnIndex("money"));
                student.setAddress(address);
                student.setAge(age);
                student.setName(name);
                student.setMoney(money);
                student.setStuNo(stuNo);
            }
        } catch (Exception e) {
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        return student;
    }

    public List<Student> getAllStudentListByPage(int pageSize, int pageindex) {
        Log.i("aaa","DAO_getAllStudentListByPage执行了");
        List<Student> data = null;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = helper.getReadableDatabase();
            int offset = pageSize * (pageindex - 1);
            data = new ArrayList<Student>();
            cursor = db.rawQuery("select * from student order by stuNo limit ?,?", new String[]{String.valueOf(offset), String.valueOf(pageSize)});
            while (cursor.moveToNext()) {
                Log.i("aaa","while执行了");
                Student student = new Student();
                int stuNo = cursor.getInt(cursor.getColumnIndex("stuNo"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                double money = cursor.getDouble(cursor.getColumnIndex("money"));
                student.setAddress(address);
                student.setStuNo(stuNo);
                student.setAge(age);
                student.setName(name);
                student.setMoney(money);
                data.add(student);
            }
            return data;
        } catch (Exception e) {
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
        Log.i("aaa","DAO_getAllStudentListByPage执行完全了");
        return data;
    }
}

