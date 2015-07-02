package com.ls.fragmentdemo3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ls on 15-7-2.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    /**
     * 相信优先使用Fragment的项目，类似的Activity非常多，使用SingleFragmentActivity来简化你的代码吧~~
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentById(R.id.id_single_fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            fragmentTransaction.add(R.id.id_single_fragment_container, fragment).commit();
        }
    }

    protected abstract Fragment createFragment();
}
