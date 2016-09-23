package com.example.dllo.colorfulchoice.picture.onclickpicture;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseAty;
import com.example.dllo.colorfulchoice.base.CommonAdapter;
import com.example.dllo.colorfulchoice.base.CommonViewHolder;
import com.example.dllo.colorfulchoice.goodthing.NormalBean;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.example.dllo.colorfulchoice.picture.PictureBean;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Coder: JiaHaiRan
 * created on 16/9/19 09:29
 */

// http://design.zuimeia.com/api/v1/article/72/?device_id=860076039322200&platform=android&lang=zh&appVersion=1.1.9&appVersionCode=10190&systemVersion=23&countryCode=CN&user_id=54289&token=4ff-c8707efb1ab1d1555ba5&package_name=com.zuiapps.zuiworld

public class PictureContentActivity extends BaseAty {

    static String startUrl = "http://design.zuimeia.com/api/v1/article/";
    static String endUrl = "/?device_id=860076039322200&platform=android&lang=zh&appVersion=1.1.9&appVersionCode=10190&systemVersion=23&countryCode=CN&user_id=54289&token=4ff-c8707efb1ab1d1555ba5&package_name=com.zuiapps.zuiworld";
    private HtmlTextView tv;
    private int pos;
    private TextView title, subTitle, userName, sign, designerLabel, productIntroduce, designerName;
    private CircleImageView userImg, authorImg, backBtn, designerImg;
    private ImageView bigImg;
    private EditText userEt;
    private RelativeLayout personTitleDetail;
    private GridView gridView, opinionGridView;


    @Override
    protected int setLayout() {

        return R.layout.activity_picture_content;

    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        pos = intent.getIntExtra("pos", 0);

        backBtn = bindView(R.id.picture_content_back_circle_btn);
        backBtn.setOnClickListener(this);

        tv = bindView(R.id.picture_content_html);

        userEt = bindView(R.id.picture_content_user_et);
        title = bindView(R.id.picture_content_title);
        subTitle = bindView(R.id.picture_content_sub_title);

        sign = bindView(R.id.picture_content_author_sign);
        authorImg = bindView(R.id.picture_content_author_img);
        userImg = bindView(R.id.picture_content_user_img);
        bigImg = bindView(R.id.picture_content_designer_img);

        designerLabel = bindView(R.id.picture_content_designer_product_position);
        designerName = bindView(R.id.picture_content_designer_product_name);
        designerImg = bindView(R.id.picture_content_designer_product_img);
        productIntroduce = bindView(R.id.picture_content_designer_product_introduce);

        gridView = bindView(R.id.picture_content_designer_gridview);
        opinionGridView = bindView(R.id.item_guest_opinion);

    }

    @Override
    protected void initData() {

        String myUrl = startUrl + pos + endUrl;
        NetTool netTool = new NetTool();

        netTool.getNetData(myUrl, PictureDetailBean.class, new NetTool.NetListener<PictureDetailBean>() {
            @Override
            public void onSuccess(PictureDetailBean pictureDetailBean) {

                title.setText(pictureDetailBean.getData().getTitle());
                subTitle.setText(pictureDetailBean.getData().getSub_title());
                sign.setText(pictureDetailBean.getData().getAuthor().getSign());

                //获取到设计师的数据
                final PictureDetailBean.DataBean.DesignersBean bean = pictureDetailBean.getData().getDesigners().get(0);

                designerLabel.setText(bean.getLabel());
                designerName.setText(bean.getName());
                productIntroduce.setText(bean.getDescription());
                Glide.with(getApplicationContext()).load(bean.getAvatar_url()).into(designerImg);
                Glide.with(getApplicationContext()).load(pictureDetailBean.getData().getImage_url()).into(bigImg);
                Glide.with(getApplicationContext()).load(pictureDetailBean.getData().getAuthor().getAvatar_url()).into(authorImg);
                Glide.with(getApplicationContext()).load(pictureDetailBean.getData().getComments().get(0).getAuthor().getAvatar_url()).into(userImg);


                //html图文混排
                String html = pictureDetailBean.getData().getContent();
                html.trim();
                html = html.replace("<p >", "<p>");
                html = html.replace("<blockquote >", "");
                html = html.replace("</blockquote>", "");
                html = html.replace("<br/>", "");
                html = html.replace("<ul  class=\" list-paddingleft-2\">", "");
                html = html.replace("</ul>", "");
                html = html.replace("</li>", "");
                html = html.replace("<li>", "");
                tv.setHtmlFromString(html, false);

                //GridView  商品信息
                gridView.setAdapter(new CommonAdapter<PictureDetailBean.DataBean.ReferProductsBean>(pictureDetailBean.getData().getRefer_products(), getApplicationContext()
                        , R.layout.item_gridview) {
                    @Override
                    public void setData(PictureDetailBean.DataBean.ReferProductsBean referProductsBean, CommonViewHolder viewHolder) {
                        final String s = referProductsBean.getCover_images().get(0);
                        viewHolder.setImage(R.id.item_gridview_img, s);
                    }
                });

                //评论列表 10条数据
                opinionGridView.setAdapter(new CommonAdapter<PictureDetailBean.DataBean.CommentsBean>(pictureDetailBean.getData().getComments(),getApplicationContext()
                , R.layout.item_opinion) {
                    @Override
                    public void setData(PictureDetailBean.DataBean.CommentsBean commentsBean, CommonViewHolder viewHolder) {

                        viewHolder.setImage(R.id.item_opinion_img, commentsBean.getAuthor().getAvatar_url());
                        viewHolder.setText(R.id.item_opinion_nick_name, commentsBean.getAuthor().getUsername());
                        viewHolder.setText(R.id.item_opinion_nick_word,commentsBean.getContent());


                        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                        final long time = commentsBean.getCreated_at();
                         String normalTime = format.format(new Date(Long.valueOf(time)));
                        viewHolder.setText(R.id.item_opinion_nick_word, normalTime);
                    }
                });


            }

            @Override
            public void onError(String errorMsg) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.picture_content_back_circle_btn:
                finish();
                break;

            default:
                break;
        }
    }
}
