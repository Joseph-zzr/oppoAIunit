package com.coderpig.oppoaiunit.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coderpig.oppoaiunit.R;
import com.yechaoa.yutils.ActivityUtil;
import com.yechaoa.yutils.YUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基类
 * 持有P层 布局的加载 写好了基本逻辑 为子类的二次开发奠定基础
 * @param <P>
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    /**
     * unbinder契约，在调用时解除视图的绑定
     */
    private Unbinder unbinder;

    protected Context mContext;

    protected P presenter;

    protected abstract P createPresenter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        unbinder = ButterKnife.bind(this,view);
        //获得栈顶 的activity
        mContext = ActivityUtil.getCurrentActivity();
        presenter = createPresenter();
        initView();
        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initListener();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (presenter != null){
            presenter.detachView();
        }
    }

    @Override
    public void showLoading() {
        YUtils.showLoading(ActivityUtil.getCurrentActivity(), getResources().getString(R.string.loading));
    }

    @Override
    public void hideLoading() {
        YUtils.dismissLoading();
    }

    @Override
    public void onErrorCode(BaseBean bean) {

    }

    public abstract int getLayoutId();
    public abstract void initView();
    public abstract void initData();
    public abstract void initListener();
}
