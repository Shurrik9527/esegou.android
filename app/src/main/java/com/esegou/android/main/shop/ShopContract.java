package com.esegou.android.main.shop;

import com.esegou.android.BasePresenter;
import com.esegou.android.BaseView;

/**
 * Created by Shurrik on 2017/11/24.
 */

public class ShopContract {

    public interface View extends BaseView<Presenter> {
        void hideRefreshing();
    }

    public interface Presenter extends BasePresenter {
        void getData();
        void search(String key);
    }
}
