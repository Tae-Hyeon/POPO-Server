package com.fortice.popo.domain.auth.service;

import com.fortice.popo.domain.model.RefreshToken;
import com.fortice.popo.domain.model.User;
import com.fortice.popo.domain.auth.dto.SignInRequest;
import com.fortice.popo.domain.auth.dto.SignUpRequest;
import com.fortice.popo.domain.auth.dto.TokenRequest;
import com.fortice.popo.domain.auth.repository.UserRepository;
import com.fortice.popo.global.common.TokenDTO;
import com.fortice.popo.global.error.exception.AlreadyCreatedException;
import com.fortice.popo.global.error.exception.NotFoundDataException;
import com.fortice.popo.global.error.exception.TokenValidFailedException;
import com.fortice.popo.domain.auth.repository.RefreshTokenRepository;
import com.fortice.popo.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public String signup(SignUpRequest request) throws Exception{
        User user = request.toUser(passwordEncoder);
        Optional<User> opUser = userRepository.findUserByEmail(request.getEmail());
        if (opUser.isPresent()) {
            //log.error("이미 존재하는 유저입니다.");
            throw new AlreadyCreatedException();
        }
        return userRepository.save(user).getEmail();
    }

    @Transactional
    public TokenDTO signin(SignInRequest request) {
        // 1. username, password 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = request.toAuthenticationToken();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDTO tokenDTO = tokenProvider.generateTokenDTO(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDTO.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return tokenDTO;
    }

    @Transactional
    public TokenDTO reissue(TokenRequest tokenReqDto) throws Exception{
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenReqDto.getRefreshToken())) {
            throw new TokenValidFailedException();
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenReqDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(NotFoundDataException::new);

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenReqDto.getRefreshToken())) {
            throw new TokenValidFailedException();
        }

        // 5. 새로운 토큰 생성
        TokenDTO tokenDTO = tokenProvider.generateTokenDTO(authentication);

        // 6. 저장소 정보 업데이트
        refreshToken.updateValue(tokenDTO.getRefreshToken());

        // 토큰 발급
        return tokenDTO;
    }

    public boolean validate(String target) throws Exception{
        if(target.contains("@"))
            return checkEmail(target);
        return false;
    }

    private boolean checkEmail(String email) throws Exception{
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) { throw new AlreadyCreatedException(); }
        return true;
    }
}