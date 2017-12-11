package com.esegou.android.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.esegou.android.R;
import com.esegou.android.main.cart.CartFragment;
import com.esegou.android.main.cart.CartPresenter;
import com.esegou.android.main.classify.ClassifyFragment;
import com.esegou.android.main.classify.ClassifyPresenter;
import com.esegou.android.main.member.MemberFragment;
import com.esegou.android.main.member.MemberPresenter;
import com.esegou.android.main.shop.ShopFragment;
import com.esegou.android.main.shop.ShopPresenter;
import com.esegou.android.widget.SwitchSlidingViewPager;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private SwitchSlidingViewPager vpContent;
    private FloatingActionButton fabIm;
    private RadioGroup rgButtomMenu;
    private RadioButton rbShop;
    private RadioButton rbClassify;
    private RadioButton rbCart;
    private RadioButton rbUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set up the statusbar.
        mDrawerLayout = findViewById(R.id.drawer_layout);
        int primary = getResources().getColor(R.color.colorPrimary);
        StatusBarUtil.setColorForDrawerLayout(this, mDrawerLayout, primary);
        // Set up the toolbar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        // Set up the navigation drawer.
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        // Set up the content
        vpContent = findViewById(R.id.vp_content);
        vpContent.setSmoothScroll(false);
        vpContent.setCanScroll(false);
        vpContent.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), getFragmentList()));

        // Set up the FloatingActionButton
        fabIm = findViewById(R.id.fab_im);
        fabIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(0);
            }
        });

        // Set up the bottom menu.

        BottomNavigationBar mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
//        mBottomNavigationBar
//                .setActiveColor("#ff0000") //设置选中的颜色
//                .setInActiveColor("#FFFFFF")
//                .setBarBackgroundColor("#000000");

        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_shop_light, "商城"))
                .addItem(new BottomNavigationItem(R.drawable.ic_classify_light, "分类"))
                .addItem(new BottomNavigationItem(R.drawable.ic_cart_light, "购物车"))
                .addItem(new BottomNavigationItem(R.drawable.ic_user_light, "会员"))
                .initialise();


        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                vpContent.setCurrentItem(position);
                if (position == 2) {
                    fabIm.hide();
                } else {
                    fabIm.show();
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

       /*  rgButtomMenu = findViewById(R.id.rg_bottom_menu);
        rbShop = findViewById(R.id.rb_shop);
        rbClassify = findViewById(R.id.rb_classify);
        rbCart = findViewById(R.id.rb_cart);
        rbUser = findViewById(R.id.rb_user);
        rgButtomMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rb_shop:
                        vpContent.setCurrentItem(0);
                        fabIm.show();
                        break;
                    case R.id.rb_classify:
                        vpContent.setCurrentItem(1);
                        fabIm.show();
                        break;
                    case R.id.rb_cart:
                        vpContent.setCurrentItem(2);
                        fabIm.hide();
                        break;
                    case R.id.rb_user:
                        vpContent.setCurrentItem(3);
                        fabIm.show();
                        break;
                }
            }
        });
        configRadioButton();
        //默认选中首页
        rgButtomMenu.check(R.id.rb_shop);*/
    }

    private List<Fragment> getFragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        ShopFragment shopFragment = new ShopFragment();
        fragmentList.add(shopFragment);
        new ShopPresenter(shopFragment);
        ClassifyFragment classifyFragment = new ClassifyFragment();
        fragmentList.add(classifyFragment);
        new ClassifyPresenter(classifyFragment);
        CartFragment cartFragment = new CartFragment();
        fragmentList.add(cartFragment);
        new CartPresenter(cartFragment);
        MemberFragment memberFragment = new MemberFragment();
        fragmentList.add(memberFragment);
        new MemberPresenter(memberFragment);
        return fragmentList;
    }

    private void configRadioButton() {
        setRadioButtonStyle(rbShop, R.drawable.ic_shop_light, R.drawable.ic_shop_dark);
        setRadioButtonStyle(rbClassify, R.drawable.ic_classify_light, R.drawable.ic_classify_dark);
        setRadioButtonStyle(rbCart, R.drawable.ic_cart_light, R.drawable.ic_cart_dark);
        setRadioButtonStyle(rbUser, R.drawable.ic_user_light, R.drawable.ic_user_dark);
    }

    public void setRadioButtonStyle(final RadioButton button, final int selectDrawable, final int unSelectDrawable) {
        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    VectorDrawableCompat vectorDrawableCompat = VectorDrawableCompat.create(getResources(), selectDrawable, getTheme());
                    //你需要改变的颜色
                    int accent = getResources().getColor(R.color.colorAccent);
                    vectorDrawableCompat.setTint(accent);
                    compoundButton.setTextColor(accent);
                    compoundButton.setCompoundDrawablesWithIntrinsicBounds(null,
                            vectorDrawableCompat,
                            null, null);
                } else {
                    VectorDrawableCompat vectorDrawableCompat = VectorDrawableCompat.create(getResources(), unSelectDrawable, getTheme());
                    //你需要改变的颜色
                    int primary = getResources().getColor(R.color.colorPrimary);
                    vectorDrawableCompat.setTint(primary);
                    compoundButton.setTextColor(primary);
                    compoundButton.setCompoundDrawablesWithIntrinsicBounds(null,
                            vectorDrawableCompat,
                            null, null);
                }
            }
        });
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {

                        }
                        // Close the navigation drawer when an item is selected.
                        //menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class FragmentPagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> fragmentList;

        public FragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList) {
            super(fragmentManager);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
