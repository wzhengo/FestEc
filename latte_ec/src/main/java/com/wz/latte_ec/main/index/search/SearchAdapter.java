package com.wz.latte_ec.main.index.search;

import android.support.v7.widget.AppCompatTextView;

import com.wz.latte_ec.R;
import com.wz.latte_ui.recycler.MultipleFields;
import com.wz.latte_ui.recycler.MultipleItemEntity;
import com.wz.latte_ui.recycler.MultipleRecycleAdapter;
import com.wz.latte_ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * @author wangzhen
 * @date 2019/07/24
 */
public class SearchAdapter extends MultipleRecycleAdapter {

    protected SearchAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(SearchItemType.ITEM_SEARCH, R.layout.item_search);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (entity.getItemType()) {
            case SearchItemType.ITEM_SEARCH:
                final AppCompatTextView tvSearchItem = holder.getView(R.id.tv_search_item);
                final String history = entity.getField(MultipleFields.TEXT);
                tvSearchItem.setText(history);
                break;
            default:
                break;
        }
    }
}
