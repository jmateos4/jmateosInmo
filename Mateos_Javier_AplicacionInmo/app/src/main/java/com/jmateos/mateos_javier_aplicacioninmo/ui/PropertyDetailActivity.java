package com.jmateos.mateos_javier_aplicacioninmo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jmateos.mateos_javier_aplicacioninmo.R;
import com.jmateos.mateos_javier_aplicacioninmo.fragment.PropertiesFragment;
import com.jmateos.mateos_javier_aplicacioninmo.fragment.PropertyDetailFragment;
import com.jmateos.mateos_javier_aplicacioninmo.listener.PropertiesInteractionListener;

public class PropertyDetailActivity extends AppCompatActivity implements PropertiesInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        if (savedInstanceState == null) {

            Bundle arguments = new Bundle();
            arguments.putString(PropertyDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(PropertyDetailFragment.ARG_ITEM_ID));
            PropertyDetailFragment fragment = new PropertyDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.property_detail_container, fragment)
                    .commit();


        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, PropertiesFragment.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
