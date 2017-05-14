package com.woostore.security.controller;

import com.woostore.config.json.View;
import com.woostore.entity.User;
import com.woostore.security.JwtAuthenticationRequest;
import com.woostore.security.JwtTokenUtil;
import com.woostore.security.JwtUser;
import com.woostore.security.service.JwtAuthenticationResponse;
import com.fasterxml.jackson.annotation.JsonView;
import com.woostore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @JsonView(View.Auth.class)
    @PostMapping("${jwt.route.authentication.path}")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, device);
        User user = userService.getUserByUsername(authenticationRequest.getUsername());
        Map result = new HashMap();
        result.put("token", token);
        result.put("user", user);
        return ResponseEntity.ok(result);
    }

//    @GetMapping(value = "${jwt.route.authentication.path}/customer")
//    @PreAuthorize("hasRole('CUSTOMER')")
//    public ResponseEntity<?> checkCustomerToken() {
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping(value = "${jwt.route.authentication.path}/admin")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<?> checkAdminToken() {
//        return ResponseEntity.ok().build();
//    }

    @GetMapping(value = "${jwt.route.authentication.refresh}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
