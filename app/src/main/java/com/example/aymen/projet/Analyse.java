package com.example.aymen.projet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import java.io.ByteArrayOutputStream;

public class Analyse extends AppCompatActivity {
    Button analyse, home;
    double[]tt;
    private static final String TAG = "Analyse";
    private Bitmap BitmapResult1;
    private Bitmap BitmapResult2;
    private Bitmap BitmapResult3;
    private Bitmap BitmapResult4;
    private Progressbar dialog;
    private Mat endMat;
    private Bitmap imageBmp;
    private ImageView imageView;
    private Bitmap startImage;
    private Mat startMat;
    private Mat maskMat ,segmentation;
    int[]tab;
    private lesionDetector lesionDetector;
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override

        public void onManagerConnected(int status) {
            switch (status) {
                default:
                    super.onManagerConnected(status);
                    return;
                case 0:
                    break;

            }
            Log.i(TAG, "OpenCV loaded successfully");
            ActionButton();

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyse);
        imageView= (ImageView) findViewById(R.id.imageView1);
        byte[]image1=imgBase();
        byte[]image2=imgBase1();
       Knn1 db=new Knn1(this);
        db.open();
      //  db.update(25);
        //db.delete(43);
        //db.vide();
    // db.insert(new information(image2,0.8894531897041894, -0.9719557797402519,-1.9131285571851318,2.3424042476875073E12,1.4640169033070964E12, 1.2335673612162525, 0, 0));
       // Log.d("azerty","Ahla");
        ActionButton();
    }
    private byte[]imgBase()
    {
        byte[]arrayOfByte=new byte[0];
        if (getIntent().hasExtra("image1"))
        {
            arrayOfByte = getIntent().getByteArrayExtra("image1");
            startImage = BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length);
            imageView.setImageBitmap(startImage);

        }
        return arrayOfByte;
    }
    private byte[]imgBase1()
    {
        byte[]arrayOfByte=new byte[0];
        if (getIntent().hasExtra("IMG")) {

             arrayOfByte = getIntent().getByteArrayExtra("IMG");
            startImage = BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length);
            imageView.setImageBitmap(startImage);

        }
        return arrayOfByte;
    }

    private void ActionButton() {
        analyse= (Button) findViewById(R.id.analyse);
        analyse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processImage p=new processImage(Analyse.this);
                p.execute(new Void[0]);
            }
        });
        home= (Button) findViewById(R.id.accueil);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),CameraOrGallery.class);
                startActivity(i);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
    }

    private void Detect() {
        startMat = new Mat();
        endMat = new Mat();
        maskMat = new Mat();
        segmentation=new Mat() ;
        new Mat();
        Log.i(TAG, "OpenCV 1 ");
        this.imageBmp = this.startImage;
        Utils.bitmapToMat(imageBmp, startMat);
        lesionDetector = new lesionDetector();
        lesionDetector.Segment(startMat);
        this.tt =lesionDetector.getvals();
        Log.i(TAG, "OpenCV 2 ");
        endMat = lesionDetector.getRGB();
        Mat localMat2 = lesionDetector.getMat(endMat);
        BitmapResult1 = Bitmap.createBitmap(localMat2.width(), localMat2.height(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(localMat2, BitmapResult1);
        endMat=lesionDetector.getMclose();
        Mat localMat3 = lesionDetector.getMat(endMat);
        BitmapResult2 = Bitmap.createBitmap(localMat3.width(), localMat3.height(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(localMat3, BitmapResult2);
        maskMat=lesionDetector.getThresholdMat();
        segmentation=lesionDetector.getMat(maskMat);
        BitmapResult3 = Bitmap.createBitmap(segmentation.width(), segmentation.height(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(segmentation, this.BitmapResult3);
        endMat = lesionDetector.getshape2();
        Mat localMat4 = lesionDetector.getMat(endMat);
        BitmapResult4 = Bitmap.createBitmap(localMat4.width(), localMat4.height(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(localMat4, BitmapResult4);
        this.tt =lesionDetector.getvals();
        double E=lesionDetector.Entropy(tab);
        Log.d("TAG","Entropy="+E);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "opencv not Loaded");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_11, this, this.mLoaderCallback);
        } else {
            Log.d(TAG, "opencv Loaded");

        }

    }

    private class processImage extends AsyncTask<Void, Void, String> {
        Context context;

        public processImage(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new Progressbar(this.context, R.drawable.bar);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(Void[] paramArrayOfVoid) {
            try {
                Detect();
                return null;
            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();
            Intent localIntent = new Intent(getBaseContext(), Resultat.class);
            ByteArrayOutputStream localByteArrayOutputStream1 = new ByteArrayOutputStream();
            BitmapResult1.compress(Bitmap.CompressFormat.PNG, 50, localByteArrayOutputStream1);
            localIntent.putExtra("image1", localByteArrayOutputStream1.toByteArray());
            ByteArrayOutputStream localByteArrayOutputStream2 = new ByteArrayOutputStream();
            BitmapResult2.compress(Bitmap.CompressFormat.PNG, 50, localByteArrayOutputStream2);
            localIntent.putExtra("image2", localByteArrayOutputStream2.toByteArray());
            ByteArrayOutputStream localByteArrayOutputStream3 = new ByteArrayOutputStream();
            BitmapResult3.compress(Bitmap.CompressFormat.PNG, 50, localByteArrayOutputStream3);
            localIntent.putExtra("image3", localByteArrayOutputStream3.toByteArray());
            ByteArrayOutputStream localByteArrayOutputStream4 = new ByteArrayOutputStream();
            BitmapResult4.compress(Bitmap.CompressFormat.PNG, 50, localByteArrayOutputStream4);
            localIntent.putExtra("image4", localByteArrayOutputStream4.toByteArray());
            localIntent.putExtra("value",Analyse.this.tt);
            startActivity(localIntent);
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


