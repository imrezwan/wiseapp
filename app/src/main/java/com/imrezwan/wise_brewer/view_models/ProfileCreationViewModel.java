package com.imrezwan.wise_brewer.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileCreationViewModel extends ViewModel {
    private final MutableLiveData<String> data = new MutableLiveData<>();

    public LiveData<String> getData() {
        return data;
    }

    public void setData(String value) {
        data.setValue(value);
    }

}
