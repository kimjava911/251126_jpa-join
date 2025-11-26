package kr.java.join.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import kr.java.join.model.entity.UserAccount;
import kr.java.join.model.entity.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserProfileRepository {
    private final EntityManagerFactory emf;

    public UserProfileRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // 생성
    public void save(UserProfile userProfile) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (userProfile.getProfileId() == null) { // 신규
                em.persist(userProfile); // 등록
            } else {
                em.merge(userProfile);
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
}
