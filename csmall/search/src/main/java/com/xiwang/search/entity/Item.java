package com.xiwang.search.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@AllArgsConstructor  //自动生成全参构造方法
@NoArgsConstructor  //自动生成无参构造方法
// Document注解标记表示当前类对应ES框架的一个实体类
// indexName指定ES的索引名称,运行时如果索引不存在SpringData会自动创建这个索引
@Document(indexName = "items")
public class Item implements Serializable {

    // SpringData通过@Id注解标记当前实体类主键
    @Id
    private Long id;

    // SpringData标记title字段需要支持分词,并定义词缀
    @Field(type = FieldType.Text,
            analyzer = "ik_max_word",
            searchAnalyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Keyword)
    private String brand;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Keyword,index = false)
    private String imgPath;
}
