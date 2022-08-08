package cn.neday.graduates.fragment

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import cn.neday.graduates.databinding.FragmentOverBinding
import cn.neday.graduates.repository.Score
import cn.neday.graduates.taptap.Leaderboard.update
import com.dylanc.longan.doOnClick
import com.dylanc.longan.toast
import java.io.FileOutputStream
import java.io.IOException

class OverFragment : BaseBindingFragment<FragmentOverBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setValue()
    }

    private fun setValue() {
        binding.tvHealthy.text = Score.healthy.toString()
        binding.tvMoney.text = Score.money.toString()
        binding.tvAbility.text = Score.ability.toString()
        binding.tvExperience.text = Score.experience.toString()
        binding.tvHappy.text = Score.happy.toString()
        binding.tvMorality.text = Score.morality.toString()
        binding.tvCommunication.text = Score.communication.toString()

        binding.tvPosition.text = mPositionString[Score.position]
        binding.tvCar.text = mCarString[Score.car]
        binding.tvHouse.text = mHouseString[Score.house]
        binding.tvPartner.text = mPartnerString[Score.partner]

        binding.tvOver0.text = makeScoreInfo()
        binding.tvOver1.text = "总分$score"

        binding.tvOver.doOnClick { uploadScore() }
    }

    // 总分计算公式：健康*1.5+金钱分+经验/4+能力/4+快乐+道德*2+交际/魅力*1.5+职位分+车子分+房子分+女/男友分
    private val score: Int
        get() = (Score.healthy * 1.5
                + moneyScore
                + Score.ability / 4
                + Score.experience / 4
                + Score.happy
                + Score.morality / 2
                + Score.communication * 1.5
                + (Score.position + 1) * 1000
                + (Score.car xor 2 * 100)
                + (Score.house xor 2 * 100)
                + (if (Score.partner == 0) 0 else 100000)
                + (Score.love + Score.career) * 10000).toInt()

    // 金钱分值
    // 1亿之内，金钱分=金钱/1000
    // 超过1亿  金钱分=100000+（金钱-100000000）/10000
    private val moneyScore: Int
        get() {
            val money = Score.money
            val mMoneyScore: Int = if (money > 100000000) {
                100000 + (money - 100000000) / 10000
            } else {
                money / 1000
            }
            return mMoneyScore
        }

    /**
     * 上传分数
     * 使用上传分数功能时需要登录
     */
    private fun uploadScore() {
        update()
    }

    // 保存到sdcard
    private fun savePic(b: Bitmap, strFileName: String) {
        val fos: FileOutputStream
        try {
            fos = FileOutputStream(strFileName)
            b.compress(Bitmap.CompressFormat.PNG, 90, fos)
            fos.flush()
            fos.close()
            toast("保存成功")
            // KTPlay.shareImageToKT("sdcard/xx.png", "大家来看看我的黄金岁月哈～～～");
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 转换分数信息数据为普通文本
     */
    private fun makeScoreInfo(): String {
        return ("你现在是" + mPositionString[Score.position] + "。"
                + mHouseString[Score.house] + "，"
                + mCarString[Score.car] + "。"
                + mPartnerString[Score.partner] + "。"
                + (if (Score.love > 10) "你的真诚与关爱打动了你的女友，和她一起走进了婚姻的殿堂。" else "在你三十岁之际，希望你找到了真爱，能和她共赴婚姻的殿坛。")
                + (if (Score.healthy > 500) "你的健康程度一般，大病没有，小病不断。 你的健康程度很差，身体弱不禁风，经常感冒，经常熬夜和饮食起居不规律带来身体的许多疾病。   你的健康程度很差，三十岁就开始秃顶掉头发，身体也已将发福，高血压高血脂带来身体的许多疾病。" else "你非常强壮，健康状态超级棒！")
                + (if (Score.healthy > 800) "要注意多运动和有规律的生活。" else "你很注意自己的健康，大量系统的健身运动加上平时的良好生活习惯造就了你运动员般的体魄！")
                + (if (Score.healthy > 5) "要注意养成健康的生活方式，多运动提高体质同时保持良好的心态。  你非常注意消费和积累的平衡，通过勤奋工作和不断学习提升自己以谋求更好的待遇和职位，" else "你平时很注意消费和积累的平衡，由于勤奋工作获得升职加薪，")
                + (if (Score.career > 10) "你平时出手大方，花钱也没什么计划，投资也可能碰到失败，所以经济方面存款不多，仅够糊口。 好运气加上大胆的投资，经济方面可以说是非常富有，钱对你来说只是个符号。好运气加上大胆的投资，经济方面可以列入富豪阶层，" else "投资方面颇有头脑，经济方面可以过上小资的生活。")
                + (if (Score.experience > 4000) "你非常注意消费和积累的平衡，通过勤奋工作和不断学习提升自己以谋求更好的待遇和职位，好运气加上大胆的投资，经济方面可以说是辉煌，年纪轻轻就成为亿万富翁！ 你平时也注意消费和积累的平衡，但总是心有余力不足，你埋怨自己不太走运，所以经济方面存款不多，只能温饱。" else "你通过勤奋工作、健身运动、阅读大量书籍、参加众多的学习培训来提升自己，")
                + (if (Score.ability > 4000) "能力方面比较强，可以独当一面，开拓守成，经验方面比较丰富。" else "在能力方面非常强，世界上几乎没有什么事情你办不了，经验方面非常丰富，")
                + (if (Score.position > 4) "也担任经理一级的职务" else "就算是总经理的位子你也可以胜任。)")
                + (if (Score.position > 8) " 虽然你还在替别人打工，" else "你通过努力扩大人脉和积累资本，终于创立了属于自己的公司。")
                + (if (Score.healthy > 5) "" else "但你对自己的将来还是充满期待。你的生活和工作平衡处理得非常好，")
                + (if (Score.happy > 800) "长时间和大工作量的工作学习使你的生活压力很大，经常愁眉不展，觉得整个世界都是灰色的。为了避免你以后忧郁过度而自杀，一定要注意保持快乐的心态。" else "特喜欢玩和喜欢热闹，每天都开开心心的，笑容常在，并给周围的人带来欢乐，")
                + (if (Score.morality > 500) "你平时喜欢赚小便宜，在对待金钱和欲望的问题上不能很好的把握自己，当然，你有时间也会抽空回家看看父母。" else "所有的人都羡慕和喜欢你！你为人非常正直，酒色财气几乎不沾，对自己欲望的控制和把握做得非常好，")
                + (if (Score.morality > 800) "你为人比较正直，能很好的把握和控制自己的欲望，还时常献一下爱心。经常回家看看，对父母还是能尽到必要的孝道。" else "你为人比较正直，能很好的把握和控制自己的欲望，还时常献一下爱心。 对父母则孝敬有加，富有爱心，是个人人称道的好人！你在人际关系方面可以说得上是个中高手，情商非常高哦！")
                + (if (Score.communication > 800) "你的人际关系方面处理得很糟糕，身边没有几个朋友，经常是行单影孤的。你应该尝试着大方一点，毕竟在这个社会中有朋友才能相互温暖。  你在人际处理方面一般，来来去去也就那么几个酒肉朋友。你可以多参加一点集体活动，增加自己的人际接触面" else "有众多朋友同事的帮助，你如鱼得水，左右逢源。可谓是人见人爱，花见花开，车见车载。")
                + "根据你的各项属性和各项资产最后得出你的综合成就总分为：$score"
                + "这段黄金岁月的含金量为：56 %")
        //       健康 1598 金钱+股票市值 85215809 能力 6873 经验 6215 快乐 1606
// 道德 821 交际 851 海外驻华公司高层老总 你有一辆时尚拉风跑车 你有城区超豪华别墅
// 你的女友是十娘 婚姻成功 创业成功 总分 107909 岁月含金量 97%
// 游戏难度：现实最难 游戏版本V2.5
    }

    companion object {
        private val mPositionString = arrayOf(
            "国内中小公司基层员工", "国内知名公司基层员工", "海外驻华公司基层员工",
            "国内中小公司基层主管", "国内知名公司基层主管", "海外驻华公司基层主管",
            "国内中小公司中层经理", "国内知名公司中层经理", "海外驻华公司中层经理",
            "国内中小公司高层老总", "国内知名公司高层老总", "海外驻华公司高层老总"
        )
        private val mCarString = arrayOf(
            "你还没有买车", "你有一辆小型节油低档车", "你有一辆经济实用中档车",
            "你有一辆豪华舒适中档车", "你有一辆超豪华享受高档车", "你有一辆时尚拉风跑车"
        )
        private val mHouseString = arrayOf(
            "你还没有买房", "你有1室1厅的房子", "你有2室1厅的房子", "你有2室2厅的房子", "你有3室1厅的房子", "你有3室2厅的房子", "你有4室2厅的房子",
            "你有市郊豪华小别墅", "你有城区超豪华别墅"
        )
        private val mPartnerString = arrayOf(
            "你还没有女朋友", "你的女友是施施", "你的女友是阿禅",
            "你的女友是昭君", "你的女友是玉环", "你的女友是圆圆", "你的女友是香香",
            "你的女友是十娘", "你的女友是小小", "你的女友是飞燕", "你的女友是莺莺"
        )

        // 获取指定Activity的截屏，保存到png文件
        private fun takeScreenShot(activity: Activity): Bitmap {
            // View是你需要截图的View
            val view = activity.window.decorView
            view.isDrawingCacheEnabled = true
            view.buildDrawingCache()
            val b1 = view.drawingCache

            // 获取状态栏高度
            val frame = Rect()
            activity.window.decorView.getWindowVisibleDisplayFrame(frame)
            val statusBarHeight = frame.top

            // 获取屏幕长和高
            val width = activity.windowManager.defaultDisplay.width
            val height = activity.windowManager.defaultDisplay.height
            // 去掉标题栏
            val b = Bitmap.createBitmap(
                b1, 0, statusBarHeight, width, height
                        - statusBarHeight
            )
            view.destroyDrawingCache()
            return b
        }
    }
}