package cn.neday.graduates.repository

import cn.neday.graduates.MMKV_Crypt_Key
import cn.neday.graduates.repository.serialize.MMKVOwner
import cn.neday.graduates.repository.serialize.serial
import com.tencent.mmkv.MMKV

object Settings : MMKVOwner {
    override val kv: MMKV = MMKV.mmkvWithID("settings", MMKV.SINGLE_PROCESS_MODE, MMKV_Crypt_Key)

    var isFirstLaunch by serial(false)

    var isAllowMusicEnable by serial(true)
    var isAllowSoundEnable by serial(true)

    var isSponsoredUser by serial(false)

    var isPlaying by serial(false) // 是否游戏进行中
    var isSave by serial(false)
    var load by serial(9) // 可读档次数
}