package com.coderpig.oppoaiunit.base;

import com.coderpig.oppoaiunit.http.API;
import com.coderpig.oppoaiunit.http.RetrofitService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * base 控制器
 * @param <V>
 */

public class BasePresenter<V extends BaseView> {

    //一种一次性容器 用完即可丢弃
    private CompositeDisposable compositeDisposable;
    public V baseView;

    protected API.WAZApi apiServer = RetrofitService.getInstance().getApiService();

    public BasePresenter(V baseView) {
        this.baseView = baseView;
    }

    /**
     * 解除绑定
     */
    public void detachView(){
        baseView = null;
        removeDisposable();
    }

    private void removeDisposable() {
        if (compositeDisposable != null){
            compositeDisposable.dispose();
        }
    }

    /**
     * 返回View
     * @return
     */
    public V getBaseView() {
        return baseView;
    }

    public void addDisposable(Observable<?> observable, BaseObserver observer){
        if (compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable
                .add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer));
    }

}
