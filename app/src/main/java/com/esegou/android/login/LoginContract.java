package com.esegou.android.login;

import com.esegou.android.BasePresenter;
import com.esegou.android.BaseView;

/**
 * Created by Shurrik on 2017/11/30.
 */

public class LoginContract {

    public interface View extends BaseView<LoginContract.Presenter> {

    }

    public interface Presenter extends BasePresenter {

    }
}
