package com.system.dao;

import java.util.List;

import com.system.domain.MiniRequest;
import com.system.domain.User;
import com.system.domain.User2;

public interface UserDao {

	User2 checkUser(User2 user);

	// @Insert("insert into work_user (name, email) values (#{name}, #{email})")
	int save(User user);

	// @Update("update work_user set name = #{name}, email = #{email} where id = #{id}")
	void update(User user);

	// @Delete("delete from work_user where id = #{id}")
	void delete(int id);

	// @Select("select count(id) from work_user")
	int count(MiniRequest request);

	// @Select("select * from (select *, row_number() over (order by id) rn from work_user) t where t.rn BETWEEN #{start} and #{end}")
	// @SelectProvider(type = UserProvider.class, method = "getUsers")
	List<User> getUsers(MiniRequest request);
}
