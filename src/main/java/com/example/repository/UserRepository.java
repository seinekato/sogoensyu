package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setHurigana(rs.getString("hurigana"));
		user.setZipCode(rs.getString("zip_code"));
		user.setAddress(rs.getString("address"));
		user.setTel(rs.getString("tel"));
		user.setPassword(rs.getString("password"));
		user.setMail(rs.getString("mail"));
		user.setResisterUser(rs.getInt("resister_user"));
		user.setResisterDateTime(rs.getTimestamp("resister_date").toLocalDateTime());
		user.setUpdateUser(rs.getInt("update_user"));
		user.setUpdateDateTime(rs.getTimestamp("update_date").toLocalDateTime());
		user.setDeleteflag(rs.getBoolean("deleteflag"));
		return user;
	};

	public User findByUmail(String mail) {
		String sql = "SELECT id,name,hurigana,zip_code,address,tel,password,mail,"
				+ " resister_user,resister_date,update_user,update_date,deleteflag FROM users WHERE mail = :mail;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mail", mail);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);

		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);

	}

	public void insertUser(User user) {
		String sql = "INSERT INTO users (name, hurigana, zip_code, address, tel, password, mail, resister_date, update_date, deleteflag) VALUES(:name, :hurigana, :zipCode, :address, :tel, :password, :mail, :resisterDateTime, :updateDateTime, :deleteflag);";
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		template.update(sql, param);
	}

}
