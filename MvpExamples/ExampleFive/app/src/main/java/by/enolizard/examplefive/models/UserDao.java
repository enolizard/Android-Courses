package by.enolizard.examplefive.models;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Query("SELECT COUNT(*) FROM users WHERE email = :email")
    int countByEmail(String email);

    @Query("SELECT * FROM users WHERE email = :email AND pass = :password")
    User findUser(String email, String password);

    @Insert
    void addUser(User user);
}
