package com.esegou.android.main.member;

import com.esegou.android.BasePresenter;
import com.esegou.android.BaseView;

/**
 * Created by Shurrik on 2017/11/30.
 */

public class MemberContract {

    public interface View extends BaseView<MemberContract.Presenter> {

    }

    public interface Presenter extends BasePresenter {

    }
}
