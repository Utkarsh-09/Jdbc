package repository;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl extends JdbcDaoSupport implements UserRepository {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void insertUser(User user) {
        String sql = "INSERT INTO user_info " + "(user_id, first_name, last_name, email, password, mobile, address1, address2) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        getJdbcTemplate().update(sql, new Object[] {
                user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getMobileNumber(), user.getAddress1(), user.getAddress2()
        });
    }

    @Override
    public void insertUser(List<User> userList) {
        String sql = "INSERT INTO user_info " + "(user_id, first_name, last_name, email, password, mobile, address1, address2) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                User user = userList.get(i);
                preparedStatement.setString(1,Integer.toString(user.getId()));
                preparedStatement.setString(2,user.getFirstName());
                preparedStatement.setString(3,user.getLastName());
                preparedStatement.setString(4,user.getEmail());
                preparedStatement.setString(5,user.getPassword());
                preparedStatement.setString(6,user.getMobileNumber());
                preparedStatement.setString(7,user.getAddress1());
                preparedStatement.setString(8,user.getAddress2());
            }

            @Override
            public int getBatchSize() {
                return userList.size();
            }
        });
    }

    @Override
    public List<User> getAllUser() {
        String sql = "SELECT * FROM user_info";
        List<Map<String,Object>> rows = getJdbcTemplate().queryForList(sql);

        List<User> result = new ArrayList<>();
        for (Map<String,Object> row: rows) {
            User user = new User();
            user.setId((Integer) row.get("user_id"));
            user.setFirstName((String) row.get("first_name"));
            user.setLastName((String) row.get("last_name"));
            user.setEmail((String) row.get("email"));
            user.setPassword((String) row.get("password"));
            user.setMobileNumber((String) row.get("mobile"));
            user.setAddress1((String) row.get("address1"));
            user.setAddress2((String) row.get("address2"));
            result.add(user);
        }
        return result;
    }

    @Override
    public User getUserById(String userId) {

        String sql = "SELECT * FROM user_info WHERE user_id = ?";
        return (User) getJdbcTemplate().queryForObject(sql, new Object[]{userId}, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(Integer.parseInt(resultSet.getString("user_id")));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setMobileNumber(resultSet.getString("mobile"));
                user.setAddress1(resultSet.getString("address1"));
                user.setAddress2(resultSet.getString("address2"));
                return user;
            }
        });
    }
}
