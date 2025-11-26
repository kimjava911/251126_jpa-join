package kr.java.join.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import kr.java.join.model.entity.Post;
import kr.java.join.model.entity.UserAccount;
import kr.java.join.model.entity.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {
    private final EntityManagerFactory emf;

    public PostRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // 생성
    public void save(Post post) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (post.getPostId() == null) { // 신규
                em.persist(post); // 등록
            } else {
                em.merge(post);
            }
            // upsert -> [up]date + in[sert]
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("추가 실패");
        } finally {
            em.close();
        }
    }

    // 전체조회
    public List<Post> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "select p from Post p", Post.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("조회 실패");
        } finally {
            em.close();
        }
    }

    // 개별조회
    public Post findById(long postId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Post.class, postId);
        } catch (Exception e) {
            throw new RuntimeException("조회 실패");
        } finally {
            em.close();
        }
    }
}
