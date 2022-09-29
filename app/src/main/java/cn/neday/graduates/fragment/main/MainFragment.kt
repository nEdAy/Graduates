package cn.neday.graduates.fragment.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.commit
import cn.neday.graduates.R
import cn.neday.graduates.activity.GameActivity
import cn.neday.graduates.databinding.FragmentMainBinding
import cn.neday.graduates.doOnClickWithSound
import cn.neday.graduates.fragment.BaseBindingFragment
import cn.neday.graduates.repository.Score
import cn.neday.graduates.repository.Settings
import cn.neday.graduates.view.sheets.Info
import com.dylanc.longan.startActivity
import com.dylanc.longan.toast
import com.tapsdk.antiaddictionui.AntiAddictionUIKit
import com.tapsdk.bootstrap.Callback
import com.tapsdk.bootstrap.account.TDSUser
import com.tapsdk.bootstrap.exceptions.TapError

class MainFragment : BaseBindingFragment<FragmentMainBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        if (Settings.isPlaying) {
            binding.btnStart.setImageResource(R.drawable.btn_continue)
            binding.btnReplay.visibility = View.VISIBLE
        } else {
            binding.btnStart.setImageResource(R.drawable.btn_start)
            binding.btnReplay.visibility = View.GONE
        }
    }

    private fun setOnClickListener() {
        binding.btnExit.doOnClickWithSound { showExitDialog() }
        binding.btnBadge.doOnClickWithSound { showBadge() }
        binding.btnTop.doOnClickWithSound { showExitDialog() }
        binding.btnStart.doOnClickWithSound { startActivity<GameActivity>() }
        binding.btnReplay.doOnClickWithSound { showReStartDialog() }
        binding.btnOptions.doOnClickWithSound {
            activity?.supportFragmentManager?.commit {
                replace(R.id.fragment_main, SettingsFragment())
                addToBackStack(null)
            }
        }
        binding.btnHint.doOnClickWithSound {
            activity?.supportFragmentManager?.commit {
                replace(R.id.fragment_main, HintFragment())
                addToBackStack(null)
            }
        }
        binding.btnCommunity.doOnClickWithSound { onTapTapCommunity() }
    }

    override fun onResume() {
        super.onResume()
        view?.isFocusableInTouchMode = true
        view?.requestFocus()
        view?.setOnKeyListener { _: View?, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                // 当back按下
                showExitDialog()
                return@setOnKeyListener true
            }
            false
        }
    }

    private fun showExitDialog() {
        Info.showDialog(R.string.exit_or_un, R.string.exit_or_un_0) {
            activity?.finish()
        }
    }

    private fun showBadge() {
        toast("成就系统暂不开放,请分享支持")
    }

    private fun showReStartDialog() {
        Info.showDialog(R.string.restart_or_un, R.string.restart_or_un_0) {
            Settings.isPlaying = false
            Score.kv.clear()
            startActivity<GameActivity>()
            activity?.finish()
        }
    }

    private fun onTapTapCommunity() {
        if (null == TDSUser.currentUser()) {
            // 未登录
            TDSUser.loginWithTapTap(activity, object : Callback<TDSUser> {
                override fun onSuccess(resultUser: TDSUser) {
//                            Map<String,Object> authData = (Map<String,Object>)resultUser.get("authData");
//                            Map<String,Object> taptapAuthData = (Map<String, Object>) authData.get("taptap");
//                            Log.d(TAG,"authData:"+ JSON.toJSONString(authData));
//                            Map<String, Object> authDataResult = (Map<String, Object>) ((Map<String, Object>) resultUser.get("authData")).get("taptap");
//                            Log.d(TAG, "unionid:"+taptapAuthData.get("unionid").toString());
//                            Log.d(TAG, "openid:"+taptapAuthData.get("openid").toString());
                    Toast.makeText(
                        activity,
                        "succeed to login with TapTap.",
                        Toast.LENGTH_SHORT
                    ).show()
                    // 开发者可以调用 resultUser 的方法获取更多属性。
                    val userId = resultUser.objectId // 用户唯一标识
                    val userName = resultUser.username
                    val avatar = resultUser["avatar"] as String // 头像
                    val nickName = resultUser["nickname"] as String // 昵称

                    AntiAddictionUIKit.startup(activity, true, userId)

                    // 玩家在游戏内退出账号时调用，重置防沉迷状态。
                    // AntiAddictionUIKit.logout();
                }

                override fun onFail(error: TapError) {
                    Toast.makeText(activity, error.message, Toast.LENGTH_SHORT).show()
                }
            }, "public_profile")
        } else {
            // 已登录，进入游戏
        }
    }
}