package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.ForResister;

@Repository
public class ForResisterRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<ForResister> RESISTER_ROW_MAPPER = (rs, i) -> {
		ForResister forResister = new ForResister();
		forResister.setId(rs.getInt("id"));
		forResister.setMail(rs.getString("mail"));
		forResister.setKey(rs.getString("key"));
		forResister.setResisterDateTime(rs.getTimestamp("resister_date").toLocalDateTime());
		forResister.setDoneflag(rs.getBoolean("doneflag"));
		return forResister;
	};

	public ForResister findByRmail(String mail) {
		String sql = "SELECT id, mail, key, resister_date, doneflag from forresister WHERE mail = :mail;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mail", mail);
		List<ForResister> forResisterList = template.query(sql, param, RESISTER_ROW_MAPPER);

		if (forResisterList.size() == 0) {
			return null;
		}
		return forResisterList.get(0);

	}

	public ForResister findByRkey(String key) {
		String sql = "SELECT id, mail, key, resister_date, doneflag from forresister WHERE key = :key;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("key", key);
		List<ForResister> forResisterList = template.query(sql, param, RESISTER_ROW_MAPPER);

		if (forResisterList.size() == 0) {
			return null;
		}
		return forResisterList.get(0);
	}

	public void insertForResister(ForResister forResister) {
		String sql = "INSERT INTO forresister (mail, key, resister_date, doneflag) VALUES(:mail, :key, :resisterDateTime, :doneflag);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(forResister);
		template.update(sql, param);

	}

	public void updateForResister(ForResister forResister) {
		String sql = "UPDATE forresister SET key = :key, resister_date = :resisterDateTime, doneflag = :doneflag WHERE mail = :mail;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(forResister);
		template.update(sql, param);
	}

}
