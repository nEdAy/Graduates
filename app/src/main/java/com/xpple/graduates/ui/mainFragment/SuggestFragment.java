package com.xpple.graduates.ui.mainFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xpple.graduates.R;
import com.xpple.graduates.ThisApplication;
import com.xpple.graduates.util.SpSettingsUtil;
import com.xpple.graduates.view.BaseFragment;

import java.text.DecimalFormat;

public class SuggestFragment extends BaseFragment implements
        RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    @SuppressLint("StaticFieldLeak")
    private static final SuggestFragment instance = new SuggestFragment();
    private View parentView;
    private SpSettingsUtil mSharedSettingsUtil;
    private EditText price;
    private ImageView go;
    private RadioGroup type;
    private RelativeLayout rl_switch_suggest;
    private ImageView iv_open_suggest, iv_close_suggest;
    private TextView tv_suggest;
    private Integer suggest_money;

    public SuggestFragment() {

    }

    public static SuggestFragment newInstance() {
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_suggest, container, false);
        setUpViews();
        return parentView;
    }

    private void setUpViews() {
        ImageView iv_back = parentView.findViewById(R.id.btn_back);
        iv_back.setOnClickListener(v -> getActivity().getSupportFragmentManager().popBackStack());
        price = parentView.findViewById(R.id.price);
        go = parentView.findViewById(R.id.go);
        type = parentView.findViewById(R.id.type);
        rl_switch_suggest = parentView.findViewById(R.id.rl_switch_suggest);
        iv_close_suggest = parentView.findViewById(R.id.iv_close_suggest);
        iv_open_suggest = parentView.findViewById(R.id.iv_open_suggest);
        tv_suggest = parentView.findViewById(R.id.tv_suggest);
        setListener();
    }

    private void setListener() {
        rl_switch_suggest.setOnClickListener(this);
        type.setOnCheckedChangeListener(this);
        go.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSharedSettingsUtil = mApplication.getSpSettingsUtil();
        suggest_money = mSharedSettingsUtil.getUserSuggestMoney();
        boolean isUserSuggest = mSharedSettingsUtil.isUserSuggest();
        if (isUserSuggest) {
            iv_open_suggest.setVisibility(View.VISIBLE);
            iv_close_suggest.setVisibility(View.INVISIBLE);
        } else {
            iv_open_suggest.setVisibility(View.INVISIBLE);
            iv_close_suggest.setVisibility(View.VISIBLE);
        }

        DecimalFormat df = new DecimalFormat("0.00");
        String mSuggestMoney = df.format(suggest_money / 100);
        tv_suggest.setText(mSuggestMoney);

    }

    // 以下仅为控件操作，可以略过
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.alipay:
                // 以下仅为控件操作，可以略过
                go.setImageResource(R.mipmap.btn_zfbzz);
                break;
            case R.id.wxpay:
                // 以下仅为控件操作，可以略过
                go.setImageResource(R.mipmap.btn_wxzz);
                break;
            default:
                break;
        }
    }

    // 默认为0.1
    private double getPrice() {
        double price = 1;
        try {
            price = Double.parseDouble(this.price.getText().toString());
        } catch (NumberFormatException ignored) {
        }
        return price;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go:
                // 当选择的是支付宝支付时
                // 调用插件用微信支付
                break;
            case R.id.rl_switch_suggest:
                ThisApplication.playSound(R.raw.button_1);
                if (suggest_money > 0) {
                    if (iv_open_suggest.getVisibility() == View.VISIBLE) {
                        iv_open_suggest.setVisibility(View.INVISIBLE);
                        iv_close_suggest.setVisibility(View.VISIBLE);
                        mSharedSettingsUtil.setUserSuggestEnable(false);
                    } else {
                        iv_open_suggest.setVisibility(View.VISIBLE);
                        iv_close_suggest.setVisibility(View.INVISIBLE);
                        mSharedSettingsUtil.setUserSuggestEnable(true);
                    }
                    onActivityCreated(null);
                } else {
                    showToast(R.string.suggest_0, true);
                }
                break;
        }
    }
}
