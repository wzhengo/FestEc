package com.wz.latte_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by WangZhen on 2019/4/23.
 */
@Target(ElementType.TYPE)           //注解用在类上
@Retention(RetentionPolicy.SOURCE)  //源码阶段处理注解
public @interface PayEntryGenerator {

    String packageName();

    Class<?> payEntryTemplate();
}
