package com.mobimation.swedishradio;

// Data Model that holds channel list data of interest to the app
// as retrieved from the Swedish Radio Open API JSON data.

class ContentItem {
    private String programName, imageURL, programURL;
    /*
    programName - string is shown in a TextView.
    imageURL    - used for asynchronously fetching an image
                  for display in an ImageView.
    programURL  - refers to a Web page for detail program info
                  that is shown in a WebView when the User selects a program list entry.
    */

    // Data get/set operations

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }


    public String getProgramUrl() {
        return programURL;
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