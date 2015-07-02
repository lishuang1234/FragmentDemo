package com.ls.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ls.fragmentdemo3.R;

/**
 * Created by ls on 15-6-30.
 */
public class ContentFragment extends Fragment {
    private String mArgument;
    public static final String ARGUMENT = "argument";
    public static final String RESPONSE = "response";

    public static final String EVALUATE_DIALOG = "evaluate_dialog";
    public static final int REQUEST_EVALUATE = 0X110;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mArgument = bundle.getString(ARGUMENT);
            Intent intent = new Intent();
            intent.putExtra(RESPONSE, "good");
            getActivity().setResult(ListTitleFragment.REQUEST_DETAIL, intent);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        TextView argument = (TextView) view.findViewById(R.id.fragment_tx_argument);
        argument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EvaluateDialog dialog = new EvaluateDialog();
                /**
                 * dialog.setTargetFragment(ContentFragment.this, REQUEST_EVALUATE);
                 * 我们调用了Fragment.setTargetFragment ，这个方法，一般就是用于当前fragment由别的fragment启动，在完成操作后返回数据的，符合我们的需求吧~~~注意，这句很重要。
                 * */
                dialog.setTargetFragment(ContentFragment.this, REQUEST_EVALUATE);
                dialog.show(getFragmentManager(), EVALUATE_DIALOG);
            }
        });
        argument.setText(mArgument);
        return view;
    }

    /**
     * 给Fragment添加newInstance方法，将需要的参数传入，设置到bundle中，然后setArguments(bundle)，最后在onCreate中进行获取；
     * 这样就完成了Fragment和Activity间的解耦。当然了这里需要注意：
     * setArguments方法必须在fragment创建以后，添加给Activity前完成。千万不要，首先调用了add，然后设置arguments。
     */
    public static ContentFragment newInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        ContentFragment contentFragment = new ContentFragment();
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    /**
     * 处理与DialogFragment交互
     * 接收返回回来的数据
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EVALUATE) {
            String eva = data.getStringExtra(EvaluateDialog.RESPONSE_EVALUATE);//获取返回数据
            Toast.makeText(getActivity(), eva, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra(RESPONSE, eva);
            getActivity().setResult(ListTitleFragment.REQUEST_DETAIL, intent);
        }

    }
}
