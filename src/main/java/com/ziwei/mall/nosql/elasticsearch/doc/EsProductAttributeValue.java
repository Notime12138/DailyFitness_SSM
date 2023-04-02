package com.ziwei.mall.nosql.elasticsearch.doc;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author Ziwei GONG
 * @date 2023/4/2
 * @name mallSpringboot
 * 搜索中的商品属性信息
 */

@Getter
@Setter
public class EsProductAttributeValue implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long productAttributeId;
    /**
     * 属性值
     */
    @Field(type = FieldType.Keyword)
    private String value;
    /**
     * 属性参数：0->规格；1->参数
     */
    private Integer type;
    /**
     * 属性名称
     */
    @Field(type=FieldType.Keyword)
    private String name;
}

