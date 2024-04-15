package liubomyr.stepanenko.lessonservice.feign;

import liubomyr.stepanenko.lessonservice.dto.response.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "user-service")
public interface UserFeignClient {
    @GetMapping("/api/users/find")
    UserDto findByUsernameOrEmail(@RequestParam(value = "data") String data);
}
