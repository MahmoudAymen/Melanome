package com.example.aymen.projet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button testAnalyse,aide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testAnalyse= (Button) findViewById(R.id.test);
        testAnalyse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),CameraOrGallery.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in,R.anim.right_out);
            }
        });
        aide=(Button) findViewById(R.id.aide);
        aide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Aide.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.home,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.item1:
                Intent i=new Intent(getApplicationContext(),Apropos.class);
                startActivity(i);
                return  true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
