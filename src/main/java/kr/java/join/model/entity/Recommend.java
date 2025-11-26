package kr.java.join.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "recommend")
public class Recommend {
    @Override
    public String toString() {
        return "Recommend{" +
                "recommendId=" + recommendId +
                ", userAccount=" + userAccount +
                ", post=" + post +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommend_id")
    private Long recommendId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    protected Recommend() {}

    public Recommend(UserAccount userAccount, Post post) {
        this.userAccount = userAccount;
        this.post = post;
    }

    public Long getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Long recommendId) {
        this.recommendId = recommendId;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
