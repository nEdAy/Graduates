package cn.neday.graduates.repository

import cn.neday.graduates.MMKV_Crypt_Key
import cn.neday.graduates.repository.serialize.MMKVOwner
import cn.neday.graduates.repository.serialize.serialLazy
import com.dylanc.longan.internalFileDirPath
import com.tencent.mmkv.MMKV

/**
 * 游戏快照/本地存档
 * 单存档模式，且单局游戏中限制读档次数（9）
 * Save -> 游戏快照(mmkv) -> 本地存档(mmkv/save)
 * Load -> 本地存档(mmkv/save) -> 游戏快照(mmkv)
 */
object Score : MMKVOwner {
    private const val mmapID = "score";
    private const val manualSaveDir = "/mmkv/save";
    override val kv: MMKV = MMKV.mmkvWithID(mmapID, MMKV.SINGLE_PROCESS_MODE, MMKV_Crypt_Key)

    fun manualSave(): Boolean {
        return MMKV.backupOneToDirectory(
            mmapID,
            "$internalFileDirPath$manualSaveDir",
            null
        )
    }

    fun manualLoad(): Boolean {
        return MMKV.restoreOneMMKVFromDirectory(
            mmapID,
            "$internalFileDirPath$manualSaveDir",
            null
        )
    }

    var month by serialLazy(96) // 剩余月
    var time by serialLazy(230) // 剩余时间

    var isPosition by serialLazy(false) // 职位 0/1
    var position by serialLazy(0) // 职位

    var isHouse by serialLazy(false) // 房产 0/1
    var house by serialLazy(0) // 房产

    var isCar by serialLazy(false) // 汽车 0/1
    var car by serialLazy(0) // 汽车

    //TODO: i++
    var purity by serialLazy(100) // 分数含金量
    var money by serialLazy(2000) // 金钱
    var stock by serialLazy(0) // 股票
    var career by serialLazy(1) // 事业指数
    var love by serialLazy(1) // 爱情指数
    var indexHouse by serialLazy(100) // 房价指数
    var income by serialLazy(2200) // 月净收益
    var communicationMonthly by serialLazy(0) // 月净交际
    var happyMonthly by serialLazy(-2) // 月净快乐

    var isStock by serialLazy(false) // 股票 0/1
    var indexStock by serialLazy(1700) // 股票指数
    var randomStock by serialLazy(100) // 随机股票指数

    var healthy by serialLazy(200) // 健康
    var ability by serialLazy(100) // 能力
    var experience by serialLazy(100) // 经验
    var happy by serialLazy(200) // 快乐
    var morality by serialLazy(200) // 道德
    var communication by serialLazy(100) // 交际

    var isPartner by serialLazy(false) // 伴侣 0/1
    var isLottery by serialLazy(false) // 彩票 0/1

    var yd by serialLazy(0) // YD 30
    var bl by serialLazy(0) // BL 30
    var ts by serialLazy(0) // TX 20
    var py by serialLazy(0) // PY 30
    var gj by serialLazy(0) // GJ 20
    var cm by serialLazy(0) // CM 30
    var cj by serialLazy(0) // CJ 30
    var zj by serialLazy(0) // ZJ 30
    var jb by serialLazy(0) // JB 30
    var sw by serialLazy(0) // SW 20
    var ty by serialLazy(0) // TY 30
    var hj by serialLazy(0) // HJ 30

    var partner by serialLazy(0)
    var partnerStory by serialLazy(0)

    var partnerXy by serialLazy(0)
    var partnerZj by serialLazy(0)
    var partnerSn by serialLazy(0)
    var partnerSnTime by serialLazy(0)
}