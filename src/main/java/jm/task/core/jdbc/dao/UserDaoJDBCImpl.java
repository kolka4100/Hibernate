package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Connection connection = new Util().getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE test(" +
                    "id BIGINT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(45)," +
                    "lastName VARCHAR(45)," +
                    "age BINARY," +
                    "PRIMARY KEY(id));");
            connection.commit();
        }catch (SQLException e){}
        finally {
            connection.close();
        }
    }

    public void dropUsersTable() throws SQLException {
        Connection connection = new Util().getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE test");
            connection.commit();
        } catch (SQLException e){}
        finally {
            connection.close();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = new Util().getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO test(name, lastName, age) VALUES(?, ?, ?);");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            connection.commit();
        }catch (SQLException e){}
        finally {
            connection.close();
        }
    }

    public void removeUserById(long id) throws SQLException {
        Connection connection = new Util().getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM test WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        }catch (SQLException e){}
        finally {
            connection.close();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> result = new ArrayList<>();
        Connection connection = new Util().getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM test");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                System.out.println(id);
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastName);
                user.setAge(age);
                result.add(user);
                connection.commit();
            }
        }catch (SQLException e){}
        finally {
            connection.close();
        }
        return result;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connection = new Util().getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM test");
            connection.commit();
        } catch (SQLException e){}
        finally {
            connection.close();
        }
    }
}