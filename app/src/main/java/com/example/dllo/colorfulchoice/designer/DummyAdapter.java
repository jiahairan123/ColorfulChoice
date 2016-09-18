package com.example.dllo.colorfulchoice.designer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.MyApp;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Coder: JiaHaiRan
 * created on 16/9/18 14:01
 */

public class DummyAdapter extends BaseAdapter{

    DesignerBean designerBean;

    public void setDesignerBean(DesignerBean designerBean) {
        this.designerBean = designerBean;
        notifyDataSetChanged();
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        if (designerBean == null){
            return 0;
        }
        return designerBean == null ? 0 : designerBean.getData().getDesigners().size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return null;
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.item_designer_dummy, null);
            viewholder = new Viewholder(convertView);
            convertView.setTag(viewholder);

        } else {
            viewholder = (Viewholder) convertView.getTag();
        }
        final DesignerBean.DataBean.DesignersBean bean = designerBean.getData().getDesigners().get(position);

        viewholder.name.setText(bean.getName());
        viewholder.position.setText(bean.getLabel());
        Glide.with(MyApp.getContext()).load(bean.getAvatar_url()).into(viewholder.personImg);
        Glide.with(MyApp.getContext()).load(bean.getRecommend_images().get(0)).into(viewholder.contentImg);

        return convertView;
    }

    class Viewholder{

        TextView name, position;
        ImageView contentImg;
        CircleImageView personImg;
        Button button;

        public Viewholder(View view){

            personImg = (CircleImageView) view.findViewById(R.id.item_designer_person_iv);
            contentImg = (ImageView) view.findViewById(R.id.item_designer_content_iv);
            name = (TextView) view.findViewById(R.id.item_designer_person_name);
            position = (TextView) view.findViewById(R.id.item_designer_person_position);
            button = (Button) view.findViewById(R.id.item_designer_btn);

        }
    }
}
