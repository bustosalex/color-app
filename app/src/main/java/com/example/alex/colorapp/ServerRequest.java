package com.example.alex.colorapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by alex on 11/25/15.
 */
public class ServerRequest {


    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://dev.mateo.io/mobiledev/alex/";

//    public ServerRequests(Context context) {
//        progressDialog = new ProgressDialog(context);
//        progressDialog.setCancelable(false);
//        progressDialog.setTitle("Processing...");
//        progressDialog.setMessage("Please wait...");
//    }

    public void storeUserDataInBackground(User user,
                                          GetUserCallback userCallBack) {
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallBack).execute();
    }


    /**
     * parameter sent to task upon execution progress published during
     * background computation result of the background computation
     */

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void> {
        User user;
        GetUserCallback userCallBack;

        public StoreUserDataAsyncTask(User user, GetUserCallback userCallBack) {
            this.user = user;
            this.userCallBack = userCallBack;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("gender", user.getGender()));
            dataToSend.add(new BasicNameValuePair("age", user.getAge()+""));
//            dataToSend.add(new BasicNameValuePair("password", user.password));
//            dataToSend.add(new BasicNameValuePair("age", user.age + ""));

            HttpParams httpRequestParams = getHttpRequestParams();

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS
                    + "addUser.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        private HttpParams getHttpRequestParams() {
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            return httpRequestParams;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            userCallBack.done(null);
        }

    }




}
