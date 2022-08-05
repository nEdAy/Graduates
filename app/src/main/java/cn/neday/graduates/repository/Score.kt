package cn.neday.graduates.repository

import cn.neday.graduates.MMKV_Crypt_Key
import com.dylanc.longan.internalFileDirPath
import com.dylanc.mmkv.MMKVOwner
import com.dylanc.mmkv.mmkvBool
import com.dylanc.mmkv.mmkvInt
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

    var month by mmkvInt(96) // 剩余月
    var time by mmkvInt(230) // 剩余时间

    var isPosition by mmkvBool() // 职位 0/1
    var position by mmkvInt(0) // 职位

    var isHouse by mmkvBool() // 房产 0/1
    var house by mmkvInt(0) // 房产

    var isCar by mmkvBool() // 汽车 0/1
    var car by mmkvInt(0) // 汽车

    //TODO: i++
    var purity by mmkvInt(100) // 分数含金量
    var money by mmkvInt(2000) // 金钱
    var stock by mmkvInt(0) // 股票
    var career by mmkvInt(1) // 事业指数
    var love by mmkvInt(1) // 爱情指数
    var indexHouse by mmkvInt(100) // 房价指数
    var income by mmkvInt(2200) // 月净收益
    var communicationMonthly by mmkvInt(0) // 月净交际
    var happyMonthly by mmkvInt(-2) // 月净快乐

    var isStock by mmkvBool() // 股票 0/1
    var indexStock by mmkvInt(1700) // 股票指数
    var randomStock by mmkvInt(100) // 随机股票指数

    var healthy by mmkvInt(200) // 健康
    var ability by mmkvInt(100) // 能力
    var experience by mmkvInt(100) // 经验
    var happy by mmkvInt(200) // 快乐
    var morality by mmkvInt(200) // 道德
    var communication by mmkvInt(100) // 交际

    var isPartner by mmkvBool() // 伴侣 0/1
    var isLottery by mmkvBool() // 彩票 0/1

    var yd by mmkvInt(0) // YD 30
    var bl by mmkvInt(0) // BL 30
    var ts by mmkvInt(0) // TX 20
    var py by mmkvInt(0) // PY 30
    var gj by mmkvInt(0) // GJ 20
    var cm by mmkvInt(0) // CM 30
    var cj by mmkvInt(0) // CJ 30
    var zj by mmkvInt(0) // ZJ 30
    var jb by mmkvInt(0) // JB 30
    var sw by mmkvInt(0) // SW 20
    var ty by mmkvInt(0) // TY 30
    var hj by mmkvInt(0) // HJ 30

    var partner by mmkvInt()
    var partnerStory by mmkvInt()

    var partnerXy by mmkvInt(0)
    var partnerZj by mmkvInt(0)
    var partnerSn by mmkvInt(0)
    var partnerSnTime by mmkvInt(0) //todo: i++
}