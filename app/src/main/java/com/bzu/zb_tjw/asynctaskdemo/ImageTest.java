package com.bzu.zb_tjw.asynctaskdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Create by Administrator on 9:40
 * Author：zb_tjw
 * Email：1549790231@qq.com
 * QQ：1549790231
 * 作用：
 */
public class ImageTest extends Activity {
    private ImageView mImage;
    private ProgressBar mProgressBar;
    private static String URL = "http://hbimg.b0.upaiyun.com/c8336b2643ba15883cdb7a3b26ed19185789942c24ef2-wk3Uj7_fw658";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);

        mImage = findViewById(R.id.image);
        mProgressBar = findViewById(R.id.progressbar);

        new MyAsyncTask().execute(URL);
    }

    class MyAsyncTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            Bitmap bitmap = null;
            URLConnection connection;//定义网络连接对象
            InputStream is;//用于获取数据的输入流
            try {
                //获取网络连接对象
                connection = new URL(url).openConnection();//自己手动import java.net.URL
                is = connection.getInputStream();//获取输入流
                BufferedInputStream bis = new BufferedInputStream(is);
                Thread.sleep(2000);
                bitmap = BitmapFactory.decodeStream(bis);//将输入流解析成bitmap
                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mProgressBar.setVisibility(View.GONE);
            mImage.setImageBitmap(bitmap);
        }
    }
}
