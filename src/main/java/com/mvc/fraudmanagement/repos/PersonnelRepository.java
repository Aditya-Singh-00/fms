package com.mvc.fraudmanagement.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.mvc.fraudmanagement.entities.Personnel;

class PersonnelMapper implements RowMapper<Personnel> {

    @Override
    public Personnel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Personnel personnel = new Personnel();
        personnel.setId(rs.getInt("id"));
        personnel.setUserId(rs.getString("user_id"));
        personnel.setFirstName(rs.getString("first_name"));
        personnel.setLastName(rs.getString("last_name"));
        personnel.setDob(rs.getDate("dob"));
        personnel.setGender(rs.getString("gender"));
        personnel.setContactNo(rs.getString("contact_no"));
        personnel.setEmail(rs.getString("email"));
        personnel.setPassword(rs.getString("password"));
        personnel.setIsAuthorized(rs.getInt("is_authorized"));
        return personnel;
    }

}

@Repository
public class PersonnelRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonnelRepository(DriverManagerDataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    public List<Personnel> findAll() {
        return jdbcTemplate.query("select * from personnel", new PersonnelMapper());
    }

    public void save(Personnel personnel) {
        jdbcTemplate.update(
            "insert into personnel (" + 
                "user_id, " + 
                "first_name, " + 
                "last_name, " + 
                "dob, " + 
                "gender, " + 
                "contact_no, " + 
                "email, " + 
                "password, " +
                "is_authorized) " +
                "values (?,?,?,?,?,?,?,?,?)",
                personnel.getUserId(),
                personnel.getFirstName(), 
                personnel.getLastName(), 
                personnel.getDob(),
                personnel.getGender(), 
                personnel.getContactNo(), 
                personnel.getEmail(), 
                personnel.getPassword(), 
                personnel.getIsAuthorized()
            );
    }

}
