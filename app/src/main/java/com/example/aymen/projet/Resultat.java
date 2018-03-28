package com.example.aymen.projet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Resultat extends AppCompatActivity  {
    protected static final String TAG = "Resultat";
    private Bitmap BitmapResults1;
    private Bitmap BitmapResults2;
    private Bitmap BitmapResults3;
    private Bitmap BitmapResults4;
    double[]tab;
    private Button afficher;
    private TextView textView, textView1;
    String str = " Vous risquez d'étre atteint par mélanome ";
    String str1 = " vous êtes en bon santé";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        textView= (TextView) findViewById(R.id.t);
        textView1= (TextView) findViewById(R.id.textView);
        afficher= (Button) findViewById(R.id.retour);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("Results", "Start Thread");
                ImageView localImageView1 = (ImageView) findViewById(R.id.i);
                ImageView localImageView2 = (ImageView) findViewById(R.id.imageView2);
                ImageView localImageView3 = (ImageView) findViewById(R.id.imageView3);
                ImageView localImageView4 = (ImageView) findViewById(R.id.imageView4);
                byte[] arrayOfByte1 = getIntent().getByteArrayExtra("image1");
                BitmapResults1 = BitmapFactory.decodeByteArray(arrayOfByte1, 0, arrayOfByte1.length);
                byte[] arrayOfByte2 = getIntent().getByteArrayExtra("image2");
                BitmapResults2 = BitmapFactory.decodeByteArray(arrayOfByte2, 0, arrayOfByte2.length);
                byte[] arrayOfByte3 = getIntent().getByteArrayExtra("image3");
                BitmapResults3 = BitmapFactory.decodeByteArray(arrayOfByte3, 0, arrayOfByte3.length);
                byte[] arrayOfByte4 = getIntent().getByteArrayExtra("image4");
                BitmapResults4 = BitmapFactory.decodeByteArray(arrayOfByte4, 0, arrayOfByte4.length);
                while (true) {
                    localImageView1.setImageBitmap(BitmapResults1);
                    localImageView2.setImageBitmap(BitmapResults4);
                    localImageView3.setImageBitmap(BitmapResults3);
                    localImageView4.setImageBitmap(BitmapResults2);

                    break;
                }

            }
        });
      Knn1 knn1=new Knn1(this);
        knn1.open();
        int n=knn1.getcount();
        tab=getIntent().getDoubleArrayExtra("value");
        Log.d("result","1=="+tab[0]);
        Log.d("result","1=="+tab[1]);
        Log.d("result","1=="+tab[2]);
        Log.d("result","1=="+tab[3]);
        Log.d("result","1=="+tab[4]);
        Log.d("result","1=="+tab[5]);
        Log.d("result","1=="+tab[6]);
       final Knn k=new Knn(n,this);
        int res=k.calculDistance(tab);
       String x= k.GetTOP().toString();
        Log.d("result","tester top final "+x);
        Log.d("image ID ", "tester  top final id ==" + k.GetTOP().get(0));
        Log.d("image ID ", "tester  top final resultat ==" + knn1.result(k.GetTOP().get(0)));
        Log.d("image ID ", "tester  top final id==" + k.GetTOP().get(1));
        Log.d("image ID ", "tester  top final resultat ==" + knn1.result(k.GetTOP().get(1)));
        Log.d("image ID ", "tester  top final id ==" + k.GetTOP().get(2));
        Log.d("image ID ", "tester  top final resultat ==" + knn1.result(k.GetTOP().get(2)));
        Log.d("image ID ", "tester  top final id ==" + k.GetTOP().get(3));
        Log.d("image ID ", "tester  top final resultat ==" + knn1.result(k.GetTOP().get(3)));
        Log.d("image ID ", "tester  top final id ==" + k.GetTOP().get(4));
        Log.d("image ID ", "tester  top final resultat ==" + knn1.result(k.GetTOP().get(4)));

        if(res==0)
        {
            TextPaint t=new TextPaint(0);
            t.setColor(textView.getResources().getColor(R.color.text_primary));
            textView.setText("Benign !");
            textView1.setText(str1);
            textView.getCurrentTextColor();
            textView.getResources().getColor(R.color.text_primary);


        }
        else
        {
            textView.setText("Attention !");
            textView1.setText(str);
            textView.getCurrentTextColor();
            textView.getResources().getColor(R.color.text_primary);

        }
        afficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Resultat.this,AfficherImage.class);
                i.putIntegerArrayListExtra("value",k.GetTOP());
                startActivity(i);
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.men,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.item1:
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                return  true;
            case R.id.item2:
                Intent ii=new Intent(getApplicationContext(),Apropos.class);
                startActivity(ii);
                return  true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

}
