package com.example.dllo.colorfulchoice.picture.onclickpicture;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseAty;
import com.example.dllo.colorfulchoice.nettool.NetTool;

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
    private TextView title, subTitle, userName, sign;
    private CircleImageView userImg, authorImg;
    private ImageView bigImg;
    private EditText userEt;

    @Override
    protected int setLayout() {
        return R.layout.activity_picture_content;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        pos = intent.getIntExtra("pos", 0);

        tv = bindView(R.id.picture_content_tv);
        title = bindView(R.id.picture_content_title);
        subTitle = bindView(R.id.picture_content_sub_title);
        authorImg = bindView(R.id.picture_content_author_img);
        sign = bindView(R.id.picture_content_author_sign);
        userImg = bindView(R.id.picture_content_user_img);
        userEt = bindView(R.id.picture_content_user_et);
        bigImg = bindView(R.id.picture_content_designer_img);


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

                Glide.with(getApplicationContext()).load(pictureDetailBean.getData().getImage_url()).into(bigImg);
                Glide.with(getApplicationContext()).load(pictureDetailBean.getData().getAuthor().getAvatar_url()).into(authorImg);
                Glide.with(getApplicationContext()).load(pictureDetailBean.getData().getComments().get(0).getAuthor().getAvatar_url()).into(userImg);


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
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
