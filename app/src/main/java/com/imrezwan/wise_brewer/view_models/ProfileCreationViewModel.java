package com.imrezwan.wise_brewer.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imrezwan.wise_brewer.models.ProfileData;

public class ProfileCreationViewModel extends ViewModel {
    private final MutableLiveData<ProfileData> data = new MutableLiveData<>();

    public LiveData<ProfileData> getProfileData() {
        return data;
    }

    public void setProfileData(ProfileData value) {
        data.setValue(value);
    }

    // Getter and setter methods for base temperature
    public void setBaseTemperature(String baseTemperatureStr) {
        if (baseTemperatureStr != null && !baseTemperatureStr.isEmpty()) {
            baseTemperatureStr = baseTemperatureStr.substring(0, baseTemperatureStr.length() - 1);
        }
        else {
            baseTemperatureStr = "-1";
        }
        data.getValue().setBaseTemperature(Integer.parseInt(baseTemperatureStr));
    }

    public int getBaseTemperature() {
        return data.getValue().getBaseTemperature();
    }

    public String getBaseTemperatureString() {
        return data.getValue().getBaseTemperature() + "c";
    }

    // Getter and setter methods for base amount of water
    public void setBaseAmountOfWater(int baseAmountOfWater) {
        data.getValue().setBaseAmountOfWater(baseAmountOfWater);
    }

    public int getBaseAmountOfWater() {
        return data.getValue().getBaseAmountOfWater();
    }

    // Getter and setter methods for bloom flag
    public void setBloomFlag(boolean bloomFlag) {
        data.getValue().setBloomFlag(bloomFlag);
    }

    public boolean isBloomFlag() {
        return data.getValue().isBloomFlag();
    }

    // Getter and setter methods for bloom water
    public void setBloomWater(int bloomWater) {
        data.getValue().setBloomWater(bloomWater);
    }

    public int getBloomWater() {
        return data.getValue().getBloomWater();
    }

    // Getter and setter methods for bloom speed
    public void setBloomSpeed(int bloomSpeed) {
        data.getValue().setBloomSpeed(bloomSpeed);
    }

    public int getBloomSpeed() {
        return data.getValue().getBloomSpeed();
    }

    // Getter and setter methods for bloom pause
    public void setBloomPause(int bloomPause) {
        data.getValue().setBloomPause(bloomPause);
    }

    public int getBloomPause() {
        return data.getValue().getBloomPause();
    }

    // Getter and setter methods for extraction1 water
    public void setExtraction1Water(int extraction1Water) {
        data.getValue().setExtraction1Water(extraction1Water);
    }

    public int getExtraction1Water() {
        return data.getValue().getExtraction1Water();
    }

    // Getter and setter methods for extraction1 speed
    public void setExtraction1Speed(int extraction1Speed) {
        data.getValue().setExtraction1Speed(extraction1Speed);
    }

    public int getExtraction1Speed() {
        return data.getValue().getExtraction1Speed();
    }

    // Getter and setter methods for extraction1 pause
    public void setExtraction1Pause(int extraction1Pause) {
        data.getValue().setExtraction1Pause(extraction1Pause);
    }

    public int getExtraction1Pause() {
        return data.getValue().getExtraction1Pause();
    }

    // Getter and setter methods for extraction2 flag
    public void setExtraction2Flag(boolean extraction2Flag) {
        data.getValue().setExtraction2Flag(extraction2Flag);
    }

    public boolean isExtraction2Flag() {
        return data.getValue().isExtraction2Flag();
    }

    // Getter and setter methods for extraction2 water
    public void setExtraction2Water(int extraction2Water) {
        data.getValue().setExtraction2Water(extraction2Water);
    }

    public int getExtraction2Water() {
        return data.getValue().getExtraction2Water();
    }

    // Getter and setter methods for extraction2 speed
    public void setExtraction2Speed(int extraction2Speed) {
        data.getValue().setExtraction2Speed(extraction2Speed);
    }

    public int getExtraction2Speed() {
        return data.getValue().getExtraction2Speed();
    }

    // Getter and setter methods for extraction2 pause
    public void setExtraction2Pause(int extraction2Pause) {
        data.getValue().setExtraction2Pause(extraction2Pause);
    }

    public int getExtraction2Pause() {
        return data.getValue().getExtraction2Pause();
    }

    // Getter and setter methods for extraction3 flag
    public void setExtraction3Flag(boolean extraction3Flag) {
        data.getValue().setExtraction3Flag(extraction3Flag);
    }

    public boolean isExtraction3Flag() {
        return data.getValue().isExtraction3Flag();
    }

    // Getter and setter methods for extraction3 water
    public void setExtraction3Water(int extraction3Water) {
        data.getValue().setExtraction3Water(extraction3Water);
    }

    public int getExtraction3Water() {
        return data.getValue().getExtraction3Water();
    }

    // Getter and setter methods for extraction3 speed
    public void setExtraction3Speed(int extraction3Speed) {
        data.getValue().setExtraction3Speed(extraction3Speed);
    }

    public int getExtraction3Speed() {
        return data.getValue().getExtraction3Speed();
    }

    // Getter and setter methods for extraction3 pause
    public void setExtraction3Pause(int extraction3Pause) {
        data.getValue().setExtraction3Pause(extraction3Pause);
    }

    public int getExtraction3Pause() {
        return data.getValue().getExtraction3Pause();
    }

    // Getter and setter methods for extraction4 flag
    public void setExtraction4Flag(boolean extraction4Flag) {
        data.getValue().setExtraction4Flag(extraction4Flag);
    }

    public boolean isExtraction4Flag() {
        return data.getValue().isExtraction4Flag();
    }

    // Getter and setter methods for extraction4 water
    public void setExtraction4Water(int extraction4Water) {
        data.getValue().setExtraction4Water(extraction4Water);
    }

    public int getExtraction4Water() {
        return data.getValue().getExtraction4Water();
    }

    // Getter and setter methods for extraction4 speed
    public void setExtraction4Speed(int extraction4Speed) {
        data.getValue().setExtraction4Speed(extraction4Speed);
    }

    public int getExtraction4Speed() {
        return data.getValue().getExtraction4Speed();
    }

    // Getter and setter methods for extraction4 pause
    public void setExtraction4Pause(int extraction4Pause) {
        data.getValue().setExtraction4Pause(extraction4Pause);
    }

    public int getExtraction4Pause() {
        return data.getValue().getExtraction4Pause();
    }

    // Getter and setter methods for extraction5 flag
    public void setExtraction5Flag(boolean extraction5Flag) {
        data.getValue().setExtraction5Flag(extraction5Flag);
    }

    public boolean isExtraction5Flag() {
        return data.getValue().isExtraction5Flag();
    }

    // Getter and setter methods for extraction5 water
    public void setExtraction5Water(int extraction5Water) {
        data.getValue().setExtraction5Water(extraction5Water);
    }

    public int getExtraction5Water() {
        return data.getValue().getExtraction5Water();
    }

    // Getter and setter methods for extraction5 speed
    public void setExtraction5Speed(int extraction5Speed) {
        data.getValue().setExtraction5Speed(extraction5Speed);
    }

    public int getExtraction5Speed() {
        return data.getValue().getExtraction5Speed();
    }

    // Getter and setter methods for extraction4 pause
    public void setExtraction5Pause(int extraction5Pause) {
        data.getValue().setExtraction5Pause(extraction5Pause);
    }

    public int getExtraction5Pause() {
        return data.getValue().getExtraction5Pause();
    }
}
