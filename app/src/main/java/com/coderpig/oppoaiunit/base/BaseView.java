package com.coderpig.oppoaiunit.base;

/**
 * base view
 */

public interface BaseView {
    void showLoading();

    void hideLoading();

    void onErrorCode(BaseBean bean);
}
