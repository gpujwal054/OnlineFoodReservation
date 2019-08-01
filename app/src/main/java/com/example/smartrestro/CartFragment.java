package com.example.smartrestro;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.util.ExceptionCatchingInputStream;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import khalti.checkOut.api.Config;
import khalti.checkOut.api.OnCheckOutListener;
import khalti.checkOut.helper.KhaltiCheckOut;
import khalti.widget.KhaltiButton;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;


public class CartFragment extends Fragment implements View.OnClickListener{
    KhaltiButton khaltiButton;
    Config config = new Config("test_public_key_f4550a60cfb14063b1ecbad7598a681f", "1", "abc", "abc.com", (long)1000, new OnCheckOutListener() {

        @Override
        public void onSuccess(HashMap<String, Object> data) {
            Log.i("Payment confirmed", data+"");
            try{
                postRequest(data);
            }
            catch (Exception e){
                
                Log.d("Error", e.toString());
            }
        }

        @Override
        public void onError(String action, String message) {
            Log.i(action, message);
        }
    });

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        KhaltiButton khaltiButton = (KhaltiButton) getView().findViewById(R.id.khalti_button);
//
        View v = inflater.inflate(R.layout.fragment_cart, container, false);
        khaltiButton = v.findViewById(R.id.khalti_button);
        khaltiButton.setOnClickListener(CartFragment.this);
        return v;
    }


    @Override
    public void onClick(View view) {
        KhaltiCheckOut khaltiCheckOut = new KhaltiCheckOut(getContext(), config);
        khaltiCheckOut.show();
    }

    public void postRequest(HashMap<String, Object> data) throws IOException {

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = "https://khalti.com/api/v2/payment/verify/";

        OkHttpClient client = new OkHttpClient();

        JSONObject postdata = new JSONObject();
        try {
            postdata.put("token", data.get("token"));
            postdata.put("amount", data.get("amount"));
        } catch(JSONException e){
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization", "Key test_secret_key_d16ef4f1e9604068ba81d67d3b2d4b01")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String mMessage = response.body().string();
            }
        });
    }


}
