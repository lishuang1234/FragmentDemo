package com.ls.fragmentdemo2;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ls.fragment.FragmentOne;


public class MainActivity extends AppCompatActivity {

    /**
     * 2.Fragment与Activity通信
     * 因为所有的Fragment都是依附于Activity的，所以通信起来并不复杂，大概归纳为：
     * a、如果你Activity中包含自己管理的Fragment的引用，可以通过引用直接访问所有的Fragment的public方法
     * b、如果Activity中未保存任何Fragment的引用，那么没关系，每个Fragment都有一个唯一的TAG或者ID,可以通过getFragmentManager.findFragmentByTag()或者findFragmentById()获得任何Fragment实例，然后进行操作。
     * c、在Fragment中可以通过getActivity得到当前绑定的Activity的实例，然后进行操作。
     * 注：如果在Fragment中需要Context，可以通过调用getActivity(),如果该Context需要在Activity被销毁后还存在，则使用getActivity().getApplicationContext()。
     * 虽然Fragment和Activity可以通过getActivity与findFragmentByTag或者findFragmentById，进行任何操作，
     * 甚至在Fragment里面操作另外的Fragment，但是没有特殊理由是绝对不提倡的。Activity担任的是Fragment间类似总线一样的角色，
     * 应当由它决定Fragment如何操作。另外虽然Fragment不能响应Intent打开，但是Activity可以，Activity可以接收Intent，
     * 然后根据参数判断显示哪个Fragment。
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 3.处理运行时配置变化：
         * 很多人觉得强制设置屏幕的方向就可以了，但是有一点，当你的应用被至于后台（例如用户点击了home），
         * 长时间没有返回的时候，你的应用也会被重新启动。比如上例：如果你把上面的例子你至于FragmentThree界面，
         * 然后处于后台状态，长时间后你会发现当你再次通过home打开时，上面FragmentThree与FragmentOne叠加在一起，
         * 这就是因为你的Activity重新启动，在原来的FragmentThree上又绘制了一个FragmentOne。
         * 解决方案：
         * 其实通过检查onCreate的参数Bundle savedInstanceState就可以判断，当前是否发生Activity的重新创建：
         * 默认的savedInstanceState会存储一些数据，包括Fragment的实例
         * */

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.id_content, new FragmentOne(), "ONE");
            fragmentTransaction.commit();
        }
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
