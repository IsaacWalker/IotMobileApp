package com.example.iotmobileapp.security;

import androidx.annotation.NonNull;

import com.example.iotmobileapp.BuildConfig;
import com.example.iotmobileapp.device.DeviceModel;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GatewayTokenService implements IGatewayTokenService {

    private final IJwtTokenService m_tokenService;


    private final SafetyNetClient m_safetyNetClient;


    private final DeviceModel m_deviceModel;


    private volatile String _currentToken;


    public GatewayTokenService(IJwtTokenService tokenService, SafetyNetClient client, DeviceModel model)
    {
        m_tokenService = tokenService;
        m_safetyNetClient = client;
        m_deviceModel = model;
    }


    @Override
    public String getCurrentToken() {
        Call<NonceModel> call = m_tokenService.getNonce();
        call.enqueue(NonceCallback);

        return _currentToken;
    }


    private Callback<NonceModel> NonceCallback = new Callback<NonceModel>()
    {
        @Override
        public void onResponse(Call<NonceModel> call, Response<NonceModel> response) {
            NonceModel nonce = response.body();
             m_safetyNetClient.attest(nonce.N.getBytes(),nonce.A)
            .addOnCompleteListener(new GetAttestCallback(nonce.N));
        }

        @Override
        public void onFailure(Call<NonceModel> call, Throwable t) {

        }
    };


    private Callback<String> TokenCallback = new Callback<String>()
    {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            _currentToken = response.body();
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {

        }
    };

    private class GetAttestCallback implements OnCompleteListener<SafetyNetApi.AttestationResponse>
    {
        private final String _nonce;


        public GetAttestCallback(String nonce)
        {
            _nonce = nonce;
        }


        @Override
        public void onComplete(@NonNull Task<SafetyNetApi.AttestationResponse> task) {
            SafetyNetApi.AttestationResponse attResponse =  task.getResult();
            String jwsResult = attResponse.getJwsResult();

            TokenRequestModel tokenRequestModel = new TokenRequestModel();
            tokenRequestModel.DeviceModel = m_deviceModel;
            tokenRequestModel.Nonce = _nonce;
            tokenRequestModel.AttestationCypher = jwsResult;

            Call<String> tokenCall = m_tokenService.getToken(tokenRequestModel);
            tokenCall.enqueue(TokenCallback);
        }
    }
}
