/**
 * 
 */
package com.ecpss.templet.domain.model;

import java.lang.annotation.*;

/**
 * 最后更新时间域注解，在数据入库时将自动取最新时间
 * @author lindongcheng
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface UpdateTime {
}
