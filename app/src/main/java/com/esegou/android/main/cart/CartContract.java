package com.esegou.android.main.cart;

import com.esegou.android.BasePresenter;
import com.esegou.android.BaseView;

/**
 * Created by Shurrik on 2017/11/30.
 */

public class CartContract {

    public interface View extends BaseView<CartContract.Presenter> {

    }

    public interface Presenter extends BasePresenter {

    }
}
