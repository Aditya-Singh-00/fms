package com.mvc.fraudmanagement.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.mvc.fraudmanagement.entities.User;

class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUserId(rs.getString("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setDob(rs.getDate("dob"));
        user.setGender(rs.getString("gender"));
        user.setContactNo(rs.getString("contact_no"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setIsAuthorized(rs.getInt("is_authorized"));
        return user;
    }

}

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(DriverManagerDataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", new UserMapper());
    }

    public void save(User user) {
        jdbcTemplate.update(
            "insert into users (" + 
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
                user.getUserId(),
                user.getFirstName(), 
                user.getLastName(), 
                user.getDob(),
                user.getGender(), 
                user.getContactNo(), 
                user.getEmail(), 
                user.getPassword(), 
                user.getIsAuthorized()
            );
    }

}