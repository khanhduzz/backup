package nashtech.khanhdu.backend.services.impl;

import jakarta.validation.Valid;
import nashtech.khanhdu.backend.dto.JwtToken;
import nashtech.khanhdu.backend.dto.SignInDto;
import nashtech.khanhdu.backend.entities.User;
import nashtech.khanhdu.backend.jwt.TokenProvider;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public AuthController (AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }


    @PostMapping(
            path = "/auth/signin",
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })

    public ResponseEntity<JwtToken> signIn (@RequestBody @Valid SignInDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authUser = authenticationManager.authenticate(usernamePassword);
        var accessToken = tokenProvider.generateAccessToken((User) authUser.getPrincipal());
        return ResponseEntity.ok(new JwtToken(accessToken));
    }


    @GetMapping("/me")
    ResponseEntity<String> me (Authentication authentication) {
        if (authentication.getPrincipal() instanceof User user) {
            return ResponseEntity.ok(user.getUsername());
        }
        return ResponseEntity.badRequest().build();
    }
}
