package kr.java.join.model.entity;

import jakarta.persistence.*;

@Entity
// N:1
@Table(name = "posts")
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false, length = 2000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false) // post -> fk.
    private UserAccount userAccount;

    protected Post() {}

    public Post(String content, UserAccount account) {
        this.content = content;
        this.userAccount = account;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
