package com.magic.magicprojectbaseapp;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.magic.magicprojectbaseapp.base.BaseActivity;
import com.magic.magicprojectbaseapp.fragment.TestFragment;
import com.magic.magicprojectbaseapp.mvp.presenter.BasePresenter;
import com.magic.magicprojectbaseapp.widget.NoScrollViewPager;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    List<Fragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();


    @Override
    protected BasePresenter initPresener() {
        return null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        outState.putString();
        Logger.e("onSaveInstanceState，是异常销毁后保存数据的地方");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            Logger.e("MainActivity！！onCreate() .savedInstanceState不为null，是异常销毁后的重建");
        }

        setContentView(R.layout.activity_main);
//        下面代码状态栏不会盖住最顶层btn1
        ImmersionBar.with(this).statusBarColor(R.color.colorAccent)
                .titleBar(R.id.btn1).init();
//        下面代码，状态栏会盖住最顶层view
//        ImmersionBar.with(this).statusBarColor(R.color.colorAccent)
//                .init();


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            Logger.e("MainActivity！！onCreate() .savedInstanceState不为null，是异常销毁后的重建");
        }
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    public void initViews() {
        initTab();

    }

    @Override
    public void initData() {

    }

    @Override
    protected void initBundleData() {

    }

    private void initTab() {
        initFragment();
//        表示相邻的预加载或保存的数量，点击第2个，limit为2，则45，78，都被创建。其他的都销毁。

        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
//                Logger.e("FragmentStatePagerAdapter getItem position:"+position);
                return fragments.get(position);

            }

            @Override
            public int getCount() {
//                Logger.e("FragmentStatePagerAdapter fragments.size():"+fragments.size());
                return fragments.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
//                Logger.e("FragmentStatePagerAdapter destroyItem position:"+position);


            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
//                Logger.e("FragmentStatePagerAdapter getPageTitle:"+titles.get(position));
                return titles.get(position);
            }
        });

        tabLayout.setupWithViewPager(viewPager);
    }

    private void initFragment() {
        fragments.add(TestFragment.newInstance("fragment1"));
        fragments.add(TestFragment.newInstance("fragment2"));
        fragments.add(TestFragment.newInstance("fragment3"));
        fragments.add(TestFragment.newInstance("fragment4"));
        fragments.add(TestFragment.newInstance("fragment5"));
        fragments.add(TestFragment.newInstance("fragment6"));
        fragments.add(TestFragment.newInstance("fragment7"));
        fragments.add(TestFragment.newInstance("fragment8"));
        titles.add("f1");
        titles.add("f2");
        titles.add("f3");
        titles.add("f4");
        titles.add("f5");
        titles.add("f6");
        titles.add("f7");
        titles.add("f8");
    }
}
