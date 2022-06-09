package com.mvc.fraudmanagement.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.mvc.fraudmanagement.entities.Card;

class CardMapper implements RowMapper<Card> {

    @Override
    public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
        Card card = new Card();
        card.setId(rs.getInt("id"));
        card.setCardNo(rs.getLong("card_no"));
        card.setUserId(rs.getString("user_id"));
        card.setCardHolderName(rs.getString("card_holder_name"));
        card.setAccNo(rs.getLong("acc_no"));
        card.setExpiryDate(rs.getDate("expiry_date"));
        card.setCardType(rs.getString("card_type"));
        card.setTransDate(rs.getDate("trans_date"));
        card.setTransDetails(rs.getString("trans_details"));
        card.setRemarks(rs.getString("remarks"));
        card.setFraudLevel(rs.getString("fraud_level"));
        card.setBlocked(rs.getInt("blocked"));
        card.setIsAuthorized(rs.getInt("is_authorized"));
        return card;
    }
    
}

@Repository
public class CardRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CardRepository(DriverManagerDataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    public List<Card> findAll() {
        return jdbcTemplate.query("select * from card", new CardMapper());
    }

    public void save(Card card) {
        jdbcTemplate.update(
            "insert into card (" + 
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
                card.getCardNo(),
                card.getUserId(), 
                card.getCardHolderName(), 
                card.getAccNo(),
                card.getExpiryDate(), 
                card.getCardType(), 
                card.getTransDate(), 
                card.getTransDetails(), 
                card.getRemarks(), 
                card.getFraudLevel(), 
                card.getBlocked(), 
                card.getIsAuthorized()
            );
    }

    public void delete(Card card) {
        jdbcTemplate.execute(
            "delete from card where id = " + card.getId()            
        );
    }

}
