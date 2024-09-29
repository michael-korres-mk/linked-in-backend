package com.licnosoft.linkedin.services.users;


import com.licnosoft.linkedin.domain.enumeration.UserRole;
import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.model.auth.UserInfoDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;
import com.licnosoft.linkedin.model.general.ToggleEntityDTO;
import com.licnosoft.linkedin.model.users.UserBasicDTO;
import com.licnosoft.linkedin.model.users.UserDetailedDTO;
import com.licnosoft.linkedin.model.users.UserProfileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Page<UserBasicDTO> search(String searchTerm, String firstName, String lastName, String email, UserRole userRole,Boolean exceptLoggedUser, Boolean enabled,Pageable pageable);
    List<UserDetailedDTO> asListSearch(String searchTerm, String firstName, String lastName, String email, UserRole userRole,Boolean exceptLoggedUser, Boolean enabled);

    Page<User> findAll(Pageable pageable);

    List<PlainEntityDTO> findAll();

    UserBasicDTO findById(Long id);

    UserProfileDTO findProfileById(Long id);

    User findEntityById(Long id);

    User findEntityByUsername(String username);
    User findEntityByEmailAndPassword(String username,String password);

    Optional<User> findByEmail(String email);

    UserBasicDTO loginUser(UserInfoDTO dto);

    ToggleEntityDTO toggleEnabled(ToggleEntityDTO dto);

    UserInfoDTO save(UserInfoDTO dto, boolean insert);

    User save(User entity);

    void deleteById(Long id);

    User getOptionalLoggedUser();

    User getLoggedUser();


    boolean isUserAdmin(User user);

    boolean isUserProfessional(User user);

    UserBasicDTO loginUser(String token, String email, String password);

    UserProfileDTO updateProfile(UserProfileDTO dto);

    UserInfoDTO findUserInfoById(Long id);

    UserBasicDTO updateUserInfo(UserInfoDTO dto);
}
