package com.imrezwan.wise_brewer.models;

public class ProfileData {
    private int baseTemperature;
    private int baseAmountOfWater;

    private boolean bloomFlag;
    private int bloomWater;
    private int bloomSpeed;
    private int bloomPause;

    private int extraction1Water;
    private int extraction1Speed;
    private int extraction1Pause;

    private boolean extraction2Flag;
    private int extraction2Water;
    private int extraction2Speed;
    private int extraction2Pause;

    private boolean extraction3Flag;
    private int extraction3Water;
    private int extraction3Speed;
    private int extraction3Pause;

    private boolean extraction4Flag;
    private int extraction4Water;
    private int extraction4Speed;
    private int extraction4Pause;

    private boolean extraction5Flag;
    private int extraction5Water;
    private int extraction5Speed;
    private int extraction5Pause;

    public ProfileData() {
    }

    public ProfileData(int baseTemperature, int baseAmountOfWater, boolean bloomFlag, int bloomWater, int bloomSpeed, int bloomPause, int extraction1Water, int extraction1Speed, int extraction1Pause, boolean extraction2Flag, int extraction2Water, int extraction2Speed, int extraction2Pause, boolean extraction3Flag, int extraction3Water, int extraction3Speed, int extraction3Pause, boolean extraction4Flag, int extraction4Water, int extraction4Speed, int extraction4Pause, boolean extraction5Flag, int extraction5Water, int extraction5Speed, int extraction5Pause) {
        this.baseTemperature = baseTemperature;
        this.baseAmountOfWater = baseAmountOfWater;
        this.bloomFlag = bloomFlag;
        this.bloomWater = bloomWater;
        this.bloomSpeed = bloomSpeed;
        this.bloomPause = bloomPause;
        this.extraction1Water = extraction1Water;
        this.extraction1Speed = extraction1Speed;
        this.extraction1Pause = extraction1Pause;
        this.extraction2Flag = extraction2Flag;
        this.extraction2Water = extraction2Water;
        this.extraction2Speed = extraction2Speed;
        this.extraction2Pause = extraction2Pause;
        this.extraction3Flag = extraction3Flag;
        this.extraction3Water = extraction3Water;
        this.extraction3Speed = extraction3Speed;
        this.extraction3Pause = extraction3Pause;
        this.extraction4Flag = extraction4Flag;
        this.extraction4Water = extraction4Water;
        this.extraction4Speed = extraction4Speed;
        this.extraction4Pause = extraction4Pause;
        this.extraction5Flag = extraction5Flag;
        this.extraction5Water = extraction5Water;
        this.extraction5Speed = extraction5Speed;
        this.extraction5Pause = extraction5Pause;
    }

    public int getBaseTemperature() {
        return baseTemperature;
    }

    public void setBaseTemperature(int baseTemperature) {
        this.baseTemperature = baseTemperature;
    }

    public int getBaseAmountOfWater() {
        return baseAmountOfWater;
    }

    public void setBaseAmountOfWater(int baseAmountOfWater) {
        this.baseAmountOfWater = baseAmountOfWater;
    }

    public boolean isBloomFlag() {
        return bloomFlag;
    }

    public void setBloomFlag(boolean bloomFlag) {
        this.bloomFlag = bloomFlag;
    }

    public int getBloomWater() {
        return bloomWater;
    }

    public void setBloomWater(int bloomWater) {
        this.bloomWater = bloomWater;
    }

    public int getBloomSpeed() {
        return bloomSpeed;
    }

    public void setBloomSpeed(int bloomSpeed) {
        this.bloomSpeed = bloomSpeed;
    }

    public int getBloomPause() {
        return bloomPause;
    }

    public void setBloomPause(int bloomPause) {
        this.bloomPause = bloomPause;
    }

    public int getExtraction1Water() {
        return extraction1Water;
    }

    public void setExtraction1Water(int extraction1Water) {
        this.extraction1Water = extraction1Water;
    }

    public int getExtraction1Speed() {
        return extraction1Speed;
    }

    public void setExtraction1Speed(int extraction1Speed) {
        this.extraction1Speed = extraction1Speed;
    }

    public int getExtraction1Pause() {
        return extraction1Pause;
    }

    public void setExtraction1Pause(int extraction1Pause) {
        this.extraction1Pause = extraction1Pause;
    }

    public boolean isExtraction2Flag() {
        return extraction2Flag;
    }

    public void setExtraction2Flag(boolean extraction2Flag) {
        this.extraction2Flag = extraction2Flag;
    }

    public int getExtraction2Water() {
        return extraction2Water;
    }

    public void setExtraction2Water(int extraction2Water) {
        this.extraction2Water = extraction2Water;
    }

    public int getExtraction2Speed() {
        return extraction2Speed;
    }

