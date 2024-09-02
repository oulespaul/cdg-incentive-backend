package com.cdg.cdg_incentive_backend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@Slf4j
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorizeRequests ->
//                                authorizeRequests
////                                .requestMatchers("/**").permitAll()
//                                        .anyRequest().authenticated()
//                ).csrf(AbstractHttpConfigurer::disable)
//                .oauth2Login(httpSecurityOAuth2LoginConfigurer -> httpSecurityOAuth2LoginConfigurer.successHandler((request, response, authentication) -> {
//                    LoginStatusResponse loginStatusResponse = loginUser();
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    String jsonString = objectMapper.writeValueAsString(loginStatusResponse);
//                    String urlEncodedJson = URLEncoder.encode(jsonString, StandardCharsets.UTF_8);
//                    if (loginStatusResponse.getStatusCode() == 200) {
//                        response.sendRedirect("localhost:5173/" + urlEncodedJson);
//                    } else {
//                        response.sendRedirect("https://google.com");
//                    }
//                }));
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> corsFilter())
                .authorizeHttpRequests((requestMatcherRegistry) -> requestMatcherRegistry
                        .requestMatchers("/api/target-commission/**", "/test/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }

//
//    public LoginStatusResponse loginUser() {
//        LoginStatusResponse loginStatusResponse = new LoginStatusResponse();
//        loginStatusResponse.setStatusCode(200);
//
////        try {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//        String upn = oauthToken.getPrincipal().getAttribute("upn");
//        if (upn == null) {
//            upn = oauthToken.getPrincipal().getAttribute("preferred_username");
//        }
//        String email = upn;
//
//        if (email == null) {
//            loginStatusResponse.setStatusCode(401);
//        }
////            UserDto userModelResponse = this.userService.findByEmail(email);
////            if (userModelResponse != null) {
////                String emailToken = userModelResponse.getEmailAddress();
////                List<PermissionDTO> permissionDTOList = roleService.getPermissions(userModelResponse.getRoleId());
////
////                if (!userModelResponse.isStatus()) {
////                    loginStatusResponse.setStatusMessage("User has been disabled");
////                    loginStatusResponse.setSuccess(false);
////                    loginStatusResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
////                    return loginStatusResponse;
////                }
////                String token = userService.createJWT(userModelResponse.getUserId(), emailToken, this.secret);
////                userModelResponse.setToken(token);
////                log.info("Token: {}", userModelResponse);
////                userService.updateUserById(userModelResponse.getUserId(), token);
////                loginStatusResponse.setSuccess(true);
////                loginStatusResponse.setPermissionDTOList(permissionDTOList);
////                loginStatusResponse.setUserName(userModelResponse.getUserName());
////                loginStatusResponse.setStatusMessage("Login successful");
////                loginStatusResponse.setStatusCode(HttpStatus.OK.value());
////                loginStatusResponse.setToken(token);
////                return loginStatusResponse;
////            } else {
////                loginStatusResponse.setStatusMessage("Email id: " + email + " does not exist");
////                loginStatusResponse.setSuccess(false);
////                loginStatusResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
////                return loginStatusResponse;
////            }
//        return loginStatusResponse;
////        } catch (Exception e) {
////            e.printStackTrace();
////            loginStatusResponse.setStatusMessage(e.getMessage());
////            loginStatusResponse.setSuccess(false);
////            loginStatusResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
////            return loginStatusResponse;
////        }
//    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }
}

