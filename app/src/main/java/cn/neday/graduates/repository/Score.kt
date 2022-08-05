package cn.neday.graduates.repository

import cn.neday.graduates.MMKV_Crypt_Key
import cn.neday.graduates.repository.serialize.MMKVOwner
import cn.neday.graduates.repository.serialize.serial
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

    var month by serial(96) // 剩余月
    var time by serial(230) // 剩余时间

    var isPosition by serial(false) // 职位 0/1
    var position by serial(0) // 职位

    var isHouse by serial(false) // 房产 0/1
    var house by serial(0) // 房产

    var isCar by serial(false) // 汽车 0/1
    var car by serial(0) // 汽车

    //TODO: i++
    var purity by serial(100) // 分数含金量
    var money by serial(2000) // 金钱
    var stock by serial(0) // 股票
    var career by serial(1) // 事业指数
    var love by serial(1) // 爱情指数
    var indexHouse by serial(100) // 房价指数
    var income by serial(2200) // 月净收益
    var communicationMonthly by serial(0) // 月净交际
    var happyMonthly by serial(-2) // 月净快乐

    var isStock by serial(false) // 股票 0/1
    var indexStock by serial(1700) // 股票指数
    var randomStock by serial(100) // 随机股票指数

    var healthy by serial(200) // 健康
    var ability by serial(100) // 能力
    var experience by serial(100) // 经验
    var happy by serial(200) // 快乐
    var morality by serial(200) // 道德
    var communication by serial(100) // 交际

    var isPartner by serial(false) // 伴侣 0/1
    var isLottery by serial(false) // 彩票 0/1

    var yd by serial(0) // YD 30
    var bl by serial(0) // BL 30
    var ts by serial(0) // TX 20
    var py by serial(0) // PY 30
    var gj by serial(0) // GJ 20
    var cm by serial(0) // CM 30
    var cj by serial(0) // CJ 30
    var zj by serial(0) // ZJ 30
    var jb by serial(0) // JB 30
    var sw by serial(0) // SW 20
    var ty by serial(0) // TY 30
    var hj by serial(0) // HJ 30

    var partner by serial(0)
    var partnerStory by serial(0)

    var partnerXy by serial(0)
    var partnerZj by serial(0)
    var partnerSn by serial(0)
    var partnerSnTime by serial(0)
}