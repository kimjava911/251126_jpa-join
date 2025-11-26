package kr.java.join.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import kr.java.join.model.entity.UserAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserAccountRepository {
    private final EntityManagerFactory emf;

    public UserAccountRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // 생성
    public void save(UserAccount userAccount) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (userAccount.getAccountId() == null) { // 신규
                em.persist(userAccount); // 등록
            } else {
                em.merge(userAccount);
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
    public List<UserAccount> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "select u from UserAccount u", UserAccount.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("조회 실패");
        } finally {
            em.close();
        }
    }
}
