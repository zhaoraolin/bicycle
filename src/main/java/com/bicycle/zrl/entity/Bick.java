package com.bicycle.zrl.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 单车基本信息
 * @author zrl
 * bick这个类以后跟MongoDb中的bikes collection关联上
 */
@Data
@Document(collection = "bikes")
public class Bick implements Serializable{
	private static final long serialVersionUID = 2330907686236499743L;

	//主键（唯一、建立索引）  对于mongodb中的_id字段
	@Id
	private String id;

	//表示经纬度数组    【0】经度    【1】纬度
	//必须加入下注解，才能使用mongodb中的geo
	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	private double[] location;
//	private double latitude;  //纬度
//
//	private double longitude;   //经度

	//对这个字段建立索引
	@Indexed
	private long bikeNo;

	private int status;   //状态   
	
}
