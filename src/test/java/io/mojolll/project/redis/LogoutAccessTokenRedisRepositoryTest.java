package io.mojolll.project.redis;

import io.mojolll.project.v1.api.redis.logout.LogoutAccessTokenFromRedis;
import io.mojolll.project.v1.api.redis.logout.LogoutAccessTokenRedisRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogoutAccessTokenRedisRepositoryTest {

    @Autowired
    LogoutAccessTokenRedisRepository logoutAccessTokenRedisRepository;

    @BeforeEach
    public void clear(){
        logoutAccessTokenRedisRepository.deleteAll();
    }

    @DisplayName("save")
    @Test
    public void save() throws Exception{
        //given
        String accessToken = "accessToken";
        String email = "email";
        Long expiration = 3000L;
        LogoutAccessTokenFromRedis logoutAccessToken = LogoutAccessTokenFromRedis.createLogoutAccessToken(accessToken, email, expiration);

        //when
        logoutAccessTokenRedisRepository.save(logoutAccessToken);

        //then
        LogoutAccessTokenFromRedis find = logoutAccessTokenRedisRepository.findById(accessToken).get();


        assertAll(
                () -> assertEquals(accessToken,find.getId()),
                () -> assertEquals(email,find.getEmail()),
                () -> assertEquals(expiration/1000,find.getExpiration())
        );
    }
}