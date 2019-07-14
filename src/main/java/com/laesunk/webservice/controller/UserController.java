package com.laesunk.webservice.controller;

import com.laesunk.webservice.domain.user.User;
import com.laesunk.webservice.domain.user.UserRepository;
import com.laesunk.webservice.response.Common;
import com.laesunk.webservice.response.ListResult;
import com.laesunk.webservice.response.SingleResult;
import com.laesunk.webservice.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Api(tags = {"1. User"})
@RequiredArgsConstructor
@RestController // 결과값을 JSON으로 출력합니다.
@RequestMapping(value = "/v1")
public class UserController {
    private final UserRepository userJpaRepo;
    private final ResponseService responseService; // 결과를 처리할 Service

    @ApiOperation(value = "회원 조회", notes = "모든 회원을 조회한다")
    @GetMapping(value = "/user")
    public ListResult<User> findAllUser() {
        return responseService.getListResult(userJpaRepo.findAll());
    }

    @ApiOperation(value = "회원 msrl 단건 조회", notes = "msrl로 회원을 조회한다")
    @GetMapping(value = "/user/msrl/{msrl}")
    public SingleResult<User> findUserById(@ApiParam(value = "msrl", required = true) @PathVariable long msrl) {
        // 결과데이터가 단일건인경우 getBasicResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userJpaRepo.findByMsrl(msrl));
    }

    @ApiOperation(value = "회원 id 단건 조회", notes = "userId로 회원을 조회한다")
    @GetMapping(value = {"/user/id/{uid}"} )
    public SingleResult<User> findUserByUiD(@ApiParam(value = "uid", required = true) @PathVariable String uid) {
        // 결과데이터가 단일건인경우 getBasicResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userJpaRepo.findByUid(uid));
    }

    @ApiOperation(value = "회원 입력", notes = "회원을 입력한다")
    @PostMapping(value = "/user")
    public SingleResult<User> save(@ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
                                   @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
        User user = User.builder()
                .uid(uid)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다")
    @PutMapping(value = "/user")
    public SingleResult<User> modify(
            @ApiParam(value = "회원번호", required = true) @RequestParam long msrl,
            @ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
            @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
        User user = User.builder()
                .msrl(msrl)
                .uid(uid)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 삭제", notes = "userId로 회원정보를 삭제한다")
    @DeleteMapping(value = "/user/{msrl}")
    @Transactional
    public Common delete(
            @ApiParam(value = "회원번호", required = true) @PathVariable long msrl) {
        userJpaRepo.deleteByMsrl(msrl);
        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
        return responseService.getSuccessResult();
    }

}
