package com.myboot.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.myboot.demo.domain.User;

public interface UserMapper {
	
	@Select("SELECT * FROM users")
	@Results({
		@Result(property="nickName",column="nickName")
	})
	List<User> getAll();
	
	@Select("SELECT * FROM users WHERE id=#{id}")
	User get(long id);
	
	@Insert("INSERT INTO `demo_test`.`users` (`userName`,`password`,`nickName`,`email`,`isAdmin`,`createDate`)"+
		"VALUES (#{userName},#{password},#{nickName}>,#{email},#{isAdmin}>,#{createDate});")
	@SelectKey( statement="select @@IDENTITY as id", keyProperty="id",before=false,resultType=long.class)
	long insert(User user);
	
	@Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
	int update(User user);
	
	@Delete("DELETE FROM users WHERE id =#{id}")
	void delete(Long id);
	
	@SelectProvider(type=UserSqlProvider.class,method="sql_getUsers") 
	List<User> getUsers(long... ids);
	
	public class UserSqlProvider{
		public String sql_getUsers(long... ids) {
			
			List<String> idsStr=new ArrayList<String>();
			for (Long id : ids) {
				idsStr.add(id.toString());
			}
			
			return String.format("SELECT * FROM users WHERE id IN (%s)", String.join(",", idsStr));
		}
	}
}


