package kr.java.join.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @Column(length = 20)
    private String nickname;

    // 양방향 연관관계
//    @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
    // mappedBy -> 연관관계의 주인이 아니고, UserAccount.profile 필드.
    // -> 생성자나 setter로 추가할 수 없고 읽기 전용.
    @OneToOne(mappedBy = "profile", fetch = FetchType.EAGER)
    private UserAccount userAccount;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    protected UserProfile() {}

    public UserProfile(String nickname) {
        this.nickname = nickname;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    @Override
    public String toString() {
//        return "UserProfile{" +
//                "profileId=" + profileId +
//                ", nickname='" + nickname + '\'' +
//                ", userAccount=" + userAccount +
//                '}'; // userAccount
        return "UserProfile{" +
                "profileId=" + profileId +
                ", nickname='" + nickname + '\'' +
                '}'; // userAccount
    }
}
