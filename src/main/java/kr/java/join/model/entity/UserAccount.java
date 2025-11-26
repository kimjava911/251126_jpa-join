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
    // Spring Boot <- 금방 세팅이 됨 / Spring
//    @OneToOne(fetch = FetchType.LAZY) // 1:1 단방향 매핑 (FK를 가질 입장에서 작성)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id") // FK 컬럼명
    private UserProfile profile;

    // Getter, Setter

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    // 객체가 String을 취급을 받을 때 어떻게 출력될 것인가
    // -> 없으면 -> 객체 주소...
    @Override
    public String toString() {
        return "UserAccount{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                ", profile=" + profile +
                '}';
    }
}
