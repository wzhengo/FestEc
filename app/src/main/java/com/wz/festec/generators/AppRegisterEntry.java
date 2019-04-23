package com.wz.festec.generators;

import com.wz.latte_annotations.AppRegisterGenerator;
import com.wz.latte_core.wechat.templates.AppRegisterTemplate;

/**
 * Created by WangZhen on 2019/4/23.
 */
@AppRegisterGenerator(packageName = "com.wz.festec", registerTemplate = AppRegisterTemplate.class)
public interface AppRegisterEntry {
}
