package com.example.dllo.colorfulchoice.designer.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseAty;
import com.example.dllo.colorfulchoice.designer.bean.TurnPagerBean;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.example.dllo.colorfulchoice.nettool.URLValue;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Coder: JiaHaiRan
 * created on 16/9/21 09:31
 */

public class DesignerDetailsActivity extends BaseAty {


    private RollPagerView viewPager;
    private CircleImageView designerPic;
    private TextView designerName, designerPosition, designerWords, designerHistory;
    private Button signBtn;
    private ViewPager centerVp;
    private TabLayout tabLayout;


    @Override
    protected int setLayout() {
        return R.layout.activity_designer_details;
    }

    @Override
    protected void initView() {

        viewPager = bindView(R.id.designer_detail_view_pager);
        designerPic = bindView(R.id.designer_detail_designer_pic);
        designerName = bindView(R.id.designer_detail_designer_name);
        designerPosition = bindView(R.id.designer_detail_designer_position);
        designerWords = bindView(R.id.designer_detail_designer_words);
        designerHistory = bindView(R.id.designer_detail_designer_history);
        signBtn = bindView(R.id.designer_detail_sign_btn);
        signBtn.setOnClickListener(this);

        tabLayout = bindView(R.id.designer_detail_center_tablayout);
        centerVp = bindView(R.id.designer_detail_center_viewpager);
    }

    @Override
    protected void initData() {

//        获取数据
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String url = URLValue.TP_BEGIN_URL + id + URLValue.TP_END_URL;


        netTool.getNetData(url, TurnPagerBean.class, new NetTool.NetListener<TurnPagerBean>() {
            @Override
            public void onSuccess(TurnPagerBean turnPagerBean) {

                Glide.with(getApplicationContext()).load(turnPagerBean.getData().getAvatar_url()).into(designerPic);

                designerName.setText(turnPagerBean.getData().getName());
                designerHistory.setText(turnPagerBean.getData().getDescription());
                designerWords.setText(turnPagerBean.getData().getConcept());
                designerPosition.setText(turnPagerBean.getData().getLabel());

                //黑科技之设置指示器的color
                viewPager.setHintView(new ColorPointHintView(getApplicationContext(), Color.BLACK, Color.GRAY));
                JiahairanLoopAdapter adapter = new JiahairanLoopAdapter(viewPager, turnPagerBean, getApplicationContext());

                viewPager.setAdapter(adapter);

            }


            @Override
            public void onError(String errorMsg) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    private class JiahairanLoopAdapter extends LoopPagerAdapter {

        TurnPagerBean turnPagerBean;
        Context context;


        public JiahairanLoopAdapter(RollPagerView viewPager, TurnPagerBean turnPagerBean, Context context) {
            super(viewPager);
            this.turnPagerBean = turnPagerBean;
            this.context = context;
            notifyDataSetChanged();
        }


        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            Glide.with(context).load(turnPagerBean.getData().getIntroduce_images().get(position)).into(view);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }


        @Override
        public int getRealCount() {

            int count = (turnPagerBean != null ? turnPagerBean.getData().getIntroduce_images().size() : 0);

            return count;

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewPager.pause();
    }


}
