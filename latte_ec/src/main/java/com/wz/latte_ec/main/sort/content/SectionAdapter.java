package com.wz.latte_ec.main.sort.content;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wz.latte_ec.R;

import java.util.List;

/**
 * Created by WangZhen on 2019-05-25.
 */
public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean, BaseViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionBean item) {
        //item.t返回类型
        final SectionContentItemEntity entity = item.t;
        final String thumb = entity.getGoodsThumb();
        final String name = entity.getGoodsName();
        final int id = entity.getGoodsId();
        final AppCompatImageView goodsImageView = helper.getView(R.id.iv);
        helper.setText(R.id.tv, name);
        Glide.with(mContext)
                .load(thumb)
                .into(goodsImageView);

    }
}
