package com.example.aymen.projet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Aide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aide);

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
