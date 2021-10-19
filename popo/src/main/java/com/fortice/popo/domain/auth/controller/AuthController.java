package com.fortice.popo.domain.auth.controller;
import com.fortice.popo.domain.auth.dto.SignInRequest;
import com.fortice.popo.domain.auth.dto.SignUpRequest;
import com.fortice.popo.domain.auth.dto.TokenRequest;
import com.fortice.popo.domain.auth.service.AuthService;
import com.fortice.popo.global.common.TokenDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @ApiOperation("가입하기")
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest) throws Exception{
        String email = authService.signup(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(email + " 회원가입이 완료되었습니다.");
    }

    @ApiOperation("로그인")
    @PostMapping("/signin")
    public ResponseEntity<TokenDTO> signin(@RequestBody SignInRequest signInRequest) {
        TokenDTO tokenDTO = authService.signin(signInRequest);
        return ResponseEntity.ok(tokenDTO);
    }

    @ApiOperation("로그인 토큰 재발급")
    @PostMapping("/reissue")
    public ResponseEntity<TokenDTO> reissue(@RequestBody TokenRequest tokenRequest) throws Exception{
        return ResponseEntity.ok(authService.reissue(tokenRequest));
    }

    @ApiOperation("중복 체크")
    @PutMapping("/check")
    public ResponseEntity<Boolean> check(@RequestParam(value = "email") @NotNull String email) throws Exception{
        return ResponseEntity.ok(authService.validate(email));
    }
}
