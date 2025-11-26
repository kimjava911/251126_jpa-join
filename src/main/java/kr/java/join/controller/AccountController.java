package kr.java.join.controller;

import kr.java.join.model.entity.Post;
import kr.java.join.model.entity.Recommend;
import kr.java.join.model.entity.UserAccount;
import kr.java.join.model.entity.UserProfile;
import kr.java.join.model.repository.PostRepository;
import kr.java.join.model.repository.RecommendRepository;
import kr.java.join.model.repository.UserAccountRepository;
import kr.java.join.model.repository.UserProfileRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class AccountController {
    private final UserAccountRepository accountRepository;
    private final UserProfileRepository profileRepository;
    private final PostRepository postRepository;
    private final RecommendRepository recommendRepository;

    public AccountController(UserAccountRepository accountRepository, UserProfileRepository profileRepository, PostRepository postRepository, RecommendRepository recommendRepository) {
        this.accountRepository = accountRepository;
        this.profileRepository = profileRepository;
        this.postRepository = postRepository;
        this.recommendRepository = recommendRepository;
    }

    @GetMapping
    public String page(Model model) {
        model.addAttribute("accounts", accountRepository.findAll());
        model.addAttribute("posts", postRepository.findAll());
        model.addAttribute("recommends", recommendRepository.findAll());
        return "account";
    }

    @PostMapping
    public String create(
            @RequestParam("username") String username, // Account
            @RequestParam("nickname") String nickname // Profile
    ) {
        // 1단계
        UserProfile profile = new UserProfile(nickname);
        profileRepository.save(profile);
        // 2단계
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        userAccount.setProfile(profile);
        accountRepository.save(userAccount);
        return "redirect:/";
    }

    @PostMapping("/post")
    public String createPost(
            @RequestParam("content") String content,
            @RequestParam("accountId") long accountId
    ) {
        // 1단계
        UserAccount account = accountRepository.findById(accountId);
        // 2단계
        postRepository.save(new Post(content, account));
        return "redirect:/";
    }

    @PostMapping("/recommend")
    public String recommend(
            @RequestParam("postId") long postId,
            @RequestParam("accountId") long accountId
    ) {
        Post p = postRepository.findById(postId);
        UserAccount a = accountRepository.findById(accountId);
        recommendRepository.save(new Recommend(a, p));
        return "redirect:/";
    }
}
