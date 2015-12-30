package assignmentapp.sample.com.mysampleapp.controllers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Web-service caller.
 */
public class WebServiceCaller implements IWebServiceCaller{

    public IWebServiceCallBackListener mWebServiceCallBackListener;
    private JSONObject mPostData;
    private Context mContext;
    private String mURL;
    private HitNetwork mHitNetwork;

    public WebServiceCaller(Context context, String url, JSONObject postData) {
        this.mContext = context;
        this.mURL = url;
        this.mPostData = postData;
    }

    public WebServiceCaller() {

    }
    @Override
    public void setWebServiceCallBackListener(IWebServiceCallBackListener webServiceCallBackListener) {
        if (this.mWebServiceCallBackListener == null) {
            this.mWebServiceCallBackListener = webServiceCallBackListener;
        }
    }
    @Override
    public void removeWebServiceCallBackListener() {
        if (this.mWebServiceCallBackListener != null) {
            this.mWebServiceCallBackListener = null;
        }
    }
    @Override
    public void executeAsync() {
        mHitNetwork = new HitNetwork(mURL, mPostData, mContext);
        mHitNetwork.execute();
    }

    public class HitNetwork extends AsyncTask<String, Integer, String> {
        ProgressDialog progressDialog;
        String url;
        JSONObject jsonObject;
        Context mContext;
        JSONObject postData;
        String mResponse = "";
        boolean flag = false;

        public HitNetwork(String url, JSONObject postData, Context mContext) {
            this.url = url;
            this.postData = postData;
            this.mContext = mContext;
            // this.jsonObject = jsonObject;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            if (!flag) {
                progressDialog = new ProgressDialog(mContext);
                progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
                progressDialog.setMessage("Loading.....");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }
        }

        @Override
        public String doInBackground(String... params) {
            String response;
            response = postJSONData(url, postData);
            mWebServiceCallBackListener.onWebServiceStatus(response != null);
            return response;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mWebServiceCallBackListener.onWebServiceProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (!flag) {
                progressDialog.dismiss();
            }
            mResponse = result;
            mWebServiceCallBackListener.onWebServiceCompleted(result);
        }


        public String postJSONData(String URL, JSONObject jsonObject) {
            InputStream inputStream = null;
            String result = "";
            try {
                HttpParams httpParameters = new BasicHttpParams();
                // Set the timeout in milliseconds until a connection is established.
                // The default value is zero, that means the timeout is not used.
                int timeoutConnection = 10000;
                HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
                // Set the default socket timeout (SO_TIMEOUT)
                // in milliseconds which is the timeout for waiting for data.
                int timeoutSocket = 15000;
                HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

                DefaultHttpClient httpclient = new DefaultHttpClient(httpParameters);
                //////
                // 1. create HttpClient
                //HttpClient httpclient = new DefaultHttpClient();
                // 2. make POST request to the given URL
                HttpPost httpPost = new HttpPost(URL);
                String json = "";
                // 4. convert JSONObject to JSON to String
                json = jsonObject.toString();
                // ** Alternative way to convert Person object to JSON string usin
                // Jackson Lib
                // ObjectMapper mapper = new ObjectMapper();
                // json = mapper.writeValueAsString(person);

                // 5. set json to StringEntity
                StringEntity se = new StringEntity(json);

                // 6. set httpPost Entity
                httpPost.setEntity(se);

                // 7. Set some headers to inform server about the type of the
                // content
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                // 8. Execute POST request to the given URL
                HttpResponse httpResponse = httpclient.execute(httpPost);
                // 9. receive response as inputStream
                inputStream = httpResponse.getEntity().getContent();
                // 10. convert inputstream to string
                if (inputStream != null)
                    result = convertInputStreamToString(inputStream);
                else
                    result = "Did not work!";
            } catch (Exception e) {
                //Log.d("InputStream", e.getLocalizedMessage());
            }
            // Log.e("res",""+result);
            // 11. return result
            return result;
        }

        private String convertInputStreamToString(InputStream inputStream)
                throws IOException {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while ((line = bufferedReader.readLine()) != null)
                result += line;
            inputStream.close();
            return result;
        }
    }
}
