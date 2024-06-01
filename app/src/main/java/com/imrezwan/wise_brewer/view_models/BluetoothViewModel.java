package com.imrezwan.wise_brewer.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imrezwan.wise_brewer.enums.Connection;

public class BluetoothViewModel extends ViewModel {
    private final MutableLiveData<Connection> bluetoothStatus = new MutableLiveData<>();

    public LiveData<Connection> getBluetoothStatus() {
        return bluetoothStatus;
    }

    public void setBluetoothStatus(Connection connection) {
        bluetoothStatus.setValue(connection);
    }
}