package com.quickcommerce.repository;

import com.quickcommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private User user;

//    @BeforeEach
//    public void beforeEach() {
//        user = User
//                .builder()
//                .id(1)
//                .email("hey@domain.com")
//                .password("1234")
//                .mobile("9890123456")
//                .build();
//        userRepository.save(user);
//    }

    //    @DisplayName("given email when findByEmail then user")
//    @Test
    void givenEmail_whenFindByEmail_thenUser() {
        Optional<User> result = userRepository.findByEmail(user.getEmail());

        assertThat(result.get()).isNotNull();
        assertThat(result.get().getId()).isEqualTo(1);
    }

    //    @DisplayName("given non existing email when findByEmail then null")
//    @Test
    void givenNonExistingEmail_whenFindByEmail_thenNull() {
        Optional<User> result = userRepository.findByEmail("non-existing-email@domain.com");

        assertThat(result.isEmpty()).isEqualTo(true);
    }
}