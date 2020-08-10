package com.hsb.gyqwanandroid.ui.main

import android.util.Log
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hsb.gyqwanandroid.R
import com.hsb.gyqwanandroid.base.BaseFragment
import com.hsb.gyqwanandroid.data.model.Article
import com.hsb.gyqwanandroid.data.model.BannerData
import com.hsb.gyqwanandroid.data.model.PageResponse
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    private val mainViewModel: MainViewModel by activityViewModels {
        MainViewModelFactory()
    }

    override fun initView() {
        super.initView()

        smart_fresh.setOnRefreshLoadMoreListener(object :OnRefreshLoadMoreListener{
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                mainViewModel.getArticleList(false)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                mainViewModel.run {
                    getBannerList()
                    getArticleList(true)
                }
            }
        })

        val bannerLayout = layoutInflater.inflate(R.layout.include_banner, null)
        val bannerView = bannerLayout.findViewById<BGABanner>(R.id.banner)
        bannerView.setAdapter { _, itemView, model, _ ->
            Glide.with(this@HomeFragment).load((model as BannerData)?.imagePath)
                .apply(RequestOptions.bitmapTransform( RoundedCorners(30)))
                .into(itemView as ImageView)
        }
        bannerView.setData(arrayListOf(BannerData("https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png")),
            null)

        mainViewModel.bannerDataList.observe(this, Observer {
            bannerView.setData(it,null)
        })

        rv.run {
            mainViewModel.articleAdapter = object : BaseQuickAdapter<Article, BaseViewHolder>(
                R.layout.adapter_article_item,
                arrayListOf()
            ) {
                override fun convert(holder: BaseViewHolder, item: Article) {
                    with(holder) {
                        setText(R.id.tv_article_title, item.title)
                        setText(
                            R.id.tv_chapter_name,
                            "${item.superChapterName}-${item.chapterName}"
                        )
                        setText(R.id.tv_author, if(item.author.isNullOrEmpty()) item.shareUser else item.author)
                        setText(R.id.tv_time, item.niceDate)
                    }
                }
            }.also {
                it.addHeaderView(bannerLayout)
            }
            layoutManager = LinearLayoutManager(context)
            adapter = mainViewModel.articleAdapter
        }
        mainViewModel.articlePage.observe(this, Observer {
            if(it.curPage == 1){
                smart_fresh.finishRefresh()
            }else{
                smart_fresh.finishLoadMore()
            }
            mainViewModel.articleAdapter.setList(it.datas)
        })
    }

    override fun initData() {
        super.initData()
        mainViewModel.run {
            getBannerList()
            getArticleList(true)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}