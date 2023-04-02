package com.ziwei.mall.nosql.elasticsearch.doc;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/4/2
 * @name mallSpringboot
 * 需要分词搜索的商品信息
 * 使用analyzer的是需要中文分词的字段
 */

@Getter
@Setter
@Document(indexName = "pms")
public class EsProduct implements Serializable {
    private static final long serialVersionUID = -1L;

    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String productSn;
    private Long brandId;

    @Field(type = FieldType.Keyword)
    private String brandName;
    private Long productCategoryId;

    @Field(type = FieldType.Keyword)
    private String productCategoryName;
    private String pic;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String name;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String subTitle;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String keywords;
    private BigDecimal price;
    private Integer sale;
    private Integer newStatus;
    private Integer recommandStatus;
    private Integer stock;
    private Integer promotionType;
    private Integer sort;

    @Field(type =FieldType.Nested)
    private List<EsProductAttributeValue> attrValueList;
}
