package cn.neday.graduates.fragment.main

import android.os.Bundle
import android.view.View
import cn.neday.graduates.R
import cn.neday.graduates.databinding.FragmentSettingsBinding
import cn.neday.graduates.doOnClickWithSound
import cn.neday.graduates.fragment.BaseBindingFragment
import cn.neday.graduates.repository.Settings
import com.dylanc.longan.doOnClick

class SettingsFragment : BaseBindingFragment<FragmentSettingsBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        binding.btnBack.doOnClick { popBackStack() }
        if (Settings.isAllowMusicEnable) {
            binding.ivOpenMusic.visibility = View.VISIBLE
            binding.ivCloseMusic.visibility = View.INVISIBLE
        } else {
            binding.ivOpenMusic.visibility = View.INVISIBLE
            binding.ivCloseMusic.visibility = View.VISIBLE
        }
        if (Settings.isAllowSoundEnable) {
            binding.ivOpenSound.visibility = View.VISIBLE
            binding.ivCloseSound.visibility = View.INVISIBLE
        } else {
            binding.ivOpenSound.visibility = View.INVISIBLE
            binding.ivCloseSound.visibility = View.VISIBLE
        }
        setListener()
    }

    private fun setListener() {
        binding.btnAbont.doOnClickWithSound {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_main, AboutFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
        binding.btnAbont.doOnClickWithSound {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_main, SuggestFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }

//    override fun onClick(v: View) {
//        val dialogBuilder: NiftyDialogBuilder = NiftyDialogBuilder.getInstance(activity)
//        when (v.id) {
//            R.id.rl_switch_music -> {
//                MusicConductor.playSound(R.raw.button_1)
//                if (binding.ivOpenMusic.visibility == View.VISIBLE) {
//                    iv_open_music.setVisibility(View.INVISIBLE)
//                    iv_close_music.setVisibility(View.VISIBLE)
//                    ThisApplication.setAllowMusicEnable(false)
//                } else {
//                    iv_open_music.setVisibility(View.VISIBLE)
//                    iv_close_music.setVisibility(View.INVISIBLE)
//                    ThisApplication.setAllowMusicEnable(true)
//                }
//                onActivityCreated(null)
//            }
//            R.id.rl_switch_sound -> {
//                MusicConductor.playSound(R.raw.button_1)
//                if (iv_open_sound.getVisibility() == View.VISIBLE) {
//                    iv_open_sound.setVisibility(View.INVISIBLE)
//                    iv_close_sound.setVisibility(View.VISIBLE)
//                    ThisApplication.setAllowSoundEnable(false)
//                } else {
//                    iv_open_sound.setVisibility(View.VISIBLE)
//                    iv_close_sound.setVisibility(View.INVISIBLE)
//                    ThisApplication.setAllowSoundEnable(true)
//                }
//                onActivityCreated(null)
//            }
////            R.id.rl_text_phone -> {
////                MusicConductor.playSound(R.raw.button_0)
////                dialogBuilder.withTitle("设置手机号")
////                    .withMessage(null).withEditText(InputType.TYPE_CLASS_PHONE).isCancelable(true)
////                    .withDuration(500).withButtonCancle().withButtonOk()
////                    .setButtonCancleClick(View.OnClickListener { v14: View? -> dialogBuilder.getDismiss() })
////                    .setButtonOk(
////                        View.OnClickListener { v13: View? ->
////                            val phone: String = NiftyDialogBuilder.et_player.getText().toString()
////                            if (RegexUtils.isMobileExact(phone)) {
////                                mSharedSettingsUtil.setUserPhone(phone)
////                                dialogBuilder.dismiss()
////                                onActivityCreated(null)
////                            } else {
////                                toast("手机号不合法，请确认后重新输入！")
////                            }
////                        }).show()
////            }
////            R.id.rl_text_nick -> {
////                MusicConductor.playSound(R.raw.button_0)
////                dialogBuilder.withTitle("设置昵称")
////                    .withMessage(null).withEditText(InputType.TYPE_CLASS_TEXT).isCancelable(true)
////                    .withDuration(500).withButtonCancle().withButtonOk()
////                    .setButtonCancleClick(View.OnClickListener { v12: View? -> dialogBuilder.getDismiss() })
////                    .setButtonOk(
////                        View.OnClickListener { v1: View? ->
////                            val nickname: String = NiftyDialogBuilder.et_player.getText().toString()
////                            mSharedSettingsUtil.setUserNick(nickname)
////                            dialogBuilder.dismiss()
////                            onActivityCreated(null)
////                        }).show()
////            }
////            R.id.btn_splash -> {
////                MusicConductor.playSound(R.raw.button_0)
////                mSharedSettingsUtil.cleanSharedPreference()
////                onActivityCreated(null)
////            }
//           
//            R.id.btn_suggent -> {
//                MusicConductor.playSound(R.raw.button_0)
//               
//            }
//            R.id.btn_update -> {}
//            
//        }
//    }
}