package com.example.iotmobileapp.workerservice.identity;

public class UserIdentity implements IIdentity {

    private final long _deviceId;

    @Override
    public long GetDeviceId() {
        return _deviceId;
    }

    public UserIdentity(long deviceId)
    {
        _deviceId = deviceId;
    }


}
