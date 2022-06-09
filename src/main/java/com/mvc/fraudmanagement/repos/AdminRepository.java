package com.mvc.fraudmanagement.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.mvc.fraudmanagement.entities.Admin;

class AdminMapper implements RowMapper<Admin> {

    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Admin admin = new Admin();
        admin.setUserId(rs.getString("user_id"));
        admin.setFirstName(rs.getString("first_name"));
        admin.setLastName(rs.getString("last_name"));
        admin.setDob(rs.getDate("dob"));
        admin.setGender(rs.getString("gender"));
        admin.setContact(rs.getString("contact_no"));
        admin.setPassword(rs.getString("password"));
        return admin;
    }

}

@Repository
public class AdminRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminRepository(DriverManagerDataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    public List<Admin> findAll() {
        return jdbcTemplate.query("select * from admin", new AdminMapper());
    }

    public void save(Admin admin) {
        jdbcTemplate.execute(
                "insert into admin values('" +
                        admin.getFirstName() + "','" +
                        admin.getLastName() + "','" +
                        new java.sql.Date(admin.getDob().getTime()) + "','" +
                        admin.getGender() + "','" +
                        admin.getContact() + "','" +
                        admin.getUserId() + "','" +
                        admin.getPassword() + "')");
    }

}
