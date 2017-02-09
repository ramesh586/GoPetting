package com.ramesh.gsignin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.ramesh.gsignin.Adapters.DataAdapter;
import com.ramesh.gsignin.Model.SingleTon;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DataAdapter(SingleTon.getmInstance().getCartList(),
                R.layout.list_item_data, this,true));
        findViewById(R.id.cartBt).setVisibility(View.GONE);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Cart Item");
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
