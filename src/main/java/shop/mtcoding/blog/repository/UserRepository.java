package shop.mtcoding.blog.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blog.dto.JoinDTO;
import shop.mtcoding.blog.dto.LoginDTO;
import shop.mtcoding.blog.model.User;

//BoardController, UserController, UserRepository (내가 직접 띄움 IOC)
//EntityManager, HttpSession (Spring이 띄움)
@Repository
public class UserRepository {

    @Autowired
    private EntityManager em;

    public User findByUsernameAndPassword(LoginDTO loginDTO) {
        Query query = em.createNativeQuery("select * from user_tb where username=:username and password=:password",
                User.class);
        query.setParameter("username", loginDTO.getUsername());
        query.setParameter("username", loginDTO.getUsername());
        return (User) query.getSingleResult();
    }

    @Transactional
    public void save(JoinDTO joinDTO) {
        Query query = em.createNativeQuery(
                "insert into user_tb(username, password, email) values(:username, :password, :email)");
        query.setParameter("username", joinDTO.getUsername());
        query.setParameter("password", joinDTO.getPassword());
        query.setParameter("email", joinDTO.getEmail());
        query.executeUpdate();
    }

}
