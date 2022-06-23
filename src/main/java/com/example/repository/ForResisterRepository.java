package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.ForResister;

@Repository
public class ForResisterRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<ForResister> Resister_ROW_MAPPER = (rs,i) -> {
		ForResister forResister = new ForResister();
		forResister.setId(rs.getInt("id"));
		forResister.setMail(rs.getString("mail"));
		forResister.setKey(rs.getString("key"));
		forResister.setDate(rs.getDate("date"));
		forResister.setDone(rs.getBoolean("done"));
	};

	public void findByEmail(String mail) {
		String sql = "SELECT id,mail,key,date,done from forresister WHERE mail = :mail;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mail", mail);
		ForResister forResister = template.query(sql, param,)

	}

}
