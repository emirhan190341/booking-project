package com.emirhanarici.bookingproject.base;

import com.emirhanarici.bookingproject.builder.UserBuilder;
import com.emirhanarici.bookingproject.logging.entity.LogEntity;
import com.emirhanarici.bookingproject.logging.service.LogService;
import com.emirhanarici.bookingproject.model.User;
import com.emirhanarici.bookingproject.security.CustomUserDetails;
import com.emirhanarici.bookingproject.security.CustomUserDetailsService;
import com.emirhanarici.bookingproject.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class BaseControllerTest extends AbstractTestContainerConfiguration {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected CustomUserDetailsService customUserDetailsService;

    @MockBean
    protected LogService logService;

    @Autowired
    protected JwtUtils jwtUtils;

    protected User mockUser;

    protected String mockUserToken;

    protected User mockAdmin;

    protected String mockAdminToken;


    @BeforeEach
    protected void initializeAuth() {

        this.mockUser = new UserBuilder().customer().build();
        this.mockAdmin = new UserBuilder().admin().build();

        final CustomUserDetails mockUserDetails = new CustomUserDetails(mockUser);
        final CustomUserDetails mockAdminDetails = new CustomUserDetails(mockAdmin);

        this.mockUserToken = generateMockToken(mockUserDetails);
        this.mockAdminToken = generateMockToken(mockAdminDetails);
        Mockito.when(customUserDetailsService.loadUserByUsername(mockUser.getEmail())).thenReturn(mockUserDetails);
        Mockito.when(customUserDetailsService.loadUserByUsername(mockAdmin.getEmail())).thenReturn(mockAdminDetails);
        Mockito.doNothing().when(logService).saveLogToDatabase(any(LogEntity.class));
    }

    private String generateMockToken(CustomUserDetails details) {
        return "Bearer ".concat(jwtUtils.generateJwtToken(details));
    }

}
