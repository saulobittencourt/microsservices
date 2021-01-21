package com.ielop.auth_service.controller;

import com.ielop.auth_service.model.AuthUserDetails;
import com.ielop.auth_service.model.Profile;
import com.ielop.auth_service.model.User;
import com.ielop.auth_service.payload.JwtAuthentication;
import com.ielop.auth_service.payload.Login;
import com.ielop.auth_service.payload.SignUp;
import com.ielop.auth_service.payload.UserSummary;
import com.ielop.auth_service.repo.UserRepo;
import com.ielop.auth_service.service.JwtTokenService;
import com.ielop.auth_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.ielop.auth_service.util.Tools.createDir;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UserRepo userRepo;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthentication> authenticateUser(@Valid @RequestBody Login loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenService.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthentication(jwt));
    }

    @PostMapping
    public ResponseEntity<User> save(@Valid @RequestBody SignUp signUpRequest){
        User user = User
                .builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .userProfile(Profile
                        .builder()
                        .displayName(signUpRequest.getName())
                        .build())
                .build();

        userService.save(user);

        createDir(user);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/picture")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> updateProfilePicture(@RequestBody String profilePicture, @AuthenticationPrincipal AuthUserDetails authUserDetails){
        return ResponseEntity.ok(userService.updateProfilePicture(profilePicture, authUserDetails.getId()));
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.findByUsername(username).get());
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserSummary> getCurrentUser(@AuthenticationPrincipal AuthUserDetails authUserDetails){
        return ResponseEntity.ok(
                UserSummary.builder()
                        .id(authUserDetails.getId())
                        .username(authUserDetails.getUsername())
                        .name(authUserDetails.getUserProfile().getDisplayName())
                        .profilePicture(authUserDetails.getUserProfile().getProfilePictureUrl())
                        .build()
        );
    }

}
