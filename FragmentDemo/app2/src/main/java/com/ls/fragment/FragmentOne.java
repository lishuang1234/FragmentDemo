package com.ls.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ls.fragmentdemo2.R;


/**
 * Created by ls on 15-6-30.
 */
public class FragmentOne extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        view.findViewById(R.id.btn_one).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentTwo fragmentTwo = new FragmentTwo();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.id_content, fragmentTwo, "TWO");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        /**
         * 1.Fragment回退栈
         * 在点击FragmentOne中的按钮时，使用了replace方法，如果你看了前一篇博客，一定记得replace是remove和add的合体，
         * 并且如果不添加事务到回退栈，前一个Fragment实例会被销毁。这里很明显，我们调用tx.addToBackStack(null);
         * 将当前的事务添加到了回退栈，所以FragmentOne实例不会被销毁，但是视图层次依然会被销毁，
         * 即会调用onDestoryView和onCreateView
         * */
    }

    /**
     * 5.Fragment与ActionBar和MenuItem集成
     * Fragment可以添加自己的MenuItem到Activity的ActionBar或者可选菜单中。
     * a、在Fragment的onCreate中调用 setHasOptionsMenu(true);
     * b、然后在Fragment子类中实现onCreateOptionsMenu
     * c、如果希望在Fragment中处理MenuItem的点击，也可以实现onOptionsItemSelected；当然了Activity也可以直接处理该MenuItem的点击事件。
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment, menu);
    }
}
