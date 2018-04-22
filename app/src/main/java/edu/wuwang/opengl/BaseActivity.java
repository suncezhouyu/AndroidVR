package edu.wuwang.opengl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;

/**
 * Created by wuwang on 2016/10/30
 */

public class BaseActivity extends /*AppCompat*/Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("OpenGL Demo");
    }

}
