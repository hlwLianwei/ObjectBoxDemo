package com.lenway.objbox;

import android.app.Application;
import android.content.Context;

import com.lenway.objbox.entity.MyObjectBox;
import com.lenway.objbox.entity.Person;
import com.lenway.objbox.entity.Project;

import java.io.File;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

public class theApp extends Application
{
    public static Context sContext = null;
    public static theApp sInstance = null;
    private static String sCurrUserId = "";
    private static BoxStore sBoxStore = null;
    private static Box<Project> sProjectBox = null;
    private static Box<Person> sPersonBox = null;

    @Override
    public void onCreate()
    {
        super.onCreate();

        sContext = this;
        sInstance = this;
    }

    public BoxStore getBoxStore(String currUserId) {

        if (sCurrUserId != currUserId)
        {
            resetBoxStore();
        }

        if (currUserId != "" && currUserId != sCurrUserId)
        {
            // 默认位置 /data/data/包名/files/objectbox/data.mdb
            //sBoxStore = MyObjectBox.builder().androidContext(this).build();

            // 其他位置 /storage/emulated/0/Android/data/包名/files/objectbox_(用户id)/data.mdb
            String strPath = getExternalFilesDir("").getAbsolutePath() + "/objectbox_" + currUserId;
            sBoxStore = MyObjectBox.builder().androidContext(this).directory(new File(strPath)).build();
            if (BuildConfig.DEBUG) {
                new AndroidObjectBrowser(sBoxStore).start(this);
            }

            sCurrUserId = currUserId;
        }

        return sBoxStore;
    }

    private void resetBoxStore()
    {
        try
        {
            if (sProjectBox != null)
            {
                sProjectBox.query().close();
                sProjectBox = null;
            }

            if (sPersonBox != null)
            {
                sPersonBox.query().close();
                sPersonBox = null;
            }

            if (sBoxStore != null)
            {
                sBoxStore.close();
            }
        }
        catch (Exception e)
        {
        }
    }

    public Box<Project> getProjectBox()
    {
        if (sProjectBox == null && sBoxStore != null)
        {
            sProjectBox = sBoxStore.boxFor(Project.class);
        }
        return sProjectBox;
    }

    public Box<Person> getPersonBox()
    {
        if (sPersonBox == null && sBoxStore != null)
        {
            sPersonBox = sBoxStore.boxFor(Person.class);
        }
        return sPersonBox;
    }
}
