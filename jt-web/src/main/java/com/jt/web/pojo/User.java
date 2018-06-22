package com.jt.web.pojo;

import com.jt.common.po.BasePojo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User extends BasePojo{
	
	private Long id;	//用户id号
	private String username;	//用户名
	private String password;	//密码  需要将密码进行加密处理
	private String phone;		//电话号码
	private String email;		//表示邮箱地址  邮箱的内容写的是电话号码  为了入库不出错

	
}
