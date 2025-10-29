package com.furkan.tradeport.controller;

import com.furkan.tradeport.command.LoginUserCommand;
import com.furkan.tradeport.command.RegisterUserCommand;
import com.furkan.tradeport.dto.DeleteUserResponse;
import com.furkan.tradeport.dto.LoginResponse;
import com.furkan.tradeport.dto.RegisterRequest;
import com.furkan.tradeport.dto.RegisterResponse;
import com.furkan.tradeport.model.AuthenticatedUser;
import com.furkan.tradeport.usecase.DeleteUserUseCase;
import com.furkan.tradeport.usecase.LoginUserUseCase;
import com.furkan.tradeport.usecase.RegisterUserUseCase;
import com.furkan.tradeport.valueobject.UserId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase, LoginUserUseCase loginUserUseCase, DeleteUserUseCase deleteUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        UserId result = registerUserUseCase.register(new RegisterUserCommand(request.email(), request.password(), request.desiredRoles()));
        return ResponseEntity.ok(new RegisterResponse(result.asString()));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserCommand loginUserCommand) {
        String token =  loginUserUseCase.login(loginUserCommand);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteUserResponse> delete(@AuthenticationPrincipal AuthenticatedUser user) {
        String result = deleteUserUseCase.deleteUserById(user.userId());
        return ResponseEntity.ok(new DeleteUserResponse(result));
    }
}
