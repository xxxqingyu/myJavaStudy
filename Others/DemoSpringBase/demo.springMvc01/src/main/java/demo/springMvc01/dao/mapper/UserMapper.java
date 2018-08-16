package demo.springMvc01.dao.mapper;

import java.util.List;

import demo.springMvc01.dao.domain.User;

public interface UserMapper {
	List<User> getAll();
	
	int update(User user);
}
