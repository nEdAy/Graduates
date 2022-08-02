package com.xpple.graduates.ui.gameFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.xpple.graduates.R;
import com.xpple.graduates.ThisApplication;
import com.xpple.graduates.ui.MainActivity;
import com.xpple.graduates.util.SpSettingsUtil;
import com.xpple.graduates.view.BaseFragment;
import com.xpple.graduates.view.NiftyDialogBuilder;

public class StartFragmentA extends BaseFragment {
    @SuppressLint("StaticFieldLeak")
    private static final StartFragmentA instance = new StartFragmentA();
    private View parentView;
    private SpSettingsUtil mSharedSettingsUtil;
    private Boolean mSex;
    private RadioButton rb_sex_0, rb_sex_1;
    private TextView tv_nick;

    public StartFragmentA() {
    }

    public static StartFragmentA newInstance() {
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_start_a, container, false);
        setUpViews();
        return parentView;
    }

    private void setUpViews() {
        ImageView btn_next_start = parentView.findViewById(R.id.btn_next_start);
        btn_next_start.setOnClickListener(v -> {
            playHeartbeatAnimation(v);
            nextStart();
        });
        tv_nick = parentView.findViewById(R.id.tv_nick);
        tv_nick.setOnClickListener(v -> {
            playHeartbeatAnimation(v);
            upNick();
        });
        rb_sex_0 = parentView.findViewById(R.id.rb_sex_0);
        rb_sex_1 = parentView.findViewById(R.id.rb_sex_1);
        RadioGroup rg_sex = parentView.findViewById(R.id.rg_sex);
        rg_sex.setOnCheckedChangeListener((arg0, arg1) -> {
            // TODO Auto-generated method stub
            //获取变更后的选中项的ID
            int rBId = arg0.getCheckedRadioButtonId();
            switch (rBId) {
                case R.id.rb_sex_0:
                    mSex = false;
                    mSharedSettingsUtil.setUserSexEnable(false);
                    break;
                case R.id.rb_sex_1:
                    mSex = true;
                    mSharedSettingsUtil.setUserSexEnable(true);
                    break;
                default:
                    break;
            }
        });
    }

    private void upNick() {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder
                .getInstance(getActivity());
        dialogBuilder.withTitle("设置昵称")
                .withMessage(null).withEditText(InputType.TYPE_CLASS_TEXT).isCancelable(true)
                .withDuration(500).withButtonCancle().withButtonOk()
                .setButtonCancleClick(v -> dialogBuilder.getDismiss()).setButtonOk(v -> {
                    String nickname = NiftyDialogBuilder.et_player.getText().toString();
                    mSharedSettingsUtil.setUserNick(nickname);
                    //                KTAccountManager.setNickname(nickname, new KTAccountManager.OnSetNicknameListener() {
                    //                    @Override
                    //                    public void onSetNicknameResult(boolean isSuccess, String nickname,
                    //                                                    KTUser user, KTError error) {
                    //                        if (isSuccess) {
                    //                            showToast("网络帐号昵称同步成功", true);
                    //                        } else {
                    //                            showToast("网络帐号昵称同步失败", false);
                    //                        }
                    //                    }
                    //                });
                    dialogBuilder.dismiss();
                    onActivityCreated(null);
                }).show();
    }

    private void nextStart() {
        ThisApplication.playSound(R.raw.button_0);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_game,
                        StartFragmentB.newInstance()).addToBackStack(null)
                .commit();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSharedSettingsUtil = mApplication.getSpSettingsUtil();
        mSex = mSharedSettingsUtil.isUserSex();
        rb_sex_0.setChecked(!mSex);
        rb_sex_1.setChecked(mSex);
        String mNick = mSharedSettingsUtil.getUserNick();
        tv_nick.setText(mNick);
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                startAnimActivity(MainActivity.class);
                getActivity().finish();
                return true;
            }
            return false;
        });
    }
}
