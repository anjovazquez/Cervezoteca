package com.cervezoteca.anjov.presentation.view;

import android.content.Context;

/**
 * Created by anjov on 07/10/2016.
 */

public interface LoadDataView {

    void showLoading();

    void hideLoading();

    void showRetry();

    void hideRetry();

    void showError(String message);

    Context getContext();
}
