package com.mobimation.swedishradio;

/**
 *  Content Item is a data model that holds channel list data of interest to the app
 *  in the format as retrieved from the Swedish Radio Open API JSON data.
 */
class ProgramItem {
    private String programName, imageURL, programURL;
    /*
    programName - string is shown in a TextView.
    imageURL    - used for asynchronously fetching an image
                  for display in an ImageView.
    programURL  - refers to a Web page for detail program info
                  that is shown in a WebView when the User selects a program list entry.
    */

    // Data get/set operations

    /**
     * Retrieve URL needed for obtaining program image.
     * @return imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Store URL to remote graphical image that represents the program.
     * @param imageURL URL string for fetching the image file
     */
    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    /**
     * Get URL to web page that presents Radio Program details and allows playback.
     * @return URL to HTML page for the radio program
     */
    public String getProgramUrl() {
        return programURL;
    }

    /**
     * Store the URL to HTML page for the program.
     * @param programURL URL to program web page
     */
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