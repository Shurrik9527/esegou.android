package com.esegou.android.test;

import com.esegou.android.BasePresenter;
import com.esegou.android.BaseView;

/**
 * Created by Shurrik on 2017/11/22.
 */

public class TestContract {

    interface View extends BaseView<Presenter>{
        void showMessage(String msg);
    }

    interface Presenter extends BasePresenter{
        void getMessage();
    }
}
