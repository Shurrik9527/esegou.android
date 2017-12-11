package com.esegou.android.main.classify;

import android.util.Log;

import com.esegou.android.HttpCenter;
import com.esegou.android.HttpResult;
import com.esegou.android.main.shop.Goods;
import com.esegou.android.main.shop.ShopContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Shurrik on 2017/11/22.
 */

public class ClassifyPresenter implements ClassifyContract.Presenter {
    private ClassifyContract.View view;
    private CompositeDisposable mCompositeDisposable;

    public ClassifyPresenter(ClassifyContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }


    @Override
    public void getClassfy() {

    }
}
