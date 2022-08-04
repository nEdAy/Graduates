package cn.neday.graduates.ui.gameFragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.neday.graduates.R
import cn.neday.graduates.databinding.FragmentStockBinding
import cn.neday.graduates.fragment.BaseBindingFragment
import cn.neday.graduates.repository.Score
import com.dylanc.longan.doOnClick
import com.dylanc.longan.toast

class StockFragment : BaseBindingFragment<FragmentStockBinding>() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpViews()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvMoney.text = Score.money.toString()
        binding.tvValue.text = Score.stock.toString()
    }

    private fun setUpViews() {
        binding.ivBack.doOnClick { activity?.supportFragmentManager?.popBackStack() }
        binding.btnBuy.doOnClick { buy() }
        binding.btnSell.doOnClick { sell() }
        setRandom()
    }

    private fun setRandom() {
        val randomStock: Int = Score.randomStock
        val indexStock: Int = Score.indexStock
        binding.tvIndex.text = indexStock.toString()
        binding.tvRate.text = (randomStock - 100).toString() + "%"
        if (randomStock < 100) {
            binding.ivRate.setImageResource(R.mipmap.die)
        } else if (randomStock > 100) {
            binding.ivRate.setImageResource(R.mipmap.zhang)
        } else {
            binding.ivRate.setImageResource(R.mipmap.ping)
        }
    }

    private fun buy() {
        var mBuy = 0
        if (!TextUtils.isEmpty(binding.etBuy.text)) {
            mBuy = binding.etBuy.text.toString().toInt()
        }
        if (mBuy > Score.money) {
            toast(R.string.stock_no_money)
        } else if (mBuy < 100) {
            toast(R.string.stock_min_money)
        } else {
            Score.money = Score.money.minus((mBuy * 1.005).toInt())
            Score.stock = Score.stock.plus(mBuy)
            onActivityCreated(null)
        }
    }

    private fun sell() {
        var mSell = 0
        if (!TextUtils.isEmpty(binding.etSell.text)) {
            mSell = binding.etSell.text.toString().toInt()
        }
        if (mSell > Score.stock) {
            toast(R.string.stock_no_stock)
        } else if (mSell < 100) {
            toast(R.string.stock_min_money)
        } else {
            Score.money = Score.money.plus((mSell * 0.995).toInt())
            Score.stock = Score.stock.minus(mSell)
            onActivityCreated(null)
        }
    }
}