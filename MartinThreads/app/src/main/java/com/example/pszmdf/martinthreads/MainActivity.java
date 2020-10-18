package com.example.pszmdf.martinthreads;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    // a placeholder function that does something, but takes its time about it
    public Bitmap doWork(long time) {
        Bitmap bitmap = Bitmap.createBitmap(1000,1000, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Rect pixel = new Rect();
        Random rand = new Random();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        for (int x = 0; x < 1000; x += 50) {
            for (int y = 0; y < 1000; y += 50) {
                pixel.set(x, y, x + 50, y + 50);
                paint.setColor(Color.rgb(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
                canvas.drawRect(pixel, paint);
            }
        }

        try{Thread.sleep(time);}catch(Exception e){;}

        return bitmap;
    }

    public void onClickDoSomething(View v) {
        imageView.setImageBitmap(doWork(0));
    }

    public void onClickDoSomethingBadly(View v) {
        imageView.setImageBitmap(doWork(10000));
    }

    Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Bundle b = msg.getData();
            Bitmap result = b.getParcelable("bitmap");
            imageView.setImageBitmap(result);
            return false;
        }
    };
    Handler mainHandler = new Handler(Looper.myLooper(), callback);

    public void onClickPostRunnable(View v) {

        new Thread(new Runnable() {

            public void run() {

                final Bitmap result = doWork(10000);
                imageView.setImageBitmap(result);

                mainHandler.post(new Runnable() {

                    public void run() {
                        imageView.setImageBitmap(result);
                    }
                });
            }
        }).start();
    }


    public void onClickPostMessage(View v)
    {
        new Thread(new Runnable() {

            public void run() {

                Bitmap result = doWork(10000);

                Message msg = new Message();
                Bundle b = new Bundle();
                b.putParcelable("bitmap", result);
                msg.setData(b);
                mainHandler.sendMessage(msg);
            }
        }).start();
    }



    public void onClickAsyncTask(View v) {
        new MyAsyncTask().execute(10000L);
    }

    private class MyAsyncTask extends AsyncTask<Long, Void, Bitmap> {

        protected Bitmap doInBackground(Long... time) {
            return doWork(time[0]);
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);;
        }
    }


    public void onClickStartThread(View v) {

        new Thread(new Runnable() {

            public void run() {
                int i = 0;
                while(true) {
                    Log.d("g53mdp", "Counting " + i++);
                    try{Thread.sleep(1000);}catch(Exception e){;}
                }
            }
        }).start();
    }
}
