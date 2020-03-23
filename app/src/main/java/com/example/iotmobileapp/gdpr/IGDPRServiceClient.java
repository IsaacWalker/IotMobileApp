package com.example.iotmobileapp.gdpr;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IGDPRServiceClient {

    @GET("/api/subjectData")
    Call<SubjectAccessRequestModel> GetSubjectData(@Query("deviceId") int deviceId);

    @DELETE("/api/subjectData")
    Call<Void> DeleteSubjectData(@Query("deviceId") int deviceId);
}
