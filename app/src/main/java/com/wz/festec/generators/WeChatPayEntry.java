package com.wz.festec.generators;

import com.wz.latte_annotations.PayEntryGenerator;
import com.wz.latte_core.wechat.templates.WXPayEntryTemplate;

/**
 * Created by WangZhen on 2019/4/23.
 */
@PayEntryGenerator(packageName = "com.wz.festec", payEntryTemplate = WXPayEntryTemplate.class)
public interface WeChatPayEntry {
}
