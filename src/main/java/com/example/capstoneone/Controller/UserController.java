package com.example.capstoneone.Controller;

import com.example.capstoneone.API.ApiResponse;
import com.example.capstoneone.Model.Product;
import com.example.capstoneone.Model.User;
import com.example.capstoneone.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.PreFlightRequestHandler;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PreFlightRequestHandler preFlightRequestHandler;

    // Get all users
    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Add user
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.ok("User added successfully");
    }

    // Update user
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id,
                                        @RequestBody @Valid User user,
                                        Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }

        boolean updated = userService.updateUser(id, user);
        if (updated) {
            return ResponseEntity.ok("User updated successfully");
        }
        return ResponseEntity.status(404).body("User not found");
    }

    // Delete user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok("User deleted successfully");
        }
        return ResponseEntity.status(404).body("User not found");
    }
    // Get user by ID
    @GetMapping("get-user-by-id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(404).body("User not found");
    }
    @GetMapping("/get-preferences-product/{userId}")
    public ResponseEntity<?> getPreferenceProducts(@PathVariable String userId){
        ArrayList<Product> preferencesProducts = userService.preferenceInterester(userId);
        if(preferencesProducts != null){
            return ResponseEntity.ok().body(preferencesProducts);
        }
        return ResponseEntity.badRequest().body(new ApiResponse("you are not allowed to use preference Method"));
    }

    @GetMapping("/get-Friends/{userId}")
    public ResponseEntity<?> getClosestFriends(@PathVariable String userId){
        if(userService.getFriends(userId) != null){
            return ResponseEntity.ok().body(userService.getFriends(userId));
        }else{
            return ResponseEntity.badRequest().body(new ApiResponse("you are Not Allowed to see this List, you have to be interest in something"));
        }
    }
}
