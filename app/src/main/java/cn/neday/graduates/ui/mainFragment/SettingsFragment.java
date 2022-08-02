package cn.neday.graduates.ui.mainFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;

import cn.neday.graduates.R;
import cn.neday.graduates.ThisApplication;
import cn.neday.graduates.util.SpSettingsUtil;
import cn.neday.graduates.view.BaseFragment;
import cn.neday.graduates.view.NiftyDialogBuilder;


public class SettingsFragment extends BaseFragment implements View.OnClickListener {
    @SuppressLint("StaticFieldLeak")
    private static final SettingsFragment instance = new SettingsFragment();
    private View parentView;
    private ImageView iv_open_music, iv_close_music, iv_open_sound, iv_close_sound;
    private SpSettingsUtil mSharedSettingsUtil;
    private ImageView btn_about, btn_suggest, btn_splash, btn_update;
    private RelativeLayout rl_switch_music, rl_switch_sound, rl_text_phone, rl_text_nick, rl_text_sex;
    private TextView tv_phone, tv_nick, tv_sex;

    public SettingsFragment() {
    }

    public static SettingsFragment newInstance() {
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_settings, container, false);
        setUpViews();
        return parentView;
    }

    private void setUpViews() {
        ImageView iv_back = parentView.findViewById(R.id.btn_back);
        iv_back.setOnClickListener(v -> getActivity().getSupportFragmentManager().popBackStack());
        rl_switch_music = parentView.findViewById(R.id.rl_switch_music);
        rl_switch_sound = parentView.findViewById(R.id.rl_switch_sound);
        rl_text_phone = parentView.findViewById(R.id.rl_text_phone);
        rl_text_nick = parentView.findViewById(R.id.rl_text_nick);
        rl_text_sex = parentView.findViewById(R.id.rl_text_sex);

        iv_open_music = parentView.findViewById(R.id.iv_open_music);
        iv_close_music = parentView.findViewById(R.id.iv_close_music);
        iv_open_sound = parentView.findViewById(R.id.iv_open_sound);
        iv_close_sound = parentView.findViewById(R.id.iv_close_sound);

        tv_phone = parentView.findViewById(R.id.tv_phone);
        tv_nick = parentView.findViewById(R.id.tv_nick);
        tv_sex = parentView.findViewById(R.id.tv_sex);
        btn_splash = parentView.findViewById(R.id.btn_splash);
        btn_about = parentView.findViewById(R.id.btn_abont);
        btn_suggest = parentView.findViewById(R.id.btn_suggent);
        btn_update = parentView.findViewById(R.id.btn_update);
        setListener();
    }

    private void setListener() {
        rl_switch_music.setOnClickListener(this);
        rl_switch_sound.setOnClickListener(this);
        rl_text_phone.setOnClickListener(this);
        rl_text_nick.setOnClickListener(this);
        rl_text_sex.setOnClickListener(this);
        btn_splash.setOnClickListener(this);
        btn_about.setOnClickListener(this);
        btn_suggest.setOnClickListener(this);
        btn_update.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSharedSettingsUtil = mApplication.getSpSettingsUtil();
        boolean isAllowMusic = mSharedSettingsUtil.isAllowMusic();
        if (isAllowMusic) {
            iv_open_music.setVisibility(View.VISIBLE);
            iv_close_music.setVisibility(View.INVISIBLE);
        } else {
            iv_open_music.setVisibility(View.INVISIBLE);
            iv_close_music.setVisibility(View.VISIBLE);
        }
        boolean isAllowSound = mSharedSettingsUtil.isAllowSound();
        if (isAllowSound) {
            iv_open_sound.setVisibility(View.VISIBLE);
            iv_close_sound.setVisibility(View.INVISIBLE);
        } else {
            iv_open_sound.setVisibility(View.INVISIBLE);
            iv_close_sound.setVisibility(View.VISIBLE);
        }
        String userPhone = mSharedSettingsUtil.getUserPhone();
        tv_phone.setText(userPhone);
        String userNick = mSharedSettingsUtil.getUserNick();
        tv_nick.setText(userNick);
        boolean isUserSex = mSharedSettingsUtil.isUserSex();
        tv_sex.setText(isUserSex ? "男" : "女");
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder
                .getInstance(getActivity());
        switch (v.getId()) {
            case R.id.rl_switch_music:
                ThisApplication.playSound(R.raw.button_1);
                if (iv_open_music.getVisibility() == View.VISIBLE) {
                    iv_open_music.setVisibility(View.INVISIBLE);
                    iv_close_music.setVisibility(View.VISIBLE);
                    ThisApplication.setAllowMusicEnable(false);
                } else {
                    iv_open_music.setVisibility(View.VISIBLE);
                    iv_close_music.setVisibility(View.INVISIBLE);
                    ThisApplication.setAllowMusicEnable(true);
                }
                onActivityCreated(null);
                break;
            case R.id.rl_switch_sound:
                ThisApplication.playSound(R.raw.button_1);
                if (iv_open_sound.getVisibility() == View.VISIBLE) {
                    iv_open_sound.setVisibility(View.INVISIBLE);
                    iv_close_sound.setVisibility(View.VISIBLE);
                    ThisApplication.setAllowSoundEnable(false);
                } else {
                    iv_open_sound.setVisibility(View.VISIBLE);
                    iv_close_sound.setVisibility(View.INVISIBLE);
                    ThisApplication.setAllowSoundEnable(true);
                }
                onActivityCreated(null);
                break;
            case R.id.rl_text_phone:
                ThisApplication.playSound(R.raw.button_0);
                dialogBuilder.withTitle("设置手机号")
                        .withMessage(null).withEditText(InputType.TYPE_CLASS_PHONE).isCancelable(true)
                        .withDuration(500).withButtonCancle().withButtonOk()
                        .setButtonCancleClick(v14 -> dialogBuilder.getDismiss()).setButtonOk(v13 -> {
                            String phone = NiftyDialogBuilder.et_player.getText().toString();
                            if (RegexUtils.isMobileExact(phone)) {
                                mSharedSettingsUtil.setUserPhone(phone);
                                dialogBuilder.dismiss();
                                onActivityCreated(null);
                            } else {
                                showToast("手机号不合法，请确认后重新输入！", false);
                            }
                        }).show();
                break;
            case R.id.rl_text_nick:
                ThisApplication.playSound(R.raw.button_0);
                dialogBuilder.withTitle("设置昵称")
                        .withMessage(null).withEditText(InputType.TYPE_CLASS_TEXT).isCancelable(true)
                        .withDuration(500).withButtonCancle().withButtonOk()
                        .setButtonCancleClick(v12 -> dialogBuilder.getDismiss()).setButtonOk(v1 -> {
                            String nickname = NiftyDialogBuilder.et_player.getText().toString();
                            mSharedSettingsUtil.setUserNick(nickname);
                            //                        KTAccountManager.setNickname(nickname, new KTAccountManager.OnSetNicknameListener() {
                            //                            @Override
                            //                            public void onSetNicknameResult(boolean isSuccess, String nickname,
                            //                                                            KTUser user, KTError error) {
                            //                                if (isSuccess) {
                            //                                    showToast("网络帐号昵称同步成功", true);
                            //                                } else {
                            //                                    showToast("网络帐号昵称同步失败", false);
                            //                                }
                            //                            }
                            //                        });
                            dialogBuilder.dismiss();
                            onActivityCreated(null);
                        }).show();
                break;
            case R.id.rl_text_sex:
                if (tv_sex.getText().equals("男")) {
                    mSharedSettingsUtil.setUserSexEnable(false);
                    tv_sex.setText("女");
                } else {
                    mSharedSettingsUtil.setUserSexEnable(true);
                    tv_sex.setText("男");
                }
                onActivityCreated(null);
                break;
            case R.id.btn_splash:
                ThisApplication.playSound(R.raw.button_0);
                mSharedSettingsUtil.cleanSharedPreference();
                onActivityCreated(null);
                break;
            case R.id.btn_abont:
                ThisApplication.playSound(R.raw.button_0);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_main,
                                AboutFragment.newInstance()).addToBackStack(null)
                        .commit();
                break;
            case R.id.btn_suggent:
                ThisApplication.playSound(R.raw.button_0);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_main,
                                SuggestFragment.newInstance()).addToBackStack(null)
                        .commit();
                break;
            case R.id.btn_update:
                // 检查更新
                // Beta.checkUpgrade();
                break;
            default:
                break;
        }
    }

}
