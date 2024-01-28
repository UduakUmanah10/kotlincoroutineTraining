package my.exercise;

import java.util.concurrent.Future;

public interface MyLegacyAPI {

    /**
     * Fetches data from remote API.
     *
     * @param callback to be called when data is fetched or error occurs.
     * @return Future, cancelling it will cancel the request
     */
    Future<Void> fetchData(Callback callback);

    @FunctionalInterface
    interface Callback {
        /**
         * Called when data is fetched or error occurs.
         * Either data or exception will be null.
         *
         * @param data      if not null, contains fetched data
         * @param exception if not null, contains exception that occurred
         */
        void call(String data, Throwable exception);
    }
}
