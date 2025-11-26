package kr.java.join.controller;

import kr.java.join.model.entity.UserAccount;
import kr.java.join.model.entity.UserProfile;
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

    public AccountController(UserAccountRepository accountRepository, UserProfileRepository profileRepository) {
        this.accountRepository = accountRepository;
        this.profileRepository = profileRepository;
    }

    @GetMapping
    public String page(Model model) {
        model.addAttribute("accounts", accountRepository.findAll());
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
}
