package cn.neday.graduates.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.dylanc.viewbinding.base.ActivityBinding
import com.dylanc.viewbinding.base.ActivityBindingDelegate
import com.tapsdk.antiaddictionui.AntiAddictionUIKit

abstract class BaseBindingActivity<VB : ViewBinding> : AppCompatActivity(),
    ActivityBinding<VB> by ActivityBindingDelegate() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentViewWithBinding()
    }

    override fun onStart() {
        super.onStart()
        AntiAddictionUIKit.enterGame();
    }

    override fun onStop() {
        super.onStop()
    }
}