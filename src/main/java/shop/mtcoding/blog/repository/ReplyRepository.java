package shop.mtcoding.blog.repository;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blog.dto.ReplyWriteDTO;

// 메모리에 떠서 사용할 수 있는 것들
// UserController, BoardController, ReplyController, ErrorControoller
// (User,Board,Reply)Repository
// EntityManager, HttpSession, (스프링)
@Repository
public class ReplyRepository {

    @Autowired
    private EntityManager em;

    @Transactional
    public void save(ReplyWriteDTO replyWriteDTO, Integer userId) {
        Query query = em.createNativeQuery(
                "insert into reply_tb(comment, board_id, user_id) values(:comment, :boardId, :userId)");

        query.setParameter("boardId", replyWriteDTO.getBoardId());
        query.setParameter("comment", replyWriteDTO.getComment());
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

}
