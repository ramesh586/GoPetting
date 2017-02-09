package com.ramesh.gsignin.Util;

/**
 * Created by thrymr on 8/2/17.
 */

import com.ramesh.gsignin.Model.DataList;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {
    @GET("service/v2/upcomingGuides/")
    //Call<List<Data>> getDataDetails();
    Call<DataList> getDataDetails();
}