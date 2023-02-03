package jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleJDBCRepository
{
    private Connection connection = null;
    private PreparedStatement ps = null;
    private Statement st = null;

    private static final String createUserSQL = "INSERT INTO users (firstname, lastname, age) VALUES (?,?,?)";
    private static final String updateUserSQL = "UPDATE users SET firstname=?, lastname=?, age=? WHERE id=?";
    private static final String deleteUser = "DELETE FROM users WHERE id=?";
    private static final String findUserByIdSQL = "SELECT * FROM users WHERE id=?";
    private static final String findUserByNameSQL = "SELECT * FROM users WHERE firstname=?";
    private static final String findAllUserSQL = "SELECT * FROM users";

    public Long createUser(User createdUser)
    {
        try
        {
            connection = CustomDataSource.getInstance().getConnection();
            ps = connection.prepareStatement(createUserSQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, createdUser.getFirstName());
            ps.setString(2, createdUser.getFirstName());
            ps.setInt(3, createdUser.getAge());
            return (long) ps.executeUpdate();

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public User findUserById(Long userId)
    {
        User user = null;
        try
        {
            connection = CustomDataSource.getInstance().getConnection();
            ps = connection.prepareStatement(findUserByIdSQL);
            ps.setLong(1, userId);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastName(resultSet.getString("lastname"));
            user.setAge(resultSet.getInt("age"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }

    public User findUserByName(String userName)
    {
        User user = null;
        try
        {
            connection = CustomDataSource.getInstance().getConnection();
            ps = connection.prepareStatement(findUserByNameSQL);
            ps.setString(1, userName);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastName(resultSet.getString("lastname"));
            user.setAge(resultSet.getInt("age"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> findAllUser()
    {
        List<User> users = new ArrayList<>();
        try
        {
            connection = CustomDataSource.getInstance().getConnection();
            ps = connection.prepareStatement(findAllUserSQL);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getInt("age"));

                users.add(user);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return users;
    }

    public User updateUser(User updatedUser)
    {
        try
        {
            connection = CustomDataSource.getInstance().getConnection();
            ps = connection.prepareStatement(updateUserSQL);

            ps.setString(1, updatedUser.getFirstName());
            ps.setString(2, updatedUser.getFirstName());
            ps.setInt(3, updatedUser.getAge());
            ps.setLong(4, updatedUser.getId());

            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return updatedUser;
    }

    private void deleteUser(Long userId)
    {
        try
        {
            connection = CustomDataSource.getInstance().getConnection();
            ps = connection.prepareStatement(deleteUser);

            ps.setLong(1, userId);

            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
