//package cn.neday.graduates.util
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.SharedPreferences
//
///**
// * 存档管理
// */
//class SpSaveUtil @SuppressLint("CommitPrefEdits") constructor(context: Context, name: String?) {
//    private val mSharedPreferences: SharedPreferences
//    fun cleanSharedPreference() {
//        editor.clear()
//        editor.commit()
//    }
//
//    //    private static final String PARTNER_FY = "partner_fy"; //飞燕
//
//    // 健康
//    var healthy: Int?
//        get() = mSharedPreferences.getInt(SCORE_HEALTHY, 0)
//        set(healthy) {
//            editor.putInt(SCORE_HEALTHY, healthy?)
//            editor.commit()
//        }
//
//    // 金钱
//    var money: Int?
//        get() = mSharedPreferences.getInt(SCORE_MONEY, 0)
//        set(money) {
//            editor.putInt(SCORE_MONEY, money?)
//            editor.commit()
//        }
//
//    // 能力
//    var ability: Int?
//        get() = mSharedPreferences.getInt(SCORE_ABILITY, 0)
//        set(ability) {
//            editor.putInt(SCORE_ABILITY, ability?)
//            editor.commit()
//        }
//
//    // 经验
//    var experience: Int?
//        get() = mSharedPreferences.getInt(SCORE_EXPERIENCE, 0)
//        set(experience) {
//            editor.putInt(SCORE_EXPERIENCE, experience?)
//            editor.commit()
//        }
//
//    // 快乐
//    var happy: Int?
//        get() = mSharedPreferences.getInt(SCORE_HAPPY, 0)
//        set(happy) {
//            editor.putInt(SCORE_HAPPY, happy?)
//            editor.commit()
//        }
//
//    // 道德
//    var morality: Int?
//        get() = mSharedPreferences.getInt(SCORE_MORALITY, 0)
//        set(morality) {
//            editor.putInt(SCORE_MORALITY, morality?)
//            editor.commit()
//        }
//
//    // 交际
//    var communication: Int?
//        get() = mSharedPreferences.getInt(SCORE_COMMUNICATION, 0)
//        set(communication) {
//            editor.putInt(SCORE_COMMUNICATION, communication?)
//            editor.commit()
//        }
//
//    // 剩余月
//    var month: Int?
//        get() = mSharedPreferences.getInt(SCORE_MONTH, 0)
//        set(month) {
//            editor.putInt(SCORE_MONTH, month?)
//            editor.commit()
//        }
//
//    // 剩余时间
//    var time: Int?
//        get() = mSharedPreferences.getInt(SCORE_TIME, 0)
//        set(time) {
//            editor.putInt(SCORE_TIME, time?)
//            editor.commit()
//        }
//
//    // 股票
//    var stock: Int?
//        get() = mSharedPreferences.getInt(SCORE_STOCK, 0)
//        set(stock) {
//            editor.putInt(SCORE_STOCK, stock?)
//            editor.commit()
//        }
//
//    // 职位
//    var position: Int?
//        get() = mSharedPreferences.getInt(SCORE_POSITION, 0)
//        set(position) {
//            editor.putInt(SCORE_POSITION, position?)
//            editor.commit()
//        }
//
//    // 房产
//    var house: Int?
//        get() = mSharedPreferences.getInt(SCORE_HOUSE, 0)
//        set(house) {
//            editor.putInt(SCORE_HOUSE, house?)
//            editor.commit()
//        }
//
//    // 汽车
//    var car: Int?
//        get() = mSharedPreferences.getInt(SCORE_CAR, 0)
//        set(car) {
//            editor.putInt(SCORE_CAR, car?)
//            editor.commit()
//        }
//
//    // 伴侣
//    var partner: Int?
//        get() = mSharedPreferences.getInt(SCORE_PARTNER, 0)
//        set(partner) {
//            editor.putInt(SCORE_PARTNER, partner?)
//            editor.commit()
//        }
//
//    //事业指数
//    var career: Int?
//        get() = mSharedPreferences.getInt(SCORE_CAREER, 0)
//        set(career) {
//            editor.putInt(SCORE_CAREER, career?)
//            editor.commit()
//        }
//
//    //爱情指数
//    var love: Int?
//        get() = mSharedPreferences.getInt(SCORE_LOVE, 0)
//        set(love) {
//            editor.putInt(SCORE_LOVE, love?)
//            editor.commit()
//        }
//
//    //随机股票指数
//    var randomStock: Int?
//        get() = mSharedPreferences.getInt(SCORE_RANDOM_STOCK, 0)
//        set(random_stock) {
//            editor.putInt(SCORE_RANDOM_STOCK, random_stock?)
//            editor.commit()
//        }
//
//    //房价指数
//    var indexHouse: Int?
//        get() = mSharedPreferences.getInt(SCORE_INDEX_HOUSE, 0)
//        set(index_house) {
//            editor.putInt(SCORE_INDEX_HOUSE, index_house?)
//            editor.commit()
//        }
//
//    //股票指数
//    var indexStock: Int
//        get() = mSharedPreferences.getInt(SCORE_INDEX_STOCK, 0)
//        set(index_stock) {
//            editor.putInt(SCORE_INDEX_STOCK, index_stock)
//            editor.commit()
//        }
//
//    // 分数含金量
//    var purity: Int?
//        get() = mSharedPreferences.getInt(SCORE_PURITY, 0)
//        set(purity) {
//            editor.putInt(SCORE_PURITY, purity?)
//            editor.commit()
//        }
//
//    // 月净收益
//    var income: Int?
//        get() = mSharedPreferences.getInt(SCORE_INCOME, 0)
//        set(income) {
//            editor.putInt(SCORE_INCOME, income?)
//            editor.commit()
//        }
//
//    // 月净交际
//    var communicationMonthly: Int?
//        get() = mSharedPreferences.getInt(SCORE_COMMUNICATION_MONTHLY, 0)
//        set(communicationMonthly) {
//            editor.putInt(SCORE_COMMUNICATION_MONTHLY, communicationMonthly?)
//            editor.commit()
//        }
//
//    // 月净快乐
//    var happyMonthly: Int?
//        get() = mSharedPreferences.getInt(SCORE_HAPPY_MONTHLY, 0)
//        set(happyMonthly) {
//            editor.putInt(SCORE_HAPPY_MONTHLY, happyMonthly?)
//            editor.commit()
//        }
//
//    //股票 0/1
//    fun isStock(): Boolean {
//        return mSharedPreferences.getBoolean(GAME_STOCK, false)
//    }
//
//    fun setStock(isStock: Boolean) {
//        editor.putBoolean(GAME_STOCK, isStock)
//        editor.commit()
//    }
//
//    //职位 0/1
//    fun isPosition(): Boolean {
//        return mSharedPreferences.getBoolean(GAME_POSITION, false)
//    }
//
//    fun position = isPosition: Boolean) {
//        editor.putBoolean(GAME_POSITION, isPosition)
//        editor.commit()
//    }
//
//    //房产 0/1
//    fun isHouse(): Boolean {
//        return mSharedPreferences.getBoolean(GAME_HOUSE, false)
//    }
//
//    fun setHouse(isHouse: Boolean) {
//        editor.putBoolean(GAME_HOUSE, isHouse)
//        editor.commit()
//    }
//
//    //汽车 0/1
//    fun isCar(): Boolean {
//        return mSharedPreferences.getBoolean(GAME_CAR, false)
//    }
//
//    fun setCar(isCar: Boolean) {
//        editor.putBoolean(GAME_CAR, isCar)
//        editor.commit()
//    }
//
//    //伴侣 0/1
//    fun isPartner(): Boolean {
//        return mSharedPreferences.getBoolean(GAME_PARTNER, false)
//    }
//
//    fun setPartner(isPartner: Boolean) {
//        editor.putBoolean(GAME_PARTNER, isPartner)
//        editor.commit()
//    }
//
//    //彩票 0/1
//    var isLottery: Boolean
//        get() = mSharedPreferences.getBoolean(GAME_LOTTERY, false)
//        set(isLottery) {
//            editor.putBoolean(GAME_LOTTERY, isLottery)
//            editor.commit()
//        }
//
//    // YD 30
//    var yd: Int?
//        get() = mSharedPreferences.getInt(GAME_YD, 0)
//        set(yd) {
//            editor.putInt(GAME_YD, yd?)
//            editor.commit()
//        }
//
//    // BL 30
//    var bl: Int?
//        get() = mSharedPreferences.getInt(GAME_BL, 0)
//        set(bl) {
//            editor.putInt(GAME_BL, bl?)
//            editor.commit()
//        }
//
//    // TX 20
//    var ts: Int?
//        get() = mSharedPreferences.getInt(GAME_TS, 0)
//        set(ts) {
//            editor.putInt(GAME_TS, ts?)
//            editor.commit()
//        }
//
//    // PY 30
//    var py: Int?
//        get() = mSharedPreferences.getInt(GAME_PY, 0)
//        set(py) {
//            editor.putInt(GAME_PY, py?)
//            editor.commit()
//        }
//
//    // GJ 30
//    var gj: Int?
//        get() = mSharedPreferences.getInt(GAME_GJ, 0)
//        set(gj) {
//            editor.putInt(GAME_GJ, gj?)
//            editor.commit()
//        }
//
//    // CM 30
//    var cm: Int?
//        get() = mSharedPreferences.getInt(GAME_CM, 0)
//        set(cm) {
//            editor.putInt(GAME_CM, cm?)
//            editor.commit()
//        }
//
//    // CJ 30
//    var cj: Int?
//        get() = mSharedPreferences.getInt(GAME_CJ, 0)
//        set(cj) {
//            editor.putInt(GAME_CJ, cj?)
//            editor.commit()
//        }
//
//    // ZJ 30
//    var zj: Int?
//        get() = mSharedPreferences.getInt(GAME_ZJ, 0)
//        set(zj) {
//            editor.putInt(GAME_ZJ, zj?)
//            editor.commit()
//        }
//
//    // JB 20
//    var jb: Int?
//        get() = mSharedPreferences.getInt(GAME_JB, 0)
//        set(jb) {
//            editor.putInt(GAME_JB, jb?)
//            editor.commit()
//        }
//
//    // SW 20
//    var sw: Int?
//        get() = mSharedPreferences.getInt(GAME_SW, 0)
//        set(sw) {
//            editor.putInt(GAME_SW, sw?)
//            editor.commit()
//        }
//
//    // TY 30
//    var ty: Int?
//        get() = mSharedPreferences.getInt(GAME_TY, 0)
//        set(ty) {
//            editor.putInt(GAME_TY, ty?)
//            editor.commit()
//        }
//
//    // HJ 30
//    var hj: Int?
//        get() = mSharedPreferences.getInt(GAME_HJ, 0)
//        set(hj) {
//            editor.putInt(GAME_HJ, hj?)
//            editor.commit()
//        }
//    var partnerStory: Int?
//        get() = mSharedPreferences.getInt(PARTNER_STORY, 0)
//        set(partner_story) {
//            editor.putInt(PARTNER_STORY, partner_story?)
//            editor.commit()
//        }
//    var partnerXy: Int?
//        get() = mSharedPreferences.getInt(PARTNER_XY, 0)
//        set(partner_xy) {
//            editor.putInt(PARTNER_XY, partner_xy?)
//            editor.commit()
//        }
//
//    //    public Integer getPartnerSs() {
//    //        return mSharedPreferences.getInt(PARTNER_SS, 0);
//    //    }
//    //
//    //    public void setPartnerSs(Integer partner_ss) {
//    //        editor.putInt(PARTNER_SS, partner_ss);
//    //        editor.commit();
//    //    }
//    //    public Integer getPartnerAc() {
//    //        return mSharedPreferences.getInt(PARTNER_AC, 0);
//    //    }
//    //
//    //    public void setPartnerAc(Integer partner_ac) {
//    //        editor.putInt(PARTNER_AC, partner_ac);
//    //        editor.commit();
//    //    }
//    var partnerZj: Int?
//        get() = mSharedPreferences.getInt(PARTNER_ZJ, 0)
//        set(partner_zj) {
//            editor.putInt(PARTNER_ZJ, partner_zj?)
//            editor.commit()
//        }
//
//
//    fun getPartnerSn(): Int {
//        return mSharedPreferences.getInt(PARTNER_SN, 0)
//    }
//
//    fun setPartnerSn(partner_sn: Int?) {
//        editor.putInt(PARTNER_SN, partner_sn?)
//        editor.commit()
//    }
//
//    fun getPartnerSnTime(): Int {
//        return mSharedPreferences.getInt(PARTNER_SN_TIME, 0)
//    }
//
//    fun setPartnerSnTime(partner_sn_time: Int?) {
//        editor.putInt(PARTNER_SN_TIME, partner_sn_time?)
//        editor.commit()
//    } //    public Integer getPartnerFy() {
//
//    //        return mSharedPreferences.getInt(PARTNER_FY, 0);
//    //    }
//    //
//    //    public void setPartnerFy(Integer partner_fy) {
//    //        editor.putInt(PARTNER_FY, partner_fy);
//    //        editor.commit();
//    //    }
//    companion object {
//        private const val SCORE_BOOLEAN = "score_boolean" //是否有记录
//        private const val SCORE_HEALTHY = "score_healthy" //健康
//        private const val SCORE_MONEY = "score_money" //金钱
//        private const val SCORE_ABILITY = "score_ability" //能力
//        private const val SCORE_EXPERIENCE = "score_experience" //经验
//        private const val SCORE_HAPPY = "score_happy" //快乐
//        private const val SCORE_MORALITY = "score_morality" //道德
//        private const val SCORE_COMMUNICATION = "score_communication" //交际
//        private const val SCORE_MONTH = "score_month" //剩余月
//        private const val SCORE_TIME = "score_time" //剩余时间
//        private const val SCORE_STOCK = "score_stock" //股票
//        private const val SCORE_POSITION = "score_position" //职位
//        private const val SCORE_HOUSE = "score_house" //房产
//        private const val SCORE_CAR = "score_car" //汽车
//        private const val SCORE_PARTNER = "score_partner" //伴侣
//        private const val SCORE_CAREER = "score_career" //事业指数
//        private const val SCORE_LOVE = "score_love" //爱情指数
//        private const val SCORE_RANDOM_STOCK = "score_random_stock" //随机股票指数
//        private const val SCORE_INDEX_HOUSE = "score_index_house" //房价指数
//        private const val SCORE_INDEX_STOCK = "score_index_stock" //股票指数
//        private const val SCORE_PURITY = "score_purity" //分数含金量
//        private const val SCORE_INCOME = "score_income" //月净收益
//        private const val SCORE_COMMUNICATION_MONTHLY = "score_communication_monthly" //月净交际
//        private const val SCORE_HAPPY_MONTHLY = "score_happy_monthly" //月净快乐
//        private const val GAME_STOCK = "game_stock" //股票 0/1
//        private const val GAME_POSITION = "game_position" //职位 0/1
//        private const val GAME_HOUSE = "game_house" //房产 0/1
//        private const val GAME_CAR = "game_car" //汽车 0/1
//        private const val GAME_PARTNER = "game_partner" //伴侣 0/1
//        private const val GAME_LOTTERY = "game_lottery" //彩票 0/1
//        private const val GAME_YD = "game_yd" //30
//        private const val GAME_BL = "game_bl" //30
//        private const val GAME_TS = "game_ts" //20
//        private const val GAME_PY = "game_py" //30
//        private const val GAME_GJ = "game_gj" //30
//        private const val GAME_CM = "game_cm" //30
//        private const val GAME_CJ = "game_cj" //30
//        private const val GAME_ZJ = "game_zj" //30
//        private const val GAME_JB = "game_jb" //20
//        private const val GAME_SW = "game_sw" //20
//        private const val GAME_TY = "game_tx" //30
//        private const val GAME_HJ = "game_hj" //30
//        private const val PARTNER_STORY = "partner_story" //当前剧情人物
//        private const val PARTNER_XY = "partner_xy" //小樱
//
//        //    private static final String PARTNER_SS = "partner_ss"; //施施
//        //    private static final String PARTNER_AC = "partner_ac"; //阿婵
//        private const val PARTNER_ZJ = "partner_zj" //昭君
//        private const val PARTNER_SN = "partner_sn" //十娘
//        private const val PARTNER_SN_TIME = "partner_sn_time" //十娘
//        private lateinit var editor: SharedPreferences.Editor
//    }
//
//    init {
//        mSharedPreferences = context.getSharedPreferences(
//            name,
//            Context.MODE_PRIVATE
//        )
//        editor = mSharedPreferences.edit()
//    }
//}