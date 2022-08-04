package cn.neday.graduates.ui.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.neday.graduates.databinding.FragmentAboutBinding
import cn.neday.graduates.fragment.BaseBindingFragment

class AboutFragment : BaseBindingFragment<FragmentAboutBinding>() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.ivBack.setOnClickListener { activity?.supportFragmentManager?.popBackStack() }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}