//package com.fortice.popo.domain.user.controller;
//
//public class UserController {
//    private final AuthService authService;
//
//    @ApiOperation("가입하기")
//    @PostMapping("/signup")
//    public ResponseEntity<String> signup(@RequestBody SignupReqDto signupReqDto) {
//        String email = authService.signup(signupReqDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(email + " 회원가입이 완료되었습니다.");
//    }
//
//    @ApiOperation("로그인")
//    @PostMapping("/signin")
//    public ResponseEntity<TokenDto> signin(@RequestBody SigninReqDto signinReqDto) {
//        TokenDto tokenDto = authService.signin(signinReqDto);
//        return ResponseEntity.ok(tokenDto);
//    }
//
//    @ApiOperation("로그인 토큰 재발급")
//    @PostMapping("/reissue")
//    public ResponseEntity<TokenDto> reissue(@RequestBody TokenReqDto tokenReqDto) {
//        return ResponseEntity.ok(authService.reissue(tokenReqDto));
//    }
//
//    @ApiOperation("중복 체크")
//    @PutMapping("/check")
//    public ResponseEntity<Boolean> check(@RequestParam(value = "target") @NotNull String target) {
//        return ResponseEntity.ok(authService.validate(target));
//    }
//}
