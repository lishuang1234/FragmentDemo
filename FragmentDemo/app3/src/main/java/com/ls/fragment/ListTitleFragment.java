package com.ls.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ls.fragmentdemo3.ContentActivity;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ls on 15-7-2.
 */
public class ListTitleFragment extends ListFragment {

    public static final int REQUEST_DETAIL = 0x11;
    private int mPosition;
    private List<String> mTitles = Arrays.asList("Hello", "World", "Android");
    private ArrayAdapter<String> mAdapter;

    /**
     * 可以看到我们在ListTitleFragment.onListItemClick，使用startActivityForResult（）跳转到目标Activity，
     * 在目标Activity的Fragment（ContentFragment）中获取参数，
     * 然后调用getActivity().setResult(ListTitleFragment.REQUEST_DETAIL, intent);进行设置返回的数据；
     * 最后在ListTitleFragment.onActivityResult（）拿到返回的数据进行回显；
     * 为大家以后在遇到类似问题时，提供了解决方案；
     * 也说明了一个问题：fragment能够从Activity中接收返回结果，但是其自设无法产生返回结果，只有Activity拥有返回结果。
     */


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mTitles));
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mPosition = position;
        Intent intent = new Intent(getActivity(), ContentActivity.class);
        intent.putExtra(ContentFragment.ARGUMENT, mTitles.get(position));
        startActivityForResult(intent, REQUEST_DETAIL);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_DETAIL) {
            mTitles.set(mPosition, mTitles.get(mPosition) + "--" + data.getStringExtra(ContentFragment.RESPONSE));
            mAdapter.notifyDataSetChanged();
        }
    }
}
