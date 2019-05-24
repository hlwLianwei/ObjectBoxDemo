package com.lenway.objbox;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lenway.objbox.entity.Person;

import java.util.List;

public class MainActivity extends Activity
{
    private final String mUserId1 = "12341";
    private final String mUserId2 = "12342";
    private String mCurrUserId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RadioButton rdb1 = findViewById(R.id.rdb_db1);
        RadioButton rdb2 = findViewById(R.id.rdb_db2);
        Button btn1 = findViewById(R.id.btn_clean_db);
        Button btn2 = findViewById(R.id.btn_add);
        Button btn3 = findViewById(R.id.btn_query);
        Button btn4 = findViewById(R.id.btn_clean_data);

        rdb1.setOnCheckedChangeListener(mChangeListener);
        rdb2.setOnCheckedChangeListener(mChangeListener);
        btn1.setOnClickListener(mListener);
        btn2.setOnClickListener(mListener);
        btn3.setOnClickListener(mListener);
        btn4.setOnClickListener(mListener);

        try {
            String[] PERMISSIONS_STORAGE = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE" };
            //检测是否有写的权限
            int permission = this.checkSelfPermission(
                "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                this.requestPermissions(PERMISSIONS_STORAGE, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CompoundButton.OnCheckedChangeListener mChangeListener = new CompoundButton.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(CompoundButton v, boolean isChecked)
        {
            if(!isChecked)
                return;

            switch (v.getId())
            {
            case R.id.rdb_db1:
                swicthId(true);
                break;
            case R.id.rdb_db2:
                swicthId(false);
                break;
            }
        }
    };

    private View.OnClickListener mListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
            case R.id.btn_clean_db:
                cleanDb();
                break;
            case R.id.btn_add:
                insertPerson();
                break;
            case R.id.btn_query:
                queryPerson();
                break;
            case R.id.btn_clean_data:
                cleanData();
                break;
            }
        }
    };

    private void cleanDb()
    {
        if (theApp.sInstance.getBoxStore(mCurrUserId) == null)
            return;

        theApp.sInstance.getPersonBox().removeAll();
        showData("已经清除数据");
    }

    private void swicthId(boolean id1)
    {
        mCurrUserId = id1 ? mUserId1 : mUserId2;
        theApp.sInstance.getBoxStore(mCurrUserId);

        showData("已经切换数据库");
    }

    private void insertPerson()
    {
        if (theApp.sInstance.getBoxStore(mCurrUserId) == null)
            return;

        Person person;

        person = new Person();
        person.id = "12345" + (mCurrUserId == mUserId1 ? "1" : "2");
        person.name = "测试员" + (mCurrUserId == mUserId1 ? "1" : "2");
        theApp.sInstance.getPersonBox().put(person);

        person = new Person();
        person.id = "12346" + (mCurrUserId == mUserId1 ? "1" : "2");
        person.name = "调试员" + (mCurrUserId == mUserId1 ? "1" : "2");
        theApp.sInstance.getPersonBox().put(person);

        showData("已经添加数据");
    }

    private void queryPerson()
    {
        if (theApp.sInstance.getBoxStore(mCurrUserId) == null)
            return;

        List<Person> list = theApp.sInstance.getPersonBox().getAll();
        StringBuffer sb = new StringBuffer();
        if (list != null)
        {
            for (Person person : list)
            {
                if (sb.length() > 0)
                {
                    sb.append("\r\n");
                }
                String str = "id:" + person.id + ", name:" + person.name;
                sb.append(str);

                Log.e("query", str);
            }
        }

        showData(sb.toString());
    }

    private void showData(String str)
    {
        if (str == null || str.length() == 0)
            return;

        TextView txt = findViewById(R.id.txt_data);
        StringBuffer sb = new StringBuffer();
        sb.append(txt.getText().toString());
        if (sb.length() > 0)
        {
            sb.append("\r\n");
        }
        sb.append(str);
        txt.setText(sb.toString());
    }

    private void cleanData()
    {
        TextView txt = findViewById(R.id.txt_data);
        txt.setText("");
    }
}
