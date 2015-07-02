package com.ls.fragmentdemo3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ls.fragment.ListTitleFragment;

/**
 * Created by ls on 15-7-2.
 */
public class ListTitleActivity extends SingleFragmentActivity {
    /**
     * 我们点击列表Fragment中的列表项，传入相应的参数，去详细信息的Fragment展示详细的信息，在详细信息页面，
     * 用户可以进行点评，当用户点击back以后，我们以往点评结果显示在列表的Fragment对于的列表项中；
     * 也就是说，我们点击跳转到对应Activity的Fragment中，并且希望它能够返回参数，
     * 那么我们肯定是使用Fragment.startActivityForResult ;
     * 在Fragment中存在startActivityForResult（）以及onActivityResult（）方法，但是呢，没有setResult（）方法，用于设置返回的intent，
     * 这样我们就需要通过调用getActivity().setResult(ListTitleFragment.REQUEST_DETAIL, intent);。
     */


    @Override
    protected Fragment createFragment() {
        return new ListTitleFragment();
    }

}
