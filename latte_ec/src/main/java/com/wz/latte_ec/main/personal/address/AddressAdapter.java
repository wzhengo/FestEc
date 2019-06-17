package com.wz.latte_ec.main.personal.address;

import android.support.v7.widget.AppCompatTextView;

import com.wz.latte_core.net.RestClient;
import com.wz.latte_ec.R;
import com.wz.latte_ui.recycler.MultipleFields;
import com.wz.latte_ui.recycler.MultipleItemEntity;
import com.wz.latte_ui.recycler.MultipleRecycleAdapter;
import com.wz.latte_ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * @author wangzhen
 * @date 2019/06/17
 */
public class AddressAdapter extends MultipleRecycleAdapter {

    protected AddressAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(AddressItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case AddressItemType.ITEM_ADDRESS:
                final String name = entity.getField(MultipleFields.NAME);
                final String phone = entity.getField(AddressItemFields.PHONE);
                final String address = entity.getField(AddressItemFields.ADDRESS);
                final boolean isDefault = entity.getField(MultipleFields.TAG);
                final int id = entity.getField(MultipleFields.ID);

                final AppCompatTextView nameText = holder.getView(R.id.tv_address_name);
                final AppCompatTextView phoneText = holder.getView(R.id.tv_address_phone);
                final AppCompatTextView addressText = holder.getView(R.id.tv_address_address);
                final AppCompatTextView deleteTextView = holder.getView(R.id.tv_address_delete);

                deleteTextView.setOnClickListener(v -> {
                    RestClient.builder()
                            .url("address.php")
                            .params("id", id)
                            .success(response -> {
                                remove(holder.getLayoutPosition());
                            })
                            .build()
                            .post();
                });

                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);
                break;

            default:
                break;
        }
    }
}
