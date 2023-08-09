package shop.mtcoding.blog.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blog.dto.JoinDTO;
import shop.mtcoding.blog.dto.LoginDTO;
import shop.mtcoding.blog.dto.UpdateDTO;
import shop.mtcoding.blog.model.Board;
import shop.mtcoding.blog.model.User;

// BoardController, UserController, UserRepository
// EntityManager, HttpSession
@Repository
public class UserRepository {

    @Autowired
    private EntityManager em;

    public User findByUsername(LoginDTO loginDTO) {
        try {
            Query query = em.createNativeQuery("select * from user_tb where username=:username",
                    User.class);
            query.setParameter("username", loginDTO.getUsername());
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void save(JoinDTO joinDTO) {

        String encPassword = BCrypt.hashpw(joinDTO.getPassword(), BCrypt.gensalt());
        System.out.println("encPassword : " + encPassword);

        Query query = em
                .createNativeQuery(
                        "insert into user_tb(username, password, email) values(:username, :password, :email)");
        query.setParameter("username", joinDTO.getUsername());
        query.setParameter("password", encPassword);
        query.setParameter("email", joinDTO.getEmail());

        query.executeUpdate();
    }

    public static void update(UpdateDTO updateDTO, Integer id) {
    }

    public User findById(Integer id) {
        Query query = em.createNativeQuery("select * from board_tb where id = :id", User.class);
        query.setParameter("id", id);
        User user = (User) query.getSingleResult();
        return user;
    }
}