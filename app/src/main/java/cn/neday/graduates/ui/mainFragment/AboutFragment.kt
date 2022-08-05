package cn.neday.graduates.ui.mainFragment

import android.os.Bundle
import android.view.View
import cn.neday.graduates.databinding.FragmentAboutBinding
import cn.neday.graduates.fragment.BaseBindingFragment

class AboutFragment : BaseBindingFragment<FragmentAboutBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBack.setOnClickListener { popBackStack() }
    }
}