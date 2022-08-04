package cn.neday.graduates.repository

import cn.neday.graduates.MMKV_Crypt_Key
import com.dylanc.mmkv.MMKVOwner
import com.dylanc.mmkv.mmkvBool
import com.dylanc.mmkv.mmkvInt
import com.tencent.mmkv.MMKV

object Settings : MMKVOwner {
    override val kv: MMKV = MMKV.mmkvWithID("settings", MMKV.SINGLE_PROCESS_MODE, MMKV_Crypt_Key)

    var isFirstLaunch by mmkvBool()

    var isAllowMusicEnable by mmkvBool(true)
    var isAllowSoundEnable by mmkvBool(true)

    var isSponsoredUser by mmkvBool()

    var isPlaying by mmkvBool() // 是否游戏进行中
    var isSave by mmkvBool()
    var load by mmkvInt(9) // 可读档次数
}