package my.gov.imi.niise_demo_hazieq;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import my.gov.imi.niise_demo_hazieq.util.JwtUtil;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1")
public class LoginController {

    private final JwtUtil jwtTokenUtil;
    private final LoginService loginService;

    /**
     * Login pengguna
     *
     * POST http://localhost:8080/api/v1/login
     */
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> authenticate(
            @Valid @RequestBody LoginRequest req) throws Exception {

        Pengguna user;

        try {
            user = loginService.authenticate(
                    req.getEmail(),
                    req.getPassword()
            );

        } catch (BadCredentialsException e) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Error: Incorrect username or password");
        }

        // Jana JWT token
        var userDetails =
                loginService.loadUserByUsername(
                        user.getEmail()
                );

        String jwt =
                jwtTokenUtil.generateToken(userDetails);

        // Response login
        return ResponseEntity.ok(
                Map.of(
                        "token", jwt,
                        "nama", user.getNama(),
                        "icno", user.getIcno(),
                        "email", user.getEmail(),
                        "hashPassword", user.getHashPassword()
                )
        );
    }
}