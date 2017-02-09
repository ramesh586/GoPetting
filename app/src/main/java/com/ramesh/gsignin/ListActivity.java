package com.ramesh.gsignin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.ramesh.gsignin.Adapters.DataAdapter;
import com.ramesh.gsignin.Model.Data;
import com.ramesh.gsignin.Model.DataList;
import com.ramesh.gsignin.Util.ApiClient;
import com.ramesh.gsignin.Util.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = ListActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        getData();
        findViewById(R.id.cartBt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCartClick(view);
            }
        });
    }

    private void getData() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<DataList> call = apiService.getDataDetails();
        call.enqueue(new Callback<DataList>() {

            @Override
            public void onResponse(Call<DataList> call, Response<DataList> response) {
                List<Data> dataList = response.body().getDataList();
                recyclerView.setAdapter(new DataAdapter(dataList, R.layout.list_item_data, ListActivity.this, false));
            }

            @Override
            public void onFailure(Call<DataList> call, Throwable t) {
                Log.d(TAG, "failure" + t.getMessage());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == Menu.FIRST) {


           /// setResult(RESULT_OK);
            finish();


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menu.add(0,Menu.FIRST,  0, "Logout").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void onCartClick(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}
