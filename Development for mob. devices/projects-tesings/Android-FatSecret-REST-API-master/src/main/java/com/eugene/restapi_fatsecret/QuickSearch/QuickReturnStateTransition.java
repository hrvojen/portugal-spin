package com.eugene.restapi_fatsecret.QuickSearch;

public interface QuickReturnStateTransition {
    public int determineState(final int rawY, int quickReturnHeight);
}
