package cn.neday.graduates.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

import cn.neday.graduates.R;
import cn.neday.graduates.view.effects.BaseEffects;

public class NiftyDialogBuilder extends Dialog implements DialogInterface {

    @SuppressLint("StaticFieldLeak")
    public static EditText et_player;
    private static int mOrientation = 1;
    private RelativeLayout mLinearLayoutView;
    private RelativeLayout mRelativeLayoutView;
    private LinearLayout mLinearLayoutTopView;
    private TextView mTitle;
    private TextView mMessage;
    private ImageView iv_show, iv_big_show;
    private RadioGroup rg_dialog;
    private RadioButton rb_a, rb_b, rb_c, rb_d;
    private ImageView btn_cancle, btn_ok;
    private int mDuration = -1;
    private boolean isCancelable = true;

    private NiftyDialogBuilder(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    public static NiftyDialogBuilder getInstance(Context context) {
        int ort = context.getResources().getConfiguration().orientation;
        NiftyDialogBuilder instance;
        if (mOrientation != ort) {
            mOrientation = ort;
        }
        instance = new NiftyDialogBuilder(context, R.style.dialog_tran);
        return instance;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(
                params);
    }

    private void init(Context context) {
        View mDialogView = View.inflate(context, R.layout.dialog_layout, null);
        mLinearLayoutView = mDialogView
                .findViewById(R.id.parentPanel);// null
        mLinearLayoutView.setOnClickListener(arg0 -> keepDialog(NiftyDialogBuilder.this));
        mRelativeLayoutView = mDialogView
                .findViewById(R.id.main);// null
        mLinearLayoutTopView = mDialogView
                .findViewById(R.id.topPanel);// null

        mTitle = mDialogView.findViewById(R.id.alertTitle);// null
        mMessage = mDialogView.findViewById(R.id.message);// null
        et_player = mDialogView.findViewById(R.id.et_player);
        iv_show = mDialogView.findViewById(R.id.iv_show);
        iv_big_show = mDialogView.findViewById(R.id.iv_big_show);
        btn_ok = mDialogView.findViewById(R.id.btn_ok);
        btn_cancle = mDialogView.findViewById(R.id.btn_cancle);
        rg_dialog = mDialogView.findViewById(R.id.dialog_rg);
        rb_a = mDialogView.findViewById(R.id.rb_a);
        rb_b = mDialogView.findViewById(R.id.rb_b);
        rb_c = mDialogView.findViewById(R.id.rb_c);
        rb_d = mDialogView.findViewById(R.id.rb_d);
        setContentView(mDialogView);

        this.setOnShowListener(dialogInterface -> {
            mLinearLayoutView.setVisibility(View.VISIBLE);
            start(Effectstype.Shake);// 启动动画

        });
        mRelativeLayoutView.setOnClickListener(view -> {
            if (isCancelable) {
                getDismiss();
            }
        });
    }

    public NiftyDialogBuilder withTitle(int textResId) {
        toggleView(mLinearLayoutTopView, textResId);
        mTitle.setText(textResId);
        return this;
    }

    public NiftyDialogBuilder withTitle(CharSequence title) {
        toggleView(mLinearLayoutTopView, title);
        mTitle.setText(title);
        return this;
    }

    public NiftyDialogBuilder withTitleColor(String colorString) {
        mTitle.setTextColor(Color.parseColor(colorString));
        return this;
    }

    public NiftyDialogBuilder withMessage(int textResId) {
        toggleView(mMessage, textResId);
        mMessage.setText(textResId);
        return this;
    }

    public NiftyDialogBuilder withMessage(CharSequence msg) {
        toggleView(mMessage, msg);
        mMessage.setText(msg);
        return this;
    }

    public NiftyDialogBuilder withMessageColor(String colorString) {
        mMessage.setTextColor(Color.parseColor(colorString));
        return this;
    }

    public NiftyDialogBuilder withDuration(int duration) {
        this.mDuration = duration;
        return this;
    }


    public NiftyDialogBuilder withButtonCancle() {
        btn_cancle.setVisibility(View.VISIBLE);
        return this;
    }

    public NiftyDialogBuilder withButtonOk() {
        btn_ok.setVisibility(View.VISIBLE);
        return this;
    }

    public NiftyDialogBuilder setButtonCancleClick(View.OnClickListener click) {
        btn_cancle.setOnClickListener(click);
        return this;
    }

    public NiftyDialogBuilder setButtonOk(View.OnClickListener click) {
        btn_ok.setOnClickListener(click);
        return this;
    }

    public NiftyDialogBuilder withImageView(int obj) {
        iv_show.setVisibility(View.VISIBLE);
        iv_show.setImageResource(obj);
        return this;
    }

    public NiftyDialogBuilder withBigImageView(int obj) {
        mLinearLayoutView.setBackground(null);
        mLinearLayoutTopView.setVisibility(View.GONE);
        iv_big_show.setVisibility(View.VISIBLE);
        iv_big_show.setImageResource(obj);
        return this;
    }

    public NiftyDialogBuilder withRg(int a, int b, int c, int d) {
        rg_dialog.setVisibility(View.VISIBLE);
        rb_a.setText(a);
        rb_b.setText(b);
        rb_c.setText(c);
        rb_d.setText(d);
        return this;
    }

    public NiftyDialogBuilder setRb_a(View.OnClickListener click) {
        rb_a.setOnClickListener(click);
        return this;
    }

    public NiftyDialogBuilder setRb_b(View.OnClickListener click) {
        rb_b.setOnClickListener(click);
        return this;
    }

    public NiftyDialogBuilder setRb_c(View.OnClickListener click) {
        rb_c.setOnClickListener(click);
        return this;
    }

    public NiftyDialogBuilder setRb_d(View.OnClickListener click) {
        rb_d.setOnClickListener(click);
        return this;
    }

    public NiftyDialogBuilder withEditText(int obj) {
        et_player.setInputType(obj);
        et_player.setVisibility(View.VISIBLE);
        return this;
    }

    public NiftyDialogBuilder isCancelable(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCancelable(cancelable);
        return this;
    }

    private void toggleView(View view, Object obj) {
        if (obj == null) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void show() {
        super.show();
    }

    private void start(Effectstype type) {
        BaseEffects animator = type.getAnimator();
        if (mDuration != -1) {
            animator.setDuration(Math.abs(mDuration));
        }
        animator.start(mRelativeLayoutView);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mLinearLayoutView.setVisibility(View.GONE);
        btn_cancle.setVisibility(View.GONE);
        btn_ok.setVisibility(View.GONE);
    }


    public void getDismiss() {
        BaseEffects animator = Effectstype.DialogCancel.getAnimator();
        if (mDuration != -1) {
            animator.setDuration(Math.abs(mDuration));
        }
        animator.start(mRelativeLayoutView);
        new Handler().postDelayed(() -> {
            // TODO Auto-generated method stub
            dismiss();
            closeDialog(NiftyDialogBuilder.this);
        }, 500);
    }

    private void keepDialog(DialogInterface mDialog) {
        try {
            Field field = mDialog.getClass().getSuperclass()
                    .getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(mDialog, false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void closeDialog(DialogInterface mDialog) {
        try {
            Field field = mDialog.getClass().getSuperclass()
                    .getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(mDialog, true);
            mDialog.dismiss();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
