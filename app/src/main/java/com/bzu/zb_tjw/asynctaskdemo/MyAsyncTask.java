package com.bzu.zb_tjw.asynctaskdemo;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Create by Administrator on 17:54
 * Author：zb_tjw
 * Email：1549790231@qq.com
 * QQ：1549790231
 * 作用：
 */
public class MyAsyncTask extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        Log.i("tag","doInBackground");
        publishProgress();//调用onProgressUpdate()方法
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i("tag","onPreExecute");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.i("tag","onPostExecute");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Log.i("tag","onProgressUpdate");
    }
}
