package com.example.dllo.colorfulchoice.picture.onclickpicture;

import android.view.View;
import android.widget.LinearLayout;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseAty;
import com.example.dllo.colorfulchoice.nettool.NetTool;

/**
 * Coder: JiaHaiRan
 * created on 16/9/19 09:29
 */

// http://design.zuimeia.com/api/v1/article/72/?device_id=860076039322200&platform=android&lang=zh&appVersion=1.1.9&appVersionCode=10190&systemVersion=23&countryCode=CN&user_id=54289&token=4ff-c8707efb1ab1d1555ba5&package_name=com.zuiapps.zuiworld

public class PictureContentActivity extends BaseAty{

    static String startUrl = "http://design.zuimeia.com/api/v1/article/";
    static String endUrl = "/?device_id=860076039322200&platform=android&lang=zh&appVersion=1.1.9&appVersionCode=10190&systemVersion=23&countryCode=CN&user_id=54289&token=4ff-c8707efb1ab1d1555ba5&package_name=com.zuiapps.zuiworld";
    int num = 72;

    String myUrl = startUrl + num + endUrl;
    private HtmlTextView tv;

    @Override
    protected int setLayout() {
        return R.layout.activity_picture_content;
    }

    @Override
    protected void initView() {
        LinearLayout linearLayout = bindView(R.id.picture_content_ll);
        tv = bindView(R.id.tv);
    }

    @Override
    protected void initData() {
        NetTool netTool = new NetTool();
        netTool.getNetData(myUrl, PictureDetailBean.class, new NetTool.NetListener<PictureDetailBean>() {
            @Override
            public void onSuccess(PictureDetailBean pictureDetailBean) {
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
                tv.setHtmlFromString(html,false);
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
