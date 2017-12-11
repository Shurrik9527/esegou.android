package com.esegou.android.goods.list;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Shurrik on 2017/11/22.
 */

public class GoodsListPresenter implements GoodsListContract.Presenter {
    private GoodsListContract.View view;
    private CompositeDisposable mCompositeDisposable;

    public GoodsListPresenter(GoodsListContract.View view) {
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

}
