package cn.neday.graduates.repository

import cn.neday.graduates.MMKV_Crypt_Key
import cn.neday.graduates.repository.serialize.MMKVOwner
import cn.neday.graduates.repository.serialize.serialLazy
import com.tencent.mmkv.MMKV

object Settings : MMKVOwner {
    override val kv: MMKV = MMKV.mmkvWithID("settings", MMKV.SINGLE_PROCESS_MODE, MMKV_Crypt_Key)

    var isFirstLaunch by serialLazy(false)

    var isAllowMusicEnable by serialLazy(true)
    var isAllowSoundEnable by serialLazy(true)

    var isSponsoredUser by serialLazy(false)

    var isPlaying by serialLazy(false) // 是否游戏进行中
    var isSave by serialLazy(false)
    var load by serialLazy(9) // 可读档次数
}