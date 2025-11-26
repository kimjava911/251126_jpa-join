package kr.java.join.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import kr.java.join.model.entity.Recommend;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecommendRepository {
    private final EntityManagerFactory emf;

    public RecommendRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // 생성
    public void save(Recommend recommend) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(recommend);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("추가 실패");
        } finally {
            em.close();
        }
    }

    // 전체조회
    public List<Recommend> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "select r from Recommend r", Recommend.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("조회 실패");
        } finally {
            em.close();
        }
    }
}
