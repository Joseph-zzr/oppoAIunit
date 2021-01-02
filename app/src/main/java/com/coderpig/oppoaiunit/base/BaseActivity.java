package com.coderpig.oppoaiunit.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.yechaoa.yutils.YUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

/**
 * Acivity基类
 *布局文件的加载
 * presenter的实例创建
 * @param <P>
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected P presenter;
    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        setContentView(LayoutInflater.from(this).inflate(getLayoutId(),null));

        ButterKnife.bind(this);
        
        presenter = createPresenter();
        
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁时，解除绑定
        if (presenter != null){
            presenter.detachView();
        }
    }

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    public void showLoading() {
        YUtils.showLoading(this,"加载中");
    }

    @Override
    public void hideLoading() {
        YUtils.dismissLoading();
    }


    /**
     * 可以处理异常
     * @param bean
     */
    @Override
    public void onErrorCode(BaseBean bean) {

    }
}
