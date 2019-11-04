# SwedishRadioPrograms

| List View  | Detail view |
|------------|-------------|
| ![image](https://drive.google.com/uc?export=view&id=1jxBWRFrzTccdU-vTztz4vQ1b_YkZTO8j) | ![image](https://drive.google.com/uc?export=view&id=1Obj_zp99muvu44ZqxGGrHmwDr-oXsRbH) |
 
Android app that lists Swedish Radio channel information and offers navigation.

This represents results of a programming assignment to retrieve JSON data from the
Swedish Radio Open API and present Radio Program information in the app.

Navigating the list of channels offers detail information about a program channel
by clicking on a list entry. This makes use of a 'programinfo' URL that is
loaded into a WebView widget that renders the actual Web page.
This allows playback of the channel audio stream as well as interacting with
other features on that site.  Pressing the back button returns the user to the channel list,
and turns any audio off. I made another version that let the audio continue to play
when returning to the list and kind of liked it to continue playing until navigating to
some other item to play.  But for now I close the detail page when returning to the list.

I also implemented the Stretch Goal of loading further parts of the list
for seamless navigation of all of the (currently) 744 program entries.
This proved much easier than I had assumed.
RecyclerView in basic usage takes care of view recycling.
I did not have to venture into detail paging management.
The major app memory usage comes from inflated images and list entries,
while the "raw data" from the SR Open API is text based.
So I went for loading ALL of the program entries
into the Android device and RecyclerView takes care of
managing views within a size of 40 entries.
I see no performance issues with this.
I added a list scroll bar to cope with the 744 programs.

One idea I left out was it would be worthwhile to have a search function into the
program information text.  It would help finding interesting radio programs quicker.
One could load the SR data into a database and do interesting searches.

I also would have spent time on accomplishing an animation feature in the list
so that when scrolling stops then as many rows as fit from program information text would fade in
below the headline (program name) of each list entry.

/gunnar  +46 760 315300
