package com.example.dllo.colorfulchoice.picture;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.example.dllo.colorfulchoice.nettool.URLValue;
import com.wirelesspienetwork.overview.model.OverviewAdapter;
import com.wirelesspienetwork.overview.model.ViewHolder;
import com.wirelesspienetwork.overview.views.Overview;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;


public class PictureFragment extends BaseFragment implements Overview.RecentsViewCallbacks {

    private List<PictureBean.DataBean.ArticlesBean> articles;
    boolean mVisible;
    Overview mRecentsView;
    private OverviewAdapter stack;

    @Override
    protected int setLayout() {
        return R.layout.fragment_picture;
    }

    @Override
    protected void initView() {
        mRecentsView =bindView(R.id.recents_view);

        //unknown
        mRecentsView.setCallbacks(new Overview.RecentsViewCallbacks() {
            @Override
            public void onCardDismissed(int position) {
                Toast.makeText(mContext, "Card被移除" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAllCardsDismissed() {

            }

            @Override
            public void onTrimMemory(int level) {

            }
        });

    }

    @Override
    protected void initData() {
        NetTool netTool = new NetTool();
        // Mark Recents as visible
        mVisible = true;

        ArrayList<Integer> models = new ArrayList<>();
        //TODO 设置个数
        for (int i = 0; i < 45; ++i) {
            Random random = new Random();
            random.setSeed(i);
            models.add(0xffffff);
        }

        stack = new OverviewAdapter<ViewHolder<View, Integer>, Integer>(models) {
            @Override
            public ViewHolder onCreateViewHolder(Context context, ViewGroup parent) {
                View v = View.inflate(context, R.layout.recents_dummy, null);
                return new ViewHolder<View, Integer>(v);
            }

            @Override
            public void onBindViewHolder(ViewHolder<View, Integer> viewHolder) {
                viewHolder.itemView.setBackgroundColor(viewHolder.model);

                int position = 44 - viewHolder.getPosition();

                TextView sign, title, content;
                ImageView contentImg;
                CircleImageView userImg;

                sign = (TextView) viewHolder.itemView.findViewById(R.id.dummy_p_nickname);
                title = (TextView) viewHolder.itemView.findViewById(R.id.dummy_title);
                content = (TextView) viewHolder.itemView.findViewById(R.id.dummy_content);
                userImg = (CircleImageView) viewHolder.itemView.findViewById(R.id.dummy_p_iv);
                contentImg = (ImageView) viewHolder.itemView.findViewById(R.id.dummy_iv);

                sign.setText(articles.get(position).getAuthor().getSign());
                title.setText(articles.get(position).getTitle());
                content.setText(articles.get(position).getSub_title());

                Glide.with(PictureFragment.this.getActivity()).load(articles.get(position).getImage_url()).into(contentImg);
                Glide.with(PictureFragment.this.getActivity()).load(articles.get(position).getAuthor().getAvatar_url()).into(userImg);
                viewHolder.itemView.setOnClickListener(new MyOnClickListener(position));

            }
        };

        netTool.getNetData(URLValue.PICTURE_URL, PictureBean.class, new NetTool.NetListener<PictureBean>() {
            @Override
            public void onSuccess(PictureBean pictureBean) {

                //获取最里层的 data
                articles = pictureBean.getData().getArticles();
                mRecentsView.setTaskStack(stack);
            }

            @Override
            public void onError(String errorMsg) {
                Toast.makeText(mContext, "data获取失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //itemView的监听
    class MyOnClickListener implements View.OnClickListener{
        int pos;

        public MyOnClickListener(int pos) {
            this.pos = pos;
        }

        //点击item 跳转activity
        @Override
        public void onClick(View view) {
            Toast.makeText(mContext, "pos:" + pos, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(),PictureContentActivity.class);
            getActivity().startActivity(intent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onCardDismissed(int position) {

    }

    @Override
    public void onAllCardsDismissed() {

    }

    @Override
    public void onTrimMemory(int level) {

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}

