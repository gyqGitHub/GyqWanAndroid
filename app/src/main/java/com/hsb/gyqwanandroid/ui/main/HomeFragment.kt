package com.hsb.gyqwanandroid.ui.main

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.hsb.gyqwanandroid.R
import com.hsb.gyqwanandroid.base.BaseFragment
import com.hsb.gyqwanandroid.data.model.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    private val mainViewModel: MainViewModel by activityViewModels {
        MainViewModelFactory()
    }

    override fun initView() {
        super.initView()

        mainViewModel.bannerList.observe(this, Observer {
            banner.run {
                if (adapter == null) {
                    adapter = object : BannerImageAdapter<Banner>(it) {
                        override fun onBindView(
                            holder: BannerImageHolder,
                            data: Banner,
                            position: Int,
                            size: Int
                        ) {
                            Glide.with(this@HomeFragment).load(data.imagePath)
                                .into(holder.imageView)
                        }
                    }
                    addBannerLifecycleObserver(this@HomeFragment)
                    setIndicator(CircleIndicator(context))
                } else {
                    adapter.setDatas(it)
                }
            }
        })
    }

    override fun initData() {
        super.initData()
        mainViewModel.getBannerList()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}