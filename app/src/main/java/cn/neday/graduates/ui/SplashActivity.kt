package cn.neday.graduates.ui

import android.annotation.SuppressLint
import android.os.Bundle
import cn.neday.graduates.R
import cn.neday.graduates.TapTap_Client_ID
import cn.neday.graduates.TapTap_Client_Token
import cn.neday.graduates.TapTap_Server_Url
import cn.neday.graduates.view.BaseActivity
import com.tapsdk.bootstrap.TapBootstrap
import com.tds.common.entities.TapConfig
import com.tds.common.models.TapRegionType
import java.util.*
import kotlin.concurrent.schedule

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initTapTap()
    }

    private fun initTapTap() {
        val tdsConfig = TapConfig.Builder()
            .withAppContext(this) // Context 上下文
            .withClientId(TapTap_Client_ID) // 必须，开发者中心对应 Client ID
            .withClientToken(TapTap_Client_Token) // 必须，开发者中心对应 Client Token
            .withServerUrl(TapTap_Server_Url) // 必须，开发者中心 > 你的游戏 > 游戏服务 > 基本信息 > 域名配置 > API
            .withRegionType(TapRegionType.CN) // TapRegionType.CN：中国大陆，TapRegionType.IO：其他国家或地区
            .build()
        TapBootstrap.init(this, tdsConfig)
    }

    override fun onResume() {
        super.onResume()
        Timer().schedule(1500) {
            startAnimActivity(MainActivity::class.java)
            finish()
        }
    }
}