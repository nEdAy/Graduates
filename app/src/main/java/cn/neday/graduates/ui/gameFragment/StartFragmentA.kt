package cn.neday.graduates.ui.gameFragment

import android.os.Bundle
import android.text.InputType
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import cn.neday.graduates.MusicConductor
import cn.neday.graduates.R
import cn.neday.graduates.activity.MainActivity
import cn.neday.graduates.databinding.FragmentStartABinding
import cn.neday.graduates.fragment.BaseBindingFragment
import cn.neday.graduates.view.NiftyDialogBuilder
import com.dylanc.longan.startActivity

class StartFragmentA : BaseBindingFragment<FragmentStartABinding>() {
    private var mSex: Boolean? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpViews()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setUpViews() {
        binding.btnNextStart.setOnClickListener { v: View ->
            playHeartbeatAnimation(v)
            nextStart()
        }
        binding.tvNick.setOnClickListener { v: View ->
            playHeartbeatAnimation(v)
            upNick()
        }

        binding.rgSex.setOnCheckedChangeListener { arg0: RadioGroup, _: Int ->
            when (arg0.checkedRadioButtonId) {
                R.id.rb_sex_0 -> {
                    mSex = false
                }
                R.id.rb_sex_1 -> {
                    mSex = true
                }

            }
        }
    }

    private fun upNick() {
        val dialogBuilder: NiftyDialogBuilder = NiftyDialogBuilder.getInstance(activity)
        dialogBuilder.withTitle("设置昵称")
            .withMessage(null).withEditText(InputType.TYPE_CLASS_TEXT).isCancelable(true)
            .withDuration(500).withButtonCancle().withButtonOk()
            .setButtonCancleClick { dialogBuilder.getDismiss() }
            .setButtonOk {
                // val nickname: String = NiftyDialogBuilder.et_player.getText().toString()
                // mSharedSettingsUtil.setUserNick(nickname)
                dialogBuilder.dismiss()
                onActivityCreated(null)
            }.show()
    }

    private fun nextStart() {
        MusicConductor.playSound(R.raw.button_0)
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(
                R.id.fragment_game,
                StartFragmentB()
            )?.addToBackStack(null)
            ?.commit()
    }

    override fun onResume() {
        super.onResume()
        view?.isFocusableInTouchMode = true
        view?.requestFocus()
        view?.setOnKeyListener { _: View?, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                startActivity<MainActivity>()
                activity?.finish()
                return@setOnKeyListener true
            }
            false
        }
    }
}