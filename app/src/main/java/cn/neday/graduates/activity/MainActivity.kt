package cn.neday.graduates.activity

import android.os.Bundle
import androidx.fragment.app.commit
import cn.neday.graduates.MusicConductor.destroyMusic
import cn.neday.graduates.MusicConductor.pauseMusic
import cn.neday.graduates.MusicConductor.resumeMusic
import cn.neday.graduates.MusicConductor.startMusic
import cn.neday.graduates.R
import cn.neday.graduates.TapTap_Client_ID
import cn.neday.graduates.databinding.ActivityMainBinding
import cn.neday.graduates.ui.main.MainFragment
import com.dylanc.longan.logDebug
import com.dylanc.longan.toast
import com.tapsdk.antiaddiction.config.AntiAddictionFunctionConfig
import com.tapsdk.antiaddiction.constants.Constants
import com.tapsdk.antiaddictionui.AntiAddictionUIKit

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment()
        initAntiAddiction()
    }

    private fun setFragment() {
        supportFragmentManager.commit {
            replace(R.id.fragment_main, MainFragment())
        }
    }

    private fun initAntiAddiction() {
        AntiAddictionUIKit.init(
            this, TapTap_Client_ID, AntiAddictionFunctionConfig.Builder()
                .enablePaymentLimit(true) // 是否启用消费限制功能
                .enableOnLineTimeLimit(false) // 是否启用时长限制功能
                .showSwitchAccount(false) // 是否显示切换账号按钮
                .build()
        ) { code, extras ->
            // 限制类型：
            // "0"，无限制（成年玩家）
            // "1"，有限制（未成年玩家）
            // public string restrict_type = "";
            // 显示给玩家的提示内容
            // public string description = "";
            // 显示给玩家的提示标题
            // public string title = "";
            when (code) {
                Constants.ANTI_ADDICTION_CALLBACK_CODE.LOGIN_SUCCESS -> {
                    AntiAddictionUIKit.enterGame() // 开始计时
                    logDebug("玩家登录后判断当前玩家可以进行游戏")
                }
                Constants.ANTI_ADDICTION_CALLBACK_CODE.LOGOUT -> {
                    AntiAddictionUIKit.leaveGame() // 停止计时
                }
                Constants.ANTI_ADDICTION_CALLBACK_CODE.TIME_LIMIT -> {

                }
                Constants.ANTI_ADDICTION_CALLBACK_CODE.NIGHT_STRICT -> { // 有extras
                    toast("未成年玩家当前无法进行游戏" + extras["description"])

                }
                Constants.ANTI_ADDICTION_CALLBACK_CODE.OPEN_ALERT_TIP -> { // 有extras
                    toast("未成年允许游戏弹窗" + extras["description"])
                }
                Constants.ANTI_ADDICTION_CALLBACK_CODE.TOKEN_EXPIRED -> {

                }
                Constants.ANTI_ADDICTION_CALLBACK_CODE.REAL_NAME_STOP -> {
                    toast("实名过程中点击了关闭实名窗")
                }
                Constants.ANTI_ADDICTION_CALLBACK_CODE.SWITCH_ACCOUNT -> {
                    toast("点击切换账号按钮")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        destroyMusic()
        startMusic(0)
    }

    override fun onResume() {
        super.onResume()
        resumeMusic(0)
    }

    override fun onPause() {
        super.onPause()
        pauseMusic()
    }
}