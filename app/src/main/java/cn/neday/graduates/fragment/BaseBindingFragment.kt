package cn.neday.graduates.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.view.animation.Animation.AnimationListener
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.dylanc.viewbinding.base.FragmentBinding
import com.dylanc.viewbinding.base.FragmentBindingDelegate

abstract class BaseBindingFragment<VB : ViewBinding> : Fragment(),
    FragmentBinding<VB> by FragmentBindingDelegate() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return createViewWithBinding(inflater, container)
    }

    protected fun popBackStack() {
        popBackStack()
    }

    // 按钮模拟心脏跳动
    protected fun playHeartbeatAnimation(imageView: View) {
        val animationSet = AnimationSet(true)
        animationSet.addAnimation(
            ScaleAnimation(
                1.0f, 0.5f, 1.0f, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f
            )
        )
        animationSet.addAnimation(AlphaAnimation(1.0f, 0.4f))
        animationSet.duration = 200
        animationSet.interpolator = AccelerateInterpolator()
        animationSet.fillAfter = true
        animationSet.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                val animationSet = AnimationSet(true)
                animationSet.addAnimation(
                    ScaleAnimation(
                        0.5f, 1.0f, 0.5f,
                        1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
                    )
                )
                animationSet.addAnimation(AlphaAnimation(0.4f, 1.0f))
                animationSet.duration = 600
                animationSet.interpolator = DecelerateInterpolator()
                animationSet.fillAfter = false

                // 实现心跳的View
                imageView.startAnimation(animationSet)
            }
        })

        // 实现心跳的View
        imageView.startAnimation(animationSet)
    }
}
