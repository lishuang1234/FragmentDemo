package com.ls.fragmentdemo;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ls.fragment.ContentFragment;
import com.ls.fragment.FriendFragment;


/**
 * a、比如：我在FragmentA中的EditText填了一些数据，当切换到FragmentB时，如果希望会到A还能看到数据，则适合你的就是hide和show；
 * 也就是说，希望保留用户操作的面板，你可以使用hide和show，当然了不要使劲在那new实例，进行下非null判断。
 * b、再比如：我不希望保留用户操作，你可以使用remove()，然后add()；
 * 或者使用replace()这个和remove,add是相同的效果。
 * c、remove和detach有一点细微的区别，在不考虑回退栈的情况下，remove会销毁整个Fragment实例，而detach则只是销毁其视图结构，实例并不会被销毁。
 * 那么二者怎么取舍使用呢？如果你的当前Activity一直存在，那么在不希望保留用户操作的时候，你可以优先使用detach。
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ContentFragment mWeixinFragment;
    private FriendFragment mFriendFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tab_bottom_friend).setOnClickListener(this);
        findViewById(R.id.tab_bottom_weixin).setOnClickListener(this);
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mWeixinFragment = new ContentFragment();
        fragmentTransaction.replace(R.id.id_content, mWeixinFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (v.getId()) {
            case R.id.tab_bottom_friend:
                if (mFriendFragment == null)
                    mFriendFragment = new FriendFragment();
                fragmentTransaction.replace(R.id.id_content, mFriendFragment);
                break;
            case R.id.tab_bottom_weixin:
                if (mWeixinFragment == null)
                    mWeixinFragment = new ContentFragment();
                fragmentTransaction.replace(R.id.id_content, mWeixinFragment);
                break;
        }
        fragmentTransaction.commit();
//        mFriendFragment.setReenterTransition();
    }
}
