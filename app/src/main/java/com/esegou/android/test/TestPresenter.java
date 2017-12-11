package com.esegou.android.test;

import com.esegou.android.HttpCenter;
import com.esegou.android.HttpResult;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Shurrik on 2017/11/22.
 */

public class TestPresenter implements TestContract.Presenter {
    private TestContract.View taskDetailView;
    private CompositeDisposable mCompositeDisposable;

    public TestPresenter(TestContract.View taskDetailView) {
        this.taskDetailView = taskDetailView;
        this.taskDetailView.setPresenter(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getMessage() {
        mCompositeDisposable.add(
                HttpCenter.getService()
                        .test("Shurrik")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<HttpResult<Data>>() {
                            @Override
                            public void accept(HttpResult<Data> result) throws Exception {
                                //Log.d("TestActivity", result.message);
                                taskDetailView.showMessage(result.getMessage());
                            }
                        }));
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }
}
