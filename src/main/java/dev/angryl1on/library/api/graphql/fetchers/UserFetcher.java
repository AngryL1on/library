package dev.angryl1on.library.api.graphql.fetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import dev.angryl1on.library.core.models.dtos.UserDTO;
import dev.angryl1on.library.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class UserFetcher {

    private final UserService userService;

    @Autowired
    public UserFetcher(UserService userService) {
        this.userService = userService;
    }

    // Fetcher для получения пользователя по ID
    @DgsQuery(field = "userById")
    public UserDTO getUserById(DgsDataFetchingEnvironment dfe) {
        UUID id = dfe.getArgument("id");
        return userService.getUserById(id);
    }

    // Fetcher для получения всех пользователей
    @DgsQuery(field = "allUsers")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
