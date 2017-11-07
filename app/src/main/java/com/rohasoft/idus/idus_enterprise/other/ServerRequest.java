package com.rohasoft.idus.idus_enterprise.other;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

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
 * Created by Ayothi selvam on 30-10-2017.
 */

public class ServerRequest {
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;

    public static final String SERVER_ADDRESS = "http://www.idusmarket.com/loan-app/app/";
//    public static final String SERVER_ADDRESS = "http://127.0.0.1/loan_app/add_cus.php";
  //  public static final String SERVER_ADDRESS = "http://localhost/loan_app/";


    public ServerRequest(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("processing");
        progressDialog.setMessage("Please wait...");
    }

    public void storeUserDataInBackground(User user, GetUserCallback userCallback) {
        progressDialog.show();
        new storeUserDataAsyncTask(user, userCallback).execute();

    }

    public void storeCustomerDataInBackground(Customer customer, GetCustomerCallBack customerCallBack) {
        progressDialog.show();
        new storeCustomerDataAsyncTask(customer, customerCallBack).execute();

    }
    public void storeLoanDataInBackground(Loan loan, GetLoanCallBack  loanCallBack) {
        progressDialog.show();
        new storeLoanDataAsyncTask(loan, loanCallBack).execute();

    }

    public void storeCollectDataInBackground(CollectLoan collectLoan, GetCollectLoanCallBack  collectLoanCallBack) {
        progressDialog.show();
        new storeCollectLoanDataAsyncTask(collectLoan, collectLoanCallBack).execute();

    }


    public void fetchUserDataInBackground(User user, GetUserCallback callBack) {
        progressDialog.show();
         new FetchUserDataAsyncTask(user,callBack).execute();
    }

    public class storeCustomerDataAsyncTask extends AsyncTask<Void, Void, Void>{

        Customer customer;
        GetCustomerCallBack customerCallBack;

        storeCustomerDataAsyncTask(Customer customer,GetCustomerCallBack customerCallBack){
            this.customer=customer;
            this.customerCallBack=customerCallBack;
        }

        @Override
        protected Void doInBackground(Void... params) {

            ArrayList<NameValuePair> dataToSend=new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("customerName",customer.customerName));
            dataToSend.add(new BasicNameValuePair("phone",customer.phone));
            dataToSend.add(new BasicNameValuePair("address",customer.address));
            dataToSend.add(new BasicNameValuePair("city",customer.city));
            dataToSend.add(new BasicNameValuePair("pincode",customer.pincode));
            dataToSend.add(new BasicNameValuePair("mapLan",customer.mapLan));
            dataToSend.add(new BasicNameValuePair("mapLac",customer.mapLac));
            dataToSend.add(new BasicNameValuePair("remaks",customer.remaks));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "add_cus.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            customerCallBack.done(null);
            super.onPostExecute(aVoid);
        }
    }




    public class storeUserDataAsyncTask extends AsyncTask<Void, Void, Void> {

        User user;
        GetUserCallback userCallback;

        public storeUserDataAsyncTask(User user, GetUserCallback userCallback) {

            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("name", user.name));
            dataToSend.add(new BasicNameValuePair("email", user.email));
            dataToSend.add(new BasicNameValuePair("phone_no", user.phone_no));
            dataToSend.add(new BasicNameValuePair("password", user.pass));
            dataToSend.add(new BasicNameValuePair("otp", user.otp));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "register.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            userCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }
    public class storeLoanDataAsyncTask extends AsyncTask<Void, Void, Void> {

        Loan loan;
        GetLoanCallBack loanCallBack;

        public storeLoanDataAsyncTask(Loan loan, GetLoanCallBack loanCallBack) {

            this.loan = loan;
            this.loanCallBack = loanCallBack;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("customer_name", loan.customer_name));
            dataToSend.add(new BasicNameValuePair("customer_id", loan.customer_id));
            dataToSend.add(new BasicNameValuePair("phone", loan.phone));
            dataToSend.add(new BasicNameValuePair("address", loan.address));
            dataToSend.add(new BasicNameValuePair("city", loan.city));
            dataToSend.add(new BasicNameValuePair("pincode", loan.pincode));
            dataToSend.add(new BasicNameValuePair("loan_amount", loan.loan_amount));
            dataToSend.add(new BasicNameValuePair("loan_option", loan.loan_option));
            dataToSend.add(new BasicNameValuePair("loan_duration", loan.loan_duration));
            dataToSend.add(new BasicNameValuePair("start_date", loan.start_date));
            dataToSend.add(new BasicNameValuePair("end_date", loan.end_date));
            dataToSend.add(new BasicNameValuePair("remarks", loan.remarks));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "add_loan.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            loanCallBack.done(null);
            super.onPostExecute(aVoid);
        }
    }
    public class storeCollectLoanDataAsyncTask extends AsyncTask<Void, Void, Void>{

        CollectLoan collectLoan;
        GetCollectLoanCallBack collectLoanCallBack;

        storeCollectLoanDataAsyncTask(CollectLoan collectLoan,GetCollectLoanCallBack collectLoanCallBack){
            this.collectLoan=collectLoan;
            this.collectLoanCallBack=collectLoanCallBack;
        }

        @Override
        protected Void doInBackground(Void... params) {

            ArrayList<NameValuePair> dataToSend=new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("customerName",collectLoan.customerName));
            dataToSend.add(new BasicNameValuePair("loanId",collectLoan.loanId));
            dataToSend.add(new BasicNameValuePair("phone",collectLoan.phone));
            dataToSend.add(new BasicNameValuePair("city",collectLoan.city));
            dataToSend.add(new BasicNameValuePair("total_amount",collectLoan.total_amount));
            dataToSend.add(new BasicNameValuePair("due_amount",collectLoan.due_amount));
            dataToSend.add(new BasicNameValuePair("balance_amount",collectLoan.balance_amount));
            dataToSend.add(new BasicNameValuePair("due_date",collectLoan.due_date));
            dataToSend.add(new BasicNameValuePair("due_paid_date",collectLoan.due_paid_date));
            dataToSend.add(new BasicNameValuePair("due_paid_amount",collectLoan.due_paid_amount));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "collect_loan.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            collectLoanCallBack.done(null);
            super.onPostExecute(aVoid);
        }
    }

    public class FetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {

        User user;
        GetUserCallback userCallback;

        public FetchUserDataAsyncTask(User user, GetUserCallback userCallback) {

            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected User doInBackground(Void... params) {

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();

            dataToSend.add(new BasicNameValuePair("email", user.email));
            dataToSend.add(new BasicNameValuePair("password", user.pass));


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "login.php");

            User returnedUser = null;

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jobject = new JSONObject(result);


                if (jobject.length() == 0) {

                    returnedUser = null;

                } else {
                    String email = jobject.getString("email");
                    String password = jobject.getString("password");
                    returnedUser = new User(email, password);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return returnedUser;
        }

        @Override
        protected void onPostExecute(User returnedUser) {
            progressDialog.dismiss();
            userCallback.done(returnedUser);
            super.onPostExecute(returnedUser);
        }

    }


}
