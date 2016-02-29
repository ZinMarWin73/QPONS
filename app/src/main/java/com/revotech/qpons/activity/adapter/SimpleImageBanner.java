package com.revotech.qpons.activity.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.banner.widget.Banner.BaseIndicaorBanner;
import com.flyco.banner.widget.Banner.base.BaseBanner;
import com.flyco.pageindicator.indicator.RoundCornerIndicaor;
import com.revotech.qpons.R;
import com.revotech.qpons.activity.data.pojo.BannerItem;
import com.revotech.qpons.activity.utils.ViewFindUtils;



public class SimpleImageBanner extends BaseBanner<BannerItem, SimpleImageBanner> {
    private ColorDrawable colorDrawable;
    //Context context;

    RoundCornerIndicaor round_indi;
    public SimpleImageBanner(Context context) {
        this(context, null, 0);
    }

    public SimpleImageBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleImageBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        colorDrawable = new ColorDrawable(Color.parseColor("#555555"));
    }

    @Override
    public void onTitleSlect(TextView tv, int position) {
        final BannerItem item = list.get(position);
        tv.setText(item.title);
    }

    @Override
    public View onCreateItemView(int position) {
       View inflate = View.inflate(context, R.layout.simple_image_banner, null);

        ImageView iv = ViewFindUtils.find(inflate, R.id.iv);

        final BannerItem item = list.get(position);
        int itemWidth = dm.widthPixels;
        int itemHeight = (int) (itemWidth * 360 * 1.0f / 640);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setLayoutParams(new LinearLayout.LayoutParams(itemWidth, itemHeight));

        String imgUrl = item.img_url;

        if (!TextUtils.isEmpty(imgUrl)) {
            Glide.with(context)
                    .load(imgUrl)
                    .override(itemWidth, itemHeight)
                    .centerCrop()
                    .placeholder(colorDrawable)
                    .into(iv);
        } else {
            iv.setImageDrawable(colorDrawable);
        }

        return inflate;
    }

    @Override
    public View onCreateIndicator() {

        round_indi = new RoundCornerIndicaor(context);
        round_indi.setViewPager(vp , list.size());

        return round_indi;
    }

    @Override
    public void setCurrentIndicator(int position) {
                round_indi.setCurrentItem(position);
    }
}
