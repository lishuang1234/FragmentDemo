package com.ls.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ls.fragmentdemo2.R;

/**
 * Created by ls on 15-6-30.
 */
public class FragmentTwo extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container,false);
        view.findViewById(R.id.btn_two).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentThree fragmentThree = new FragmentThree();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(this);
        fragmentTransaction.add(R.id.id_content, fragmentThree, "THREE");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        /**
         * 这里点击时，我们没有使用replace，而是先隐藏了当前的Fragment，然后添加了FragmentThree的实例，
         * 最后将事务添加到回退栈。这样做的目的是为了给大家提供一种方案：
         * 如果不希望视图重绘该怎么做，请再次仔细看效果图，我们在FragmentTwo的EditText填写的内容，用户Back回来时，数据还在
         * */
    }

}
