package edu.wuwang.opengl.vr;

import android.content.Context;
//import android.hardware.Sensor;
//import android.hardware.SensorEvent;
//import android.hardware.SensorEventListener;
//import android.hardware.SensorManager;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import edu.wuwang.opengl.BaseActivity;
import edu.wuwang.opengl.R;
import java.util.List;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by aiya on 2017/5/19.
 */

public class VrContextActivity extends BaseActivity implements GLSurfaceView.Renderer/*SensorEventListener*/ {

    private GLSurfaceView mGLView;
    //private SensorManager mSensorManager;
    //private Sensor mRotation;
    private SkySphere mSkySphere;
    int m_iAngle = 0;

    private float[] matrix=new float[16];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glview);

        //todo 判断是否存在rotation vector sensor
        //mRotation=mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        mGLView=(GLSurfaceView) findViewById(R.id.mGLView);
        mGLView.setEGLContextClientVersion(2);
        mGLView.setRenderer(this);
        mGLView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        mSkySphere=new SkySphere(this.getApplicationContext(),"vr/earth2.jpg");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mSensorManager.registerListener(this,mRotation,SensorManager.SENSOR_DELAY_GAME);
        mGLView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mSensorManager.unregisterListener(this);
        mGLView.onPause();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mSkySphere.create();
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glCullFace(GLES20.GL_FRONT);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        mSkySphere.setSize(width, height);
        GLES20.glViewport(0,0,width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClearColor(0,0,0,1);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT|GLES20.GL_DEPTH_BUFFER_BIT);
        /*
        float [] arrSpinMat = new float[16];
        float [] arrTanslateMat = new float[16];
        float [] arrModelMat = new float[16];
        m_iAngle = m_iAngle + 1;
        if(360 == m_iAngle)
        {
            m_iAngle = 0;
        }
        mSkySphere.vGetSpinningMatrix((float)m_iAngle, 0,0,1, arrSpinMat);
        mSkySphere.vGetTranslationMatrix(0, 0,0,arrTanslateMat);
        Matrix.multiplyMM(arrModelMat, 0, arrTanslateMat, 0, arrSpinMat,0);
        mSkySphere.vSetModelMatrix(arrModelMat);
        */
        mSkySphere.draw();
    }

    /*@Override
    public void onSensorChanged(SensorEvent event) {
        SensorManager.getRotationMatrixFromVector(matrix,event.values);
        mSkySphere.setMatrix(matrix);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    */
}
