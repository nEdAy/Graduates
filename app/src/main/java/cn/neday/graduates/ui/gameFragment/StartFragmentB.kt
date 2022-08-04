package cn.neday.graduates.ui.gameFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.neday.graduates.MusicConductor.playSound
import cn.neday.graduates.R
import cn.neday.graduates.databinding.FragmentStartBBinding
import cn.neday.graduates.fragment.BaseBindingFragment
import cn.neday.graduates.view.NiftyDialogBuilder
import com.dylanc.longan.doOnClick

class StartFragmentB : BaseBindingFragment<FragmentStartBBinding>() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpViews()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setUpViews() {
        binding.btnForward.doOnClick { nextStart() }
        binding.rbStart0.doOnClick {
            showImageChooseViewDialog(
                R.string.start_b_0,
                R.string.start_b_0_0,
                R.mipmap.zgz,
                object : Button1onClickListener {
                    override fun onClick() {

                    }
                },
                object : Button2onClickListener {
                    override fun onClick() {
                        showTextViewDialog(
                            R.string.start_b_0,
                            R.string.start_b_0_1,
                            object : Button2onClickListener {
                                override fun onClick() {
                                    nextStart()
                                }
                            })
                    }
                })
        }
        binding.rbStart1.doOnClick {
            showImageChooseViewDialog(
                R.string.start_b_1,
                R.string.start_b_1_0,
                R.mipmap.zjcy,
                object : Button1onClickListener {
                    override fun onClick() {

                    }
                },
                object : Button2onClickListener {
                    override fun onClick() {
                        showTextViewDialog(
                            R.string.start_b_1,
                            R.string.start_b_1_1,
                            object : Button2onClickListener {
                                override fun onClick() {

                                }
                            })
                    }
                })
        }
        binding.rbStart2.doOnClick {
            showImageChooseViewDialog(
                R.string.start_b_2,
                R.string.start_b_2_0,
                R.mipmap.ky,
                object : Button1onClickListener {
                    override fun onClick() {

                    }
                },
                object : Button2onClickListener {
                    override fun onClick() {
                        showTextViewDialog(
                            R.string.start_b_2,
                            R.string.start_b_2_1,
                            object : Button2onClickListener {
                                override fun onClick() {

                                }
                            })
                    }
                })
        }
        binding.rbStart3.doOnClick {
            showImageChooseViewDialog(
                R.string.start_b_3,
                R.string.start_b_3_0,
                R.mipmap.gwy,
                object : Button1onClickListener {
                    override fun onClick() {

                    }
                },
                object : Button2onClickListener {
                    override fun onClick() {
                        showTextViewDialog(
                            R.string.start_b_3,
                            R.string.start_b_3_1,
                            object : Button2onClickListener {
                                override fun onClick() {

                                }
                            })
                    }
                })
        }
        binding.rbStart4.doOnClick {
            showImageChooseViewDialog(
                R.string.start_b_4,
                R.string.start_b_4_0,
                R.mipmap.cg,
                object : Button1onClickListener {
                    override fun onClick() {

                    }
                },
                object : Button2onClickListener {
                    override fun onClick() {
                        showTextViewDialog(
                            R.string.start_b_4,
                            R.string.start_b_4_1,
                            object : Button2onClickListener {
                                override fun onClick() {

                                }
                            })
                    }
                })
        }
    }

    private fun nextStart() {
        playSound(R.raw.button_0)
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(
                R.id.fragment_game,
                StartFragmentC()
            )?.addToBackStack(null)
            ?.commit()
    }

    private fun showImageChooseViewDialog(
        title: Int,
        message: Int,
        iv: Int,
        Button1onClickListener: Button1onClickListener,
        Button2onClickListener: Button2onClickListener
    ) {
        val dialogBuilder = NiftyDialogBuilder.getInstance(activity)
        dialogBuilder.withTitle(title)
            .withMessage(message).withImageView(iv).isCancelable(true)
            .withDuration(500).withButtonCancle().withButtonOk()
            .setButtonCancleClick {
                Button1onClickListener.onClick()
                dialogBuilder.closeDialog(dialogBuilder)
            }
            .setButtonOk {
                Button2onClickListener.onClick()
                dialogBuilder.closeDialog(dialogBuilder)
            }.show()
    }

    private fun showTextViewDialog(
        title: Int,
        message: Int,
        Button2onClickListener: Button2onClickListener
    ) {
        val dialogBuilder = NiftyDialogBuilder
            .getInstance(activity)
        dialogBuilder.withTitle(title)
            .withMessage(message).isCancelable(false)
            .withDuration(500).withButtonOk()
            .setButtonOk { v: View? ->
                dialogBuilder.closeDialog(dialogBuilder)
                Button2onClickListener.onClick()
            }.show()
    }

    private interface Button1onClickListener {
        fun onClick()
    }

    private interface Button2onClickListener {
        fun onClick()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private val instance = StartFragmentB()
        fun newInstance(): StartFragmentB {
            return instance
        }
    }
}