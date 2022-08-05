package cn.neday.graduates.ui.gameFragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import cn.neday.graduates.MusicConductor
import cn.neday.graduates.R
import cn.neday.graduates.databinding.FragmentLotteryBinding
import cn.neday.graduates.fragment.BaseBindingFragment
import cn.neday.graduates.repository.Score
import cn.neday.graduates.view.FlakeView
import com.dylanc.longan.doOnClick
import com.dylanc.longan.toast
import java.util.*

/**
 * 彩票
 */
class LotteryFragment : BaseBindingFragment<FragmentLotteryBinding>() {
    // 金币掉落动画的主体动画
    private var flakeView: FlakeView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        flakeView = FlakeView(activity)
        val random = Random()
        val n = random.nextInt(1000) + 1
        binding.btnLottery.doOnClick {
            Score.isLottery = true
            Score.money = Score.money - 100
            if (n == 1) {
                MusicConductor.playSound(R.raw.lottery)
                showPopWindows(binding.btnLottery, "一等奖", 5000000)
            } else if (n == 11 || n == 12) {
                MusicConductor.playSound(R.raw.lottery)
                showPopWindows(binding.btnLottery, "二等奖", 500000)
            } else if (11 >= n && n <= 20) {
                MusicConductor.playSound(R.raw.lottery)
                showPopWindows(binding.btnLottery, "三等奖", 50000)
            } else {
                MusicConductor.playSound(R.raw.money)
                toast("您本月没有中奖，欢迎下月再来~")
                popBackStack()
            }
        }
    }

    private fun setUpViews() {
        binding.ivBack.doOnClick { popBackStack() }
    }

    private fun showPopWindows(v: View?, lotteryStr: String, money: Int): PopupWindow {
        Score.money = Score.money.plus(money)
        popBackStack()
        val view = activity?.layoutInflater?.inflate(R.layout.view_login_reward, null)
        val tvTips = view?.findViewById<TextView>(R.id.tv_tip)
        val moneyView = view?.findViewById<TextView>(R.id.tv_money)
        tvTips?.text = "恭喜您中得$lotteryStr,奖金$money,请领取！"
        moneyView?.text = money.toString()
        val container = view?.findViewById<LinearLayout>(R.id.container)
        // 将flakeView 添加到布局中
        container?.addView(flakeView)
        // 设置背景
        activity?.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        // 设置同时出现在屏幕上的金币数量  建议64以内 过多会引起卡顿
        flakeView?.addFlakes(8)
        // 绘制的类型
        flakeView?.setLayerType(View.LAYER_TYPE_NONE, null)
        val pop = PopupWindow(
            view,
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        view?.findViewById<View>(R.id.btn_ikow)?.setOnClickListener {
            container?.removeAllViews()
            pop?.dismiss()
        }
        val dw = ColorDrawable(resources.getColor(R.color.color_half_transparent))
        pop?.setBackgroundDrawable(dw)
        pop?.isOutsideTouchable = true
        pop?.isFocusable = true
        pop?.showAtLocation(v, Gravity.CENTER, 0, 0)
        val player = MediaPlayer.create(activity, R.raw.shake)
        player.start()
        return pop
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private val instance = LotteryFragment()

        @JvmStatic
        fun newInstance(): LotteryFragment {
            return instance
        }
    }
}