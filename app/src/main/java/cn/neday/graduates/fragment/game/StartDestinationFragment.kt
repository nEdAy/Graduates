package cn.neday.graduates.fragment.game

import android.os.Bundle
import android.view.View
import cn.neday.graduates.MusicConductor.playSound
import cn.neday.graduates.R
import cn.neday.graduates.databinding.FragmentStartDestinationBinding
import cn.neday.graduates.fragment.BaseBindingFragment
import cn.neday.graduates.view.sheets.Info
import com.dylanc.longan.doOnClick

class StartDestinationFragment : BaseBindingFragment<FragmentStartDestinationBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.rbStart0.doOnClick {
            Info.showDialog(R.string.start_b_0, R.string.start_b_0_0, R.drawable.zgz) {
                Info.showDialog(R.string.start_b_0, R.string.start_b_0_1) {
                    nextStart()
                }
            }
        }
        binding.rbStart1.doOnClick {
            Info.showDialog(R.string.start_b_1, R.string.start_b_1_0, R.drawable.zjcy) {
                Info.showDialog(R.string.start_b_1, R.string.start_b_1_1) {
                    nextStart()
                }
            }
        }
        binding.rbStart2.doOnClick {
            Info.showDialog(R.string.start_b_2, R.string.start_b_2_0, R.drawable.ky) {
                Info.showDialog(R.string.start_b_2, R.string.start_b_2_1) {
                    nextStart()
                }
            }
        }
        binding.rbStart3.doOnClick {
            Info.showDialog(R.string.start_b_3, R.string.start_b_3_0, R.drawable.gwy) {
                Info.showDialog(R.string.start_b_3, R.string.start_b_3_1) {
                    nextStart()
                }
            }
        }
        binding.rbStart4.doOnClick {
            Info.showDialog(R.string.start_b_4, R.string.start_b_4_0, R.drawable.cg) {
                Info.showDialog(R.string.start_b_4, R.string.start_b_4_1) {
                    nextStart()
                }
            }
        }
    }

    private fun nextStart() {
        playSound(R.raw.button_0)
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(
                R.id.fragment_game,
                StartInformationFragment()
            )?.addToBackStack(null)
            ?.commit()
    }
}