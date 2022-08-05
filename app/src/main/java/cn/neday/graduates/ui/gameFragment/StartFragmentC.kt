package cn.neday.graduates.ui.gameFragment

import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import cn.neday.graduates.MusicConductor
import cn.neday.graduates.R
import cn.neday.graduates.databinding.FragmentStartCBinding
import cn.neday.graduates.fragment.BaseBindingFragment
import cn.neday.graduates.repository.Score
import cn.neday.graduates.repository.Settings

class StartFragmentC : BaseBindingFragment<FragmentStartCBinding>() {
    private var jtbg = 0
    private var xgqx = 0
    private var dxzy = 0
    private var gzfx = 0
    private var yxnd = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        binding.btnGoGame.setOnClickListener { v: View ->
            playHeartbeatAnimation(v)
            setSharedUtil()
            startGame()
        }
        binding.rgJtbg.setOnCheckedChangeListener { arg0: RadioGroup, _: Int ->
            when (arg0.checkedRadioButtonId) {
                R.id.rb_jtbg_0 -> {
                    jtbg = 0
                    binding.tvJtbg.setText(R.string.jtbg_0)
                }
                R.id.rb_jtbg_1 -> {
                    jtbg = 1
                    binding.tvJtbg.setText(R.string.jtbg_1)
                }
                R.id.rb_jtbg_2 -> {
                    jtbg = 2
                    binding.tvJtbg.setText(R.string.jtbg_2)
                }
                R.id.rb_jtbg_3 -> {
                    jtbg = 3
                    binding.tvJtbg.setText(R.string.jtbg_3)
                }
            }
        }
        binding.rgXgqx.setOnCheckedChangeListener { arg0: RadioGroup, _: Int ->
            when (arg0.checkedRadioButtonId) {
                R.id.rb_xgqx_0 -> {
                    xgqx = 0
                    binding.tvXgqx.setText(R.string.xgqx_0)
                }
                R.id.rb_xgqx_1 -> {
                    xgqx = 1
                    binding.tvXgqx.setText(R.string.xgqx_1)
                }
                R.id.rb_xgqx_2 -> {
                    xgqx = 2
                    binding.tvXgqx.setText(R.string.xgqx_2)
                }
                R.id.rb_xgqx_3 -> {
                    xgqx = 3
                    binding.tvXgqx.setText(R.string.xgqx_3)
                }
            }
        }

        binding.rgDxzy.setOnCheckedChangeListener { arg0: RadioGroup, _: Int ->
            when (arg0.checkedRadioButtonId) {
                R.id.rb_dxzy_0 -> {
                    dxzy = 0
                    binding.tvDxzy.setText(R.string.dxzy_0)
                }
                R.id.rb_dxzy_1 -> {
                    dxzy = 1
                    binding.tvDxzy.setText(R.string.dxzy_1)
                }
                R.id.rb_dxzy_2 -> {
                    dxzy = 2
                    binding.tvDxzy.setText(R.string.dxzy_2)
                }
                R.id.rb_dxzy_3 -> {
                    dxzy = 3
                    binding.tvDxzy.setText(R.string.dxzy_3)
                }
            }
        }
        binding.rgGzfx.setOnCheckedChangeListener { arg0: RadioGroup, _: Int ->
            when (arg0.checkedRadioButtonId) {
                R.id.rb_gzfx_0 -> {
                    gzfx = 0
                    binding.tvGzfx.setText(R.string.gzfx_0)
                }
                R.id.rb_gzfx_1 -> {
                    gzfx = 1
                    binding.tvGzfx.setText(R.string.gzfx_1)
                }
                R.id.rb_gzfx_2 -> {
                    gzfx = 2
                    binding.tvGzfx.setText(R.string.gzfx_2)
                }
                R.id.rb_gzfx_3 -> {
                    gzfx = 3
                    binding.tvGzfx.setText(R.string.gzfx_3)
                }
            }
        }

        binding.rgYxnd.setOnCheckedChangeListener { arg0: RadioGroup, _: Int ->
            when (arg0.checkedRadioButtonId) {
                R.id.rb_yxnd_0 -> {
                    yxnd = 0
                    binding.tvYxnd.setText(R.string.yxnd_0)
                }
                R.id.rb_yxnd_1 -> {
                    yxnd = 1
                    binding.tvYxnd.setText(R.string.yxnd_1)
                }
                R.id.rb_yxnd_2 -> {
                    yxnd = 2
                    binding.tvYxnd.setText(R.string.yxnd_2)
                }
                R.id.rb_yxnd_3 -> {
                    yxnd = 3
                    binding.tvYxnd.setText(R.string.yxnd_3)
                }
            }
        }
    }

    private fun startGame() {
        MusicConductor.playSound(R.raw.button_0)
        Settings.isPlaying = true
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(
                R.id.fragment_game,
                GameFragment()
            )?.commit()
    }

    private fun setSharedUtil() {
        when (jtbg) {
            0 -> {
                Score.ability = Score.ability.plus(300)
                Score.experience = Score.experience.plus(300)
                Score.morality = Score.morality.plus(100)
            }
            1 -> {
                Score.money = Score.money.plus(20000)
                Score.healthy = Score.healthy.plus(50)
                Score.happy = Score.happy.plus(50)
            }
            2 -> {
                Score.money = Score.money.plus(100000)
                Score.communication = Score.communication.plus(50)
            }
            3 -> {
                Score.money = Score.money.plus(500000)
                Score.purity = Score.purity.minus(20)
            }
        }
        when (xgqx) {
            0 -> {
                Score.healthy = Score.healthy.plus(50)
                Score.happy = Score.happy.plus(50)
            }
            1 -> Score.communication = Score.communication.plus(50)
            2 -> Score.morality = Score.morality.plus(50)
            3 -> {
                Score.ability = Score.ability.plus(200)
                Score.experience = Score.experience.plus(200)
            }
        }
        when (dxzy) {
            0 -> {
                Score.ability = Score.ability.plus(200)
                Score.experience = Score.experience.plus(200)
            }
            1 -> Score.happy = Score.happy.plus(50)
            2 -> Score.communication = Score.communication.plus(50)
            3 -> Score.healthy = Score.healthy.plus(50)
        }
        when (gzfx) {
            0 -> {
                Score.ability = Score.ability.plus(200)
                Score.experience = Score.experience.plus(200)
            }
            1 -> Score.happy = Score.happy.plus(50)
            2 -> Score.communication = Score.communication.plus(50)
            3 -> Score.career = Score.career.plus(1)
        }
        when (yxnd) {
            0 -> Score.purity = 0
            1 -> {
                Score.career = Score.career.plus(1)
                Score.love = Score.love.plus(1)
                Score.purity = Score.purity.minus(10)
            }
            2 -> {
                Score.money = Score.money.plus(1000000)
                Score.ability = Score.ability.plus(1000)
                Score.experience = Score.experience.plus(1000)
                Score.happy = Score.experience.plus(200)
                Score.morality = Score.experience.plus(200)
                Score.communication = Score.experience.plus(200)
                Score.purity = Score.purity.minus(40)
            }
            3 -> {
                Score.money = Score.money.plus(9998000)
                Score.ability = Score.ability.plus(4900)
                Score.experience = Score.experience.plus(4900)
                Score.happy = Score.experience.plus(800)
                Score.morality = Score.experience.plus(800)
                Score.communication = Score.experience.plus(900)
                Score.purity = Score.purity.minus(100)
            }

        }
    }
}