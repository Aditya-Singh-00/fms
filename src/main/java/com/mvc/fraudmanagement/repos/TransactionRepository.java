package com.mvc.fraudmanagement.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.mvc.fraudmanagement.entities.Transaction;

class TransactionMapper implements RowMapper<Transaction> {

    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(rs.getInt("id"));
        transaction.setCardNo(rs.getLong("card_no"));
        transaction.setUserId(rs.getString("user_id"));
        transaction.setCardHolderName(rs.getString("card_holder_name"));
        transaction.setAccNo(rs.getLong("acc_no"));
        transaction.setExpiryDate(rs.getDate("expiry_date"));
        transaction.setCardType(rs.getString("card_type"));
        transaction.setTransDate(rs.getDate("trans_date"));
        transaction.setTransDetails(rs.getString("trans_details"));
        transaction.setRemarks(rs.getString("remarks"));
        transaction.setFraudLevel(rs.getString("fraud_level"));
        transaction.setBlocked(rs.getInt("blocked"));
        transaction.setIsAuthorized(rs.getInt("is_authorized"));
        return transaction;
    }

}

@Repository
public class TransactionRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TransactionRepository(DriverManagerDataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    public List<Transaction> findAll() {
        return jdbcTemplate.query("select * from transaction", new TransactionMapper());
    }

    public void save(Transaction transaction) {
        jdbcTemplate.update(
            "insert into transaction (" + 
                "card_no, " + 
                "user_id, " + 
                "card_holder_name, " + 
                "acc_no, " + 
                "expiry_date, " + 
                "card_type, " + 
                "trans_date, " + 
                "trans_details, " +
                "remarks, " +
                "fraud_level, " + 
                "blocked, " + 
                "is_authorized) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?)",
                transaction.getCardNo(),
                transaction.getUserId(), 
                transaction.getCardHolderName(), 
                transaction.getAccNo(),
                transaction.getExpiryDate(), 
                transaction.getCardType(), 
                transaction.getTransDate(), 
                transaction.getTransDetails(), 
                transaction.getRemarks(), 
                transaction.getFraudLevel(), 
                transaction.getBlocked(), 
                transaction.getIsAuthorized()
            );
    }

    public void delete(Transaction transaction) {
        jdbcTemplate.execute(
            "delete from transaction where id = " + transaction.getId()            
        );
    }
}