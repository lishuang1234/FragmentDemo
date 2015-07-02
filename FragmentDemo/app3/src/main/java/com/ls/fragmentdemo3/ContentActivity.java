package com.ls.fragmentdemo3;

import android.support.v4.app.Fragment;

import com.ls.fragment.ContentFragment;

/**
 * Created by ls on 15-7-2.
 */
public class ContentActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return ContentFragment.newInstance(getIntent().getStringExtra(ContentFragment.ARGUMENT));
    }
}
