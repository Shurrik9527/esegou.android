package com.esegou.android.main.classify;

import com.esegou.android.BasePresenter;
import com.esegou.android.BaseView;

/**
 * Created by Shurrik on 2017/11/30.
 */

public class ClassifyContract {

    public interface View extends BaseView<ClassifyContract.Presenter> {
        void openClassfiyList();
    }

    public interface Presenter extends BasePresenter {
        void getClassfy();
    }
}
