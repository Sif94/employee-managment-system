package org.baouz.employeemanagementsystem.user;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.baouz.employeemanagementsystem.commun.PageResponse;
import org.baouz.employeemanagementsystem.email.EmailDTO;
import org.baouz.employeemanagementsystem.email.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    private final EmailService emailService;
    private final UserService userService;

    @PostMapping("/email")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> sendEmail(@RequestBody EmailDTO emailDTO){
        try {
            emailService.sendEmail(emailDTO);
            return ResponseEntity.accepted().build();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping
    public ResponseEntity<PageResponse<UserResponse>> getAllUsers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ){
        return ResponseEntity.ok(userService.findAllUsers(page,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @RequestBody @Valid UserRequest request
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.saveUser(request));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequest request
    ){
        return ResponseEntity.ok(userService.updateUser(id, request));
    }
}
