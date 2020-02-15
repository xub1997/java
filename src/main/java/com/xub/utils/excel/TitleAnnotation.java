package com.xub.utils.excel;

/**
 * Excel表头注解
 **/
public @interface TitleAnnotation {

    /**
     * 字段描述
     *
     * @return
     */
    String titleName() default "";

    /**
     * 是否必填字段
     *
     * @return
     */
    boolean required() default false;

    /**
     * 是否必填字段
     *
     * @return
     */
    String requiredDesc() default "(必填)";

    /**
     * 校验必填字段
     *
     * @return
     */
    boolean checkRequired() default false;
}
