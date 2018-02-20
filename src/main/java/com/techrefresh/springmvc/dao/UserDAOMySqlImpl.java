package com.techrefresh.springmvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.techrefresh.springmvc.model.Login;
import com.techrefresh.springmvc.model.User;

@Repository
@Qualifier("mySqlUserDao")
public class UserDAOMySqlImpl implements UserDAO {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void register(User user) {
		
		String query = "insert into users (user_id, first_name, last_name, user_role, email, phone)"
						+"values (:userId, :firstName, :lastName, :userRole, :email, :phone)";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("userId", user.getUserId());
		paramMap.put("firstName", user.getFirstName());
		paramMap.put("lastName", user.getLastName());
		paramMap.put("userRole", user.getRole());
		paramMap.put("email", user.getEmail());
		paramMap.put("phone", user.getPhone());
		
		namedParameterJdbcTemplate.execute(query, paramMap, new PreparedStatementCallback<Object>() {
			
			public Object doInPreparedStatement(PreparedStatement ps) 
					throws SQLException, DataAccessException {
				return ps.executeUpdate();
			}
		});

	}

	public User validateUser(Login login) {
		
		String query = "select * from users where user_id = :userId and password = :password";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", login.getUserId());
		paramMap.put("password", login.getPassword());
		
		List<User> userList = namedParameterJdbcTemplate.query(query, paramMap, new RowMapper<User>() {
			
			public User mapRow(ResultSet rs, int rowNumber) throws SQLException {
				
				User user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getString("user_role"));
				
				return user;
			}
			
		});
		
		User returnUser = null;
		
		if(userList != null && userList.size() > 0) {
			System.out.println("User found");
			returnUser = userList.get(0);
		} else {
			System.out.println("User/password does not match");
		}
		
		return returnUser;
	}
	
/*	public List<User> getAllUsers() {
		String query = "select * from users";
		
		List<User> userList = jdbcTemplate.query(query, new RowMapper<User>() {
			
			public User mapRow(ResultSet rs, int rowNumber) throws SQLException {
				
				User user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getString("user_role"));
				
				return user;
			}
			
		});
		return userList;
	}
*/
	public List<User> getAllUsers() {
		String query = "select * from users";
		
		ArrayList<User> userList = (ArrayList<User>) jdbcTemplate.query(query, new BeanPropertyRowMapper<User>(User.class));
		return userList;
	}
}
