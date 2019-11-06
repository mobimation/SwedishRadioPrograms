package com.mobimation.swedishradio;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

/**
 * ProgramDataSource represents the data source
 * for Swedish Radio Open API program information pages.
 * @see https://sverigesradio.se/api/documentation/v2/index.html
 *
 * This is part of paged retrieval of remote data
 * implementing 'true seamless scrolling' of large data sets.
 *
 * The Program Data Source class knows how to retrieve initial, previous and next page
 * of information about Swedish Radio programs from the Swedish Radio Open API.
 *
 * Use of the Paging API ( @see https://developer.android.com/topic/libraries/architecture/paging )
 * removes complex developer challenges of managing a scroll listener and
 * figuring out at what scroll positions to initiate a pre-fetch of next or previous data.
 * This is now managed by RecyclerView which given some initial constraints will call the
 * loadInitial/LoadBefore/loadNext methods in order to provide itself with data ahead of time.
 *
 * The loadInitial method will request a page that represents a fixed size array
 * of program information items intended for an initial list navigation.
 * This amount request determines the page size for a subsequent request of pages.
 * Upon arrival the JSON structure contains information
 * about the total data set size and, depending on the requested amount,
 * optionally convenience URLs that simplify retrieval of next or previous page.
 *
 * If no previous page URL exists it means we are already on the first page.
 * If no next page URL exists it means we are already on the last page.
 * Subsequent use of loadBefore / loadAfter will have the Open API
 * return new next and previous URLs relevent to
 * the most recent retrieval.
 **/
 public class ProgramDataSource extends PageKeyedDataSource {
    /**
     * Load the initial data set to be used. The retrieved initial data set will contain
     * an URL that support loadAfter retrieval. The size of initial data loaded represents
     * the size of a page loaded in loadBefore/loadAfter calls.
     * @param params   The parameters provided by RecyclerView
     * @param callback Callback to handle the asynchronous response
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {

    }
    /**
     * Load the page before the current one.
     * In case the most recent retrieval returned an URL for previous page,
     * then that URL is used here for an asynchronous fetch of the previous page of program data,
     * else if no such URL it means we were on the first page. If so do nothing.
     * @param params   The parameters provided by RecyclerView
     * @param callback Callback to handle the asynchronous response
     */
    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }
    /**
     * Load the page after the current one.
     * In case the most recent retrieval contains an URL for the next page of program data,
     * then that URL is used here for an asynchronous fetch of this page,
     * else if no such URL then we were on the last page. If so do nothing.
     * @param params   The parameters provided by RecyclerView
     * @param callback Callback to handle the asynchronous response
     */
    @Override
    public void loadAfter(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }
 }