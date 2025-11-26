package kr.java.join.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
//    @Override
//    public String toString() {
//        return "UserAccount{" +
//                "accountId=" + accountId +
//                ", username='" + username + '\'' +
//                ", profile=" + profile +
//                '}';
//    }


    @Override
    public String toString() {
        return "UserAccount{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                ", profile=" + profile +
                ", posts=" + posts +
                '}';
    }

    @OneToMany(mappedBy = "userAccount",
            cascade = CascadeType.ALL, // UserAccount 변동사항이 생기면 -> 파생.
            orphanRemoval = true, // UserAccount가 삭제 되었을 때 fk의 pk를 잃어버린 post를 자동 삭제
//            fetch = FetchType.LAZY
            fetch = FetchType.EAGER
    )
    private List<Post> posts = new ArrayList<>();

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post post) {
        posts.add(post);
        post.setUserAccount(this);
    }

    // M:N
//    @ManyToMany
//    @JoinTable(
//        name = "recommend", // 중간/중개 테이블
//        joinColumns = @JoinColumn(name = "account_id"), // 내 PK
//        inverseJoinColumns = @JoinColumn(name = "post_id") // 상대방쪽 PK
//    )
//    private List<Post> recommendPosts = new ArrayList<>();
//
//    public List<Post> getRecommendPosts() {
//        return recommendPosts;
//    }
//
//    public void setRecommendPosts(List<Post> recommendPosts) {
//        this.recommendPosts = recommendPosts;
//    }
}
