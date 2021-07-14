package com.image.search.domain.param;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
public class SearchImageParams {

    private final String query;
    private final int page_number;
    private final int page_size;
    private final boolean auto_correct;
    private final boolean safe_search;

    private final boolean isInternetAvailable;

    public SearchImageParams(SearchImageParamsBuilder builder) {
        this.query = builder.query;
        this.page_number = builder.page_number;
        this.page_size = builder.page_size;
        this.auto_correct = builder.auto_correct;
        this.safe_search = builder.safe_search;
        this.isInternetAvailable = builder.isInternetAvailable;
    }

    public String getQuery() {
        return query;
    }

    public int getPage_number() {
        return page_number;
    }

    public int getPage_size() {
        return page_size;
    }

    public boolean isAuto_correct() {
        return auto_correct;
    }

    public boolean isSafe_search() {
        return safe_search;
    }

    public boolean isInternetAvailable() {
        return isInternetAvailable;
    }

    public static class SearchImageParamsBuilder {

        private final boolean isInternetAvailable;

        private String query;
        private int page_number;
        private int page_size;
        private boolean auto_correct;
        private boolean safe_search;

        public SearchImageParamsBuilder(final boolean isInternetAvailable) {
            this.isInternetAvailable = isInternetAvailable;
        }

        public SearchImageParamsBuilder setQuery(String query) {
            this.query = query;
            return this;
        }

        public SearchImageParamsBuilder setPage_number(int page_number) {
            this.page_number = page_number;
            return this;
        }

        public SearchImageParamsBuilder setPage_size(int page_size) {
            this.page_size = page_size;
            return this;
        }

        public SearchImageParamsBuilder setAuto_correct(boolean auto_correct) {
            this.auto_correct = auto_correct;
            return this;
        }

        public SearchImageParamsBuilder setSafe_search(boolean safe_search) {
            this.safe_search = safe_search;
            return this;
        }

        public SearchImageParams build() {
            return new SearchImageParams(this);
        }
    }

}
