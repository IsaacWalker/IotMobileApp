package com.example.iotmobileapp.gdpr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.iotmobileapp.APIClient;
import com.example.iotmobileapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivacyActivity extends AppCompatActivity {
    private Button accessRequestBtn, erasureRequestButton, policyButton, informButton;
    private IGDPRServiceClient m_gdprClient;
    private int _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        _id = getIntent().getIntExtra("Id", 0);
        accessRequestBtn = findViewById(R.id.accessRequestBtn);
        erasureRequestButton = findViewById(R.id.erasureRequestBtn);
        policyButton = findViewById(R.id.privPolicBtn);
        informButton = findViewById(R.id.portRequestBtn);

        accessRequestBtn.setOnClickListener(AccessRequestButtonListener);
        erasureRequestButton.setOnClickListener(ErasureRequestButtonListener);
        policyButton.setOnClickListener(PolicyButtonListener);
        informButton.setOnClickListener(InformationButtonListener);

        m_gdprClient = APIClient.getClient("http://www.gdpr.iotrelationshipfyp.com")
                .create(IGDPRServiceClient.class);


    }

    private View.OnClickListener AccessRequestButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(PrivacyActivity.this, AccessRequestActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener ErasureRequestButtonListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(PrivacyActivity.this);
            builder.setMessage("Are you sure you want to Erase all data?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }
    };

    private View.OnClickListener PolicyButtonListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(PrivacyActivity.this, PolicyActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener InformationButtonListener = new View.OnClickListener()
    {
        String url = "http://www.portal.iotrelationshipfyp.com/imgs/edit.png";

        @Override
        public void onClick(View view) {
            Log.d("URL", url);
            new DownloadTask(PrivacyActivity.this,url);
        }
    };

    private Callback<SubjectAccessRequestModel> SarCallback = new Callback<SubjectAccessRequestModel>() {
        @Override
        public void onResponse(Call<SubjectAccessRequestModel> call, Response<SubjectAccessRequestModel> response) {
            SubjectAccessRequestModel model = response.body();
        }

        @Override
        public void onFailure(Call<SubjectAccessRequestModel> call, Throwable t) {

        }
    };

    private Callback<Void> SerCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {

        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {

        }
    };

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    Call<Void> call =m_gdprClient.DeleteSubjectData(_id);
                    call.enqueue(SerCallback);
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        }
    };
}
