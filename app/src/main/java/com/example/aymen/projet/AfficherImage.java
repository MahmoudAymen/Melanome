package com.example.aymen.projet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AfficherImage extends AppCompatActivity {
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5;
    TextView textView,textView1,textView2,textView3,textView4;
    Button result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_image);
        imageView1= (ImageView) findViewById(R.id.i);
        imageView2= (ImageView) findViewById(R.id.imageView2);
        imageView3= (ImageView) findViewById(R.id.imageView3);
        imageView4= (ImageView) findViewById(R.id.imageView4);
        imageView5= (ImageView) findViewById(R.id.i1);
        textView= (TextView) findViewById(R.id.textView8);
        textView1= (TextView) findViewById(R.id.TextView01);
        textView2= (TextView) findViewById(R.id.TextView02);
        textView3= (TextView) findViewById(R.id.TextView03);
        textView4= (TextView) findViewById(R.id.textV);
        getImage().create().show();
        Knn1 knn1=new Knn1(this);
        knn1.open();
        ArrayList<Integer>L=getIntent().getIntegerArrayListExtra("value");
       Log.d("image ID ", "proches 1 ==" + L.get(0));
        Log.d("image ID ", "proches 1 resultat ==" + knn1.result(L.get(0)));

        Log.d("image ID ", "proches 1 ==" + L.get(1));
        Log.d("image ID ", "proches 1 resultat ==" + knn1.result(L.get(1)));

        Log.d("image ID ", "proches 1 ==" + L.get(2));
        Log.d("image ID ", "proches 1 resultat ==" + knn1.result(L.get(2)));

        Log.d("image ID ", "proches 1 ==" + L.get(3));
        Log.d("image ID ", "proches 1 resultat ==" + knn1.result(L.get(3)));

        Log.d("image ID ", "proches 1 ==" + L.get(4));
        Log.d("image ID ", "proches 1 resultat ==" + knn1.result(L.get(4)));



        Bitmap bitmap=knn1.getImage(L.get(0));
        imageView1.setImageBitmap(bitmap);
        if( knn1.result(L.get(0))==0)
        {
            textView.setText("Benign");
        }
        else
        {
            textView.setText("Melanome");
        }

        Bitmap bitmap1=knn1.getImage(L.get(1));
        imageView2.setImageBitmap(bitmap1);
        if(knn1.result(L.get(1))==0)
        {
            textView1.setText("Benign");
        }
        else
        {
            textView1.setText("Melanome");
        }
        Bitmap bitmap2=knn1.getImage(L.get(2));
        imageView3.setImageBitmap(bitmap2);
        if(knn1.result(L.get(2))==0)
        {
            textView2.setText("Benign");
        }
        else
        {
            textView2.setText("Melanome");
        }
        Bitmap bitmap3=knn1.getImage(L.get(3));
        imageView4.setImageBitmap(bitmap3);
        if(knn1.result(L.get(3))==0)
        {
            textView3.setText("Benign");
        }
        else
        {
            textView3.setText("Melanome");
        }
        Bitmap bitmap4=knn1.getImage(L.get(4));
        imageView5.setImageBitmap(bitmap4);
        if(knn1.result(L.get(4))==0)
        {
            textView4.setText("Benign");
        }
        else
        {
            textView4.setText("Melanome");
        }
        result= (Button) findViewById(R.id.retour);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AfficherImage.this.finish();
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
                Intent ii=new  Intent(getApplicationContext(),Apropos.class);
                startActivity(ii);
                return  true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
    public AlertDialog.Builder getImage() {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setTitle("Voici les 5 images les plus proche de votre resultat");
        localBuilder.setIcon(R.drawable.ay);
        localBuilder.setCancelable(false);
        localBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return localBuilder;
    }
}
