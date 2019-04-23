package com.wz.festec.generators;

import com.wz.latte_annotations.EntryGenerator;
import com.wz.latte_core.wechat.templates.WXEntryTemplate;

/**
 * Created by WangZhen on 2019/4/23.
 */
@EntryGenerator(packageName = "com.wz.festec", entryTemplate = WXEntryTemplate.class)
public interface WeChatEntry {
}
