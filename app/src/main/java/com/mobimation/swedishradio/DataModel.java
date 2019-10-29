package com.mobimation.swedishradio;

// Data Model that holds channel list data of interest to the app
// as retrieved from the Swedish Radio Open API JSON data.

class DataModel {
    private String programName, imageURL, programURL; // The data of interest

    public String getImageURL() {
        return imageURL;
    }

    public String getProgramUrl() {
        return programURL;
    }

    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    public void setProgramURL(String programURL){
        this.programURL = programURL;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String name) {
        this.programName = name;
    }
}