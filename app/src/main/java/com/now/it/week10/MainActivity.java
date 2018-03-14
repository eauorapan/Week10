package com.now.it.week10;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    Button btn1;
    String outputStr = "";
    int number = 0;
    ProgressBar pb;
    ImageView imv;
    ByteArrayOutputStream byeBuffer = new ByteArrayOutputStream();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.tv);
        btn1 = findViewById(R.id.button1);
        pb = findViewById(R.id.progressBar3);
        imv = findViewById(R.id.imageView);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                outputStr += "\n";

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <= 100; i++) {
                            final int value = i;

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv1.setText("" + value);
                                    pb.setProgress(value);
                                }
                            });

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }).start();

                //exampel();
                //exampel2();
                exampel5();

            }
        });

    }

    private void exampel() {

        for (int i = 0; i < 5; i++) {
            outputStr += "A";

        }
        for (int i = 0; i < 5; i++) {
            outputStr += "B";

        }
        tv1.setText(outputStr);
    }

    private void exampel2() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    outputStr += "A";

                }
            }

        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    outputStr += "B";

                }

            }
        }).start();

        tv1.setText(outputStr);
    }


    private void exampel5() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    URL newurl = new URL("https://pokemontrainer.in.th/images/stories/pokemon/025_02.png");
                    //final Bitmap bimap = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());

                    HttpURLConnection con = (HttpURLConnection) newurl.openConnection();
                    InputStream is = con.getInputStream();
                    int imgSize = con.getContentLength();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    int sum = 0;
                    while ((len = is.read(buffer)) > 0)//)
                    {
                        byeBuffer.write(buffer, 0, len);
                        sum += len;
                        float percent = (sum * 100.0f) / imgSize;
                        pb.setProgress((int)percent);
                        //pb.setImageBitmap(bmp);
                    }
                    imv.post(new Runnable() {
                        public void run() {
                            Bitmap bmp = BitmapFactory.decodeByteArray(byeBuffer.toByteArray(), 0, byeBuffer.size());
                            imv.setImageBitmap(bmp);
                        }
                    });
                }catch (Exception e)
                {

                }


                    imv.post(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap bmp = BitmapFactory.decodeByteArray(byeBuffer.toByteArray(),0,byeBuffer.size());
                            imv.setImageBitmap(bmp);

                        }
                    });
            }

        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    outputStr += "B";

                }

            }
        }).start();

        tv1.setText(outputStr);
    }

}