    public void setExtraction2Speed(int extraction2Speed) {
        this.extraction2Speed = extraction2Speed;
    }

    public int getExtraction2Pause() {
        return extraction2Pause;
    }

    public void setExtraction2Pause(int extraction2Pause) {
        this.extraction2Pause = extraction2Pause;
    }

    public boolean isExtraction3Flag() {
        return extraction3Flag;
    }

    public void setExtraction3Flag(boolean extraction3Flag) {
        this.extraction3Flag = extraction3Flag;
    }

    public int getExtraction3Water() {
        return extraction3Water;
    }

    public void setExtraction3Water(int extraction3Water) {
        this.extraction3Water = extraction3Water;
    }

    public int getExtraction3Speed() {
        return extraction3Speed;
    }

    public void setExtraction3Speed(int extraction3Speed) {
        this.extraction3Speed = extraction3Speed;
    }

    public int getExtraction3Pause() {
        return extraction3Pause;
    }

    public void setExtraction3Pause(int extraction3Pause) {
        this.extraction3Pause = extraction3Pause;
    }

    public boolean isExtraction4Flag() {
        return extraction4Flag;
    }

    public void setExtraction4Flag(boolean extraction4Flag) {
        this.extraction4Flag = extraction4Flag;
    }

    public int getExtraction4Water() {
        return extraction4Water;
    }

    public void setExtraction4Water(int extraction4Water) {
        this.extraction4Water = extraction4Water;
    }

    public int getExtraction4Speed() {
        return extraction4Speed;
    }

    public void setExtraction4Speed(int extraction4Speed) {
        this.extraction4Speed = extraction4Speed;
    }

    public int getExtraction4Pause() {
        return extraction4Pause;
    }

    public void setExtraction4Pause(int extraction4Pause) {
        this.extraction4Pause = extraction4Pause;
    }

    public boolean isExtraction5Flag() {
        return extraction5Flag;
    }

    public void setExtraction5Flag(boolean extraction5Flag) {
        this.extraction5Flag = extraction5Flag;
    }

    public int getExtraction5Water() {
        return extraction5Water;
    }

    public void setExtraction5Water(int extraction5Water) {
        this.extraction5Water = extraction5Water;
    }

    public int getExtraction5Speed() {
        return extraction5Speed;
    }

    public void setExtraction5Speed(int extraction5Speed) {
        this.extraction5Speed = extraction5Speed;
    }

    public int getExtraction5Pause() {
        return extraction5Pause;
    }

    public void setExtraction5Pause(int extraction5Pause) {
        this.extraction5Pause = extraction5Pause;
    }

    public String getProfileDataForBluetooth() {
        return
            baseTemperature + " " +
            baseAmountOfWater + " " +
            bloomFlag + " " +
            bloomWater + " " +
            bloomSpeed + " " +
            bloomPause + " " +
            extraction1Water + " " +
            extraction1Speed + " " +
            extraction1Pause + " " +
            extraction2Flag + " " +
            extraction2Water + " " +
            extraction2Speed + " " +
            extraction2Pause + " " +
            extraction3Flag + " " +
            extraction3Water + " " +
            extraction3Speed + " " +
            extraction3Pause + " " +
            extraction4Flag + " " +
            extraction4Water + " " +
            extraction4Speed + " " +
            extraction4Pause + " " +
            extraction5Flag + " " +
            extraction5Water + " " +
            extraction5Speed + " " +
            extraction5Pause;
    }

    @Override
    public String toString() {
        return "ProfileData{" +
                "baseTemperature=" + baseTemperature +
                ", baseAmountOfWater=" + baseAmountOfWater +
                ", bloomFlag=" + bloomFlag +
                ", bloomWater=" + bloomWater +
                ", bloomSpeed=" + bloomSpeed +
                ", bloomPause=" + bloomPause +
                ", extraction1Water=" + extraction1Water +
                ", extraction1Speed=" + extraction1Speed +
                ", extraction1Pause=" + extraction1Pause +
                ", extraction2Flag=" + extraction2Flag +
                ", extraction2Water=" + extraction2Water +
                ", extraction2Speed=" + extraction2Speed +
                ", extraction2Pause=" + extraction2Pause +
                ", extraction3Flag=" + extraction3Flag +
                ", extraction3Water=" + extraction3Water +
                ", extraction3Speed=" + extraction3Speed +
                ", extraction3Pause=" + extraction3Pause +
                ", extraction4Flag=" + extraction4Flag +
                ", extraction4Water=" + extraction4Water +
                ", extraction4Speed=" + extraction4Speed +
                ", extraction4Pause=" + extraction4Pause +
                ", extraction5Flag=" + extraction5Flag +
                ", extraction5Water=" + extraction5Water +
                ", extraction5Speed=" + extraction5Speed +
                ", extraction5Pause=" + extraction5Pause +
                '}';
    }
}
