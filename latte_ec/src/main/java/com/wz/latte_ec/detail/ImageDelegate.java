package com.wz.latte_ec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wz.latte_core.delegate.LatteDelegate;
import com.wz.latte_ec.R;
import com.wz.latte_ec.R2;
import com.wz.latte_ui.recycler.ItemType;
import com.wz.latte_ui.recycler.MultipleFields;
import com.wz.latte_ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author wangzhen
 * @date 2019/07/30
 */
public class ImageDelegate extends LatteDelegate {

    @BindView(R2.id.rv_image_container)
    RecyclerView mRecyclerView;

    private static final String ARG_PICTURES = "ARG_PICTURES";


    public static ImageDelegate create(ArrayList<String> pictures) {
        final Bundle args = new Bundle();
        args.putStringArrayList(ARG_PICTURES, pictures);
        final ImageDelegate delegate = new ImageDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_image;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        initImages();
    }

    private void initImages() {
        final Bundle arguments = getArguments();
        if (arguments != null) {
            final ArrayList<String> pictures = arguments.getStringArrayList(ARG_PICTURES);
            final ArrayList<MultipleItemEntity> entities = new ArrayList<>();
            final int size;
            if (pictures != null) {
                size = pictures.size();
                for (int i = 0; i < size; i++) {
                    final String imageUrl = pictures.get(i);
                    final MultipleItemEntity entity = MultipleItemEntity
                            .builder()
                            .setItemType(ItemType.SINGLE_BIG_IMAGE)
                            .setField(MultipleFields.IMAGE_URL, imageUrl)
                            .build();
                    entities.add(entity);
                }
                final RecyclerImageAdapter adapter = new RecyclerImageAdapter(entities);
                mRecyclerView.setAdapter(adapter);
            }
        }
    }
}
