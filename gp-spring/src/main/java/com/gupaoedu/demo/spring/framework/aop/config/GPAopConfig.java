package com.gupaoedu.demo.spring.framework.aop.config;

import lombok.Data;

/**
 * Created by Tom.
 *
 * @author Tom
 */
@Data
public class GPAopConfig {
    private String pointCut;
    private String aspectClass;
    private String aspectBefore;
    private String aspectAfter;
    private String aspectAfterThrow;
    private String aspectAfterThrowingName;


}
