package com.licnosoft.linkedin.services.users;


import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.model.auth.UserInfoDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;
import com.licnosoft.linkedin.model.general.ToggleEntityDTO;
import com.licnosoft.linkedin.model.users.UserConnectionDTO;
import com.licnosoft.linkedin.model.users.UserBasicDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Page<UserBasicDTO> search(String searchTerm, Boolean enabled, Pageable pageable);

    Page<User> findAll(Pageable pageable);

    List<PlainEntityDTO> findAll();

    UserBasicDTO findById(Long id);

    User findEntityById(Long id);

    User findEntityByUsername(String username);
    User findEntityByEmailAndPassword(String username,String password);

    Optional<User> findByEmail(String email);

    UserBasicDTO loginUser(UserInfoDTO dto);

    ToggleEntityDTO toggleEnabled(ToggleEntityDTO dto);

    UserBasicDTO save(UserInfoDTO dto, boolean insert);

    User save(User entity);

    void deleteById(Long id);

    User getOptionalLoggedUser();

    User getLoggedUser();


    boolean isUserAdmin(User user);

    boolean isUserProfessional(User user);

    UserBasicDTO loginUser(String token, String email, String password);

	List<UserConnectionDTO> getUserConnectionRequests(boolean accepted);

}
