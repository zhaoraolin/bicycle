package com.bicycle.zrl.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "users")
public class User implements Serializable {

    private static final long serialVersionUID = -3318743732734667961L;

    @Id
    private String id;

    @Indexed
    private String phoneNum;   //手机号

    private Date regDate;  //注册时间

    private String name;   //姓名

    private String idNum;   //身份证号

    private Double deposit;   //押金

    private Integer status;    //状态
}
