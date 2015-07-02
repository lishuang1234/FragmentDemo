package com.ls.fragmentdemo3;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ls.fragment.ContentFragment;


public class MainActivity extends AppCompatActivity {
    private ContentFragment mContentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        mContentFragment = (ContentFragment) manager.findFragmentById(R.id.id_fragment_container);
        if (mContentFragment == null) {
            mContentFragment = ContentFragment.newInstance("LS");
            transaction.add(R.id.id_fragment_container, mContentFragment);
            transaction.commit();
        }

        /**
         * 1、为什么需要判null呢？
         主要是因为，当Activity因为配置发生改变（屏幕旋转）或者内存不足被系统杀死，造成重新创建时，
         我们的fragment会被保存下来，但是会创建新的FragmentManager，
         新的FragmentManager会首先会去获取保存下来的fragment队列，
         重建fragment队列，从而恢复之前的状态。
         2、add(R.id.id_fragment_container,mContentFragment)中的布局的id有何作用？
         一方面呢，是告知FragmentManager，此fragment的位置；另一方面是此fragment的唯一标识；
         就像我们上面通过fm.findFragmentById(R.id.id_fragment_container)查找~~
         *
         * */
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
}
