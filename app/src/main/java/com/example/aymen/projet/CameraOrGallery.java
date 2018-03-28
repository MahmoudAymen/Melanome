package com.example.aymen.projet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class CameraOrGallery extends AppCompatActivity implements View.OnClickListener{
    Button gallery,camera;
    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_or_gallery);
        imageView=(ImageView)findViewById(R.id.imageView1);
        gallery= (Button) findViewById(R.id.gallery);
        camera= (Button) findViewById(R.id.camera);
        gallery.setOnClickListener(this);
        camera.setOnClickListener(this);
    }
    private void OuvrirGallery()
    {
        Intent i=new Intent();
        i.setType("image/*");
        i.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(i, "select Picture"), 1);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((resultCode == -1) && (requestCode == 1) && (data != null)) {
            Intent i = new Intent(getBaseContext(), Analyse.class);
            Uri uri = data.getData();
            imageView.setImageURI(uri);
            bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
            i.putExtra("IMG", byteArrayOutputStream.toByteArray());
            startActivity(i);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.gallery:
               OuvrirGallery();
                break;
            case R.id.camera:
                Intent i1=new Intent(CameraOrGallery.this,Camira.class);
                startActivity(i1);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                break;
            default:
                break;
        }
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
