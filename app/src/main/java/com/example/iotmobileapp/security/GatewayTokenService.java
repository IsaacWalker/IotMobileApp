package com.example.iotmobileapp.security;

import com.example.iotmobileapp.BuildConfig;
import com.example.iotmobileapp.device.DeviceModel;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetClient;
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
        Call<String> call = m_tokenService.getNonce();
        call.enqueue(NonceCallback);

        return _currentToken;
    }


    private Callback<String> NonceCallback = new Callback<String>()
    {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            String nonce = response.body();
            SafetyNetApi.AttestationResponse attResponse =  m_safetyNetClient.attest(nonce.getBytes(),null)
            .getResult();

            String jwsResult = attResponse.getJwsResult();

            TokenRequestModel tokenRequestModel = new TokenRequestModel();
            tokenRequestModel.DeviceModel = m_deviceModel;
            tokenRequestModel.Nonce = nonce;
            tokenRequestModel.AttestationCypher = jwsResult;

            Call<String> tokenCall = m_tokenService.getToken(tokenRequestModel);
            tokenCall.enqueue(TokenCallback);
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {

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
}
