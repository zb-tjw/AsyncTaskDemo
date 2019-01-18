package com.bzu.zb_tjw.asynctaskdemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * Create by Administrator on 12:24
 * Author：zb_tjw
 * Email：1549790231@qq.com
 * QQ：1549790231
 * 作用：
 */
public class ProgressBarTest extends Activity {

    private ProgressBar progressBar;
    private MyAsyncTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        initView();
        mTask = new MyAsyncTask();
        mTask.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mTask!= null && mTask.getStatus() == AsyncTask.Status.RUNNING){
            //cancel()方法只是将对应的AsyncTask标记为cancel状态，并不是真正的取消线程的执行
            mTask.cancel(true);
        }
    }

    private void initView() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 1; i <= 100; i++) {
                if (isCancelled()){
                    break;
                }
                publishProgress(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (isCancelled()){
                return;
            }
            progressBar.setProgress(values[0]);
        }
    }
}
