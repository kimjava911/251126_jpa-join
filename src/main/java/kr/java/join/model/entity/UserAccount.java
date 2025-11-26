package kr.java.join.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_account")
public class UserAccount {
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long accountId;

    @Column(nullable = false, unique = true)
    private String username;

    // 기본생성자만 필요할 시에는 별도로 만들어주지 X.

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // 1:1 매핑
    @OneToOne(fetch = FetchType.LAZY) // 1:1 단방향 매핑 (FK를 가질 입장에서 작성)
    @JoinColumn(name = "profile_id") // FK 컬럼명
    private UserProfile profile;

    // Getter, Setter

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }
}
