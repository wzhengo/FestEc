package com.wz.latte_ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.wz.latte_core.delegate.LatteDelegate;
import com.wz.latte_ui.recycler.ItemType;
import com.wz.latte_ui.recycler.MultipleFields;
import com.wz.latte_ui.recycler.MultipleItemEntity;
import com.wz.latte_ui.recycler.MultipleRecycleAdapter;
import com.wz.latte_ui.recycler.MultipleViewHolder;
import com.wz.latte_ec.R;
import com.wz.latte_ec.main.sort.SortDelegate;
import com.wz.latte_ec.main.sort.content.ContentDelegate;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;

/**
 * Created by WangZhen on 2019-05-25.
 */
public class SortRecyclerAdapter extends MultipleRecycleAdapter {

    private final SortDelegate DELEGATE;
    private int mPrePosition = 0;

    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String text = entity.getField(MultipleFields.TEXT);
                final boolean isClicked = entity.getField(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = holder.getAdapterPosition();
                        if (mPrePosition != currentPosition) {
                            //还原上一个
                            getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);

                            //更新选中的item
                            entity.setField(MultipleFields.TAG, true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;

                            final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);
                            showContent(contentId);
                        }
                    }
                });

                if (!isClicked) {
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                } else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }

                holder.setText(R.id.tv_vertical_item_name, text);

                break;
            default:
                break;
        }
    }

    private void showContent(int contentId) {
        final ContentDelegate delegate = ContentDelegate.newInstance(contentId);


    }

    private void switchContent(ContentDelegate delegate) {
        final LatteDelegate contentDelegate =
                SupportHelper.findFragment(DELEGATE.getChildFragmentManager(), ContentDelegate.class);
        if (contentDelegate != null) {
            contentDelegate.getSupportDelegate().replaceFragment(delegate, false);
        }
    }
}
