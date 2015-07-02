package com.ls.fragment;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by ls on 15-7-2.
 */
public class EvaluateDialog extends DialogFragment {

    private String[] mEvaluteVals = new String[]{"GOOD", "BAD", "NORMAL"};
    public static final String RESPONSE_EVALUATE = "response_evaluate";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("EvaluateDialog").setItems(mEvaluteVals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setResult(which);
            }
        });
        return builder.create();
    }

    /**
     * 重点就是看点击后的setResult了，我们首先判断是否设置了targetFragment，
     * 如果设置了，意味我们要返回一些数据到targetFragment。
     * 我们创建intent封装好需要传递数据，最后手动调用onActivityResult进行返回数据~~
     * 最后我们在ContentFragment的onActivityResult接收即可。
     */
    private void setResult(int which) {
        if (getTargetFragment() == null)
            return;
        Intent intent = new Intent();
        intent.putExtra(RESPONSE_EVALUATE, mEvaluteVals[which]);
        getTargetFragment().onActivityResult(ContentFragment.REQUEST_EVALUATE, Activity.RESULT_OK, intent);
    }

}
