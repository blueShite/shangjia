package com.huishengyuan.storemanage.CustomView;

import android.app.Dialog;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.huishengyuan.storemanage.R;

/**
 * Created by longhengyu on 2017/8/8.
 */

public class AddFootAlert {

    private Context context;
    private Dialog dialog;
    private ConstraintLayout CLayout_bg;
    private Button cancelBtn;
    private Button submitBtn;
    private Display display;
    private EditText editText;

    public AddFootAlert(Context context){

        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();

    }

    public AddFootAlert builder(){
        View view = LayoutInflater.from(context).inflate(R.layout.addfoot_alert,null);
        CLayout_bg = (ConstraintLayout) view.findViewById(R.id.layout_add_foot);
        cancelBtn = (Button) view.findViewById(R.id.button_add_foot_cancel);
        submitBtn = (Button) view.findViewById(R.id.button_add_foot_submit);
        editText = (EditText) view.findViewById(R.id.edit_add_foot);
        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AddFoot_DialogStyle);
        dialog.setContentView(view);
        // 调整dialog背景大小
        CLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.87), ConstraintLayout.LayoutParams.WRAP_CONTENT));
        return this;

    }

    public AddFootAlert onClickSubmit(final OnClickAddFootInterface addFootInterface){

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFootInterface.addFoot(editText.getText().toString());
                dialog.dismiss();
            }
        });

        return this;
    }

    public AddFootAlert onClickCancel(final View.OnClickListener listener){
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
                dialog.dismiss();
            }
        });
        return this;
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public interface OnClickAddFootInterface{
        void addFoot(String numStr);
    }


}
