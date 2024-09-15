package com.licnosoft.linkedin.services.users;

import com.licnosoft.linkedin.config.exceptions.AppNotFoundException;
import com.licnosoft.linkedin.config.exceptions.AppValidationException;
import com.licnosoft.linkedin.config.security.JwtUtil;
import com.licnosoft.linkedin.domain.enumeration.UserRole;
import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.model.auth.UserInfoDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;
import com.licnosoft.linkedin.model.general.ToggleEntityDTO;
import com.licnosoft.linkedin.model.users.UserBasicDTO;
import com.licnosoft.linkedin.model.users.UserConnectionDTO;
import com.licnosoft.linkedin.model.users.UserDetailedDTO;
import com.licnosoft.linkedin.repository.users.UserRepository;
import com.licnosoft.linkedin.util.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Value("${app.auth-disabled:false}")
    boolean authDisabled;

    private final UserRepository entityRepository;
    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository entityRepository, ModelMapper modelMapper, JwtUtil jwtUtil) {
        this.entityRepository = entityRepository;
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Page<UserBasicDTO> search(String searchTerm,String firstName, String lastName, String email, UserRole userRole, Boolean enabled, Pageable pageable) {
        return entityRepository.search(searchTerm, firstName, lastName, email, userRole, enabled, pageable)
                .map(entity -> modelMapper.map(entity, UserBasicDTO.class));
    }

    @Override
    public List<PlainEntityDTO> findAll() {
        return entityRepository.findAll().stream().map(PlainEntityDTO::new).toList();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return entityRepository.findAll(pageable);
    }

    @Override
    public UserDetailedDTO findById(Long id) {
        return modelMapper.map(findEntityById(id), UserDetailedDTO.class);
    }

    @Override
    public User findEntityById(Long id) {
        return entityRepository.findById(id).orElseThrow(AppNotFoundException::new);
    }

    @Override
    public User findEntityByEmailAndPassword(String email,String password) {
        return entityRepository.findByEmailAndPassword(email,password).orElseThrow(AppNotFoundException::new);
    }

    @Override
    public User findEntityByUsername(String username) {
        return entityRepository.findByUsername(username).orElseThrow(AppNotFoundException::new);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return entityRepository.findByEmail(email);
    }

    @Override
    public UserInfoDTO loginUser(UserInfoDTO dto) {
//        UserType type = dto.getType().equals(TokenServiceType.???) ? UserType.GUARDIAN : UserType.STATE_EMPLOYEE;
        User user = findEntityByEmailAndPassword(dto.getEmail(),dto.getPassword());
        return modelMapper.map(entityRepository.save(user), UserInfoDTO.class);
    }

    @Override
    public UserBasicDTO loginUser(String token, String email, String password) {
        UserInfoDTO info;
        if(token != null){
            info = jwtUtil.decodeJwtToUserInfo(token);
        }
        else {
            info = new UserInfoDTO(email, password);
        }

        UserInfoDTO dto = loginUser(info);

        dto.setPassword(null);
        dto.setConfirmPassword(null);

        dto.setToken(jwtUtil.generateToken(dto));

        return dto;
    }

    @Override
    public ToggleEntityDTO toggleEnabled(ToggleEntityDTO dto) {
        User result = findEntityById(dto.getId());
        result.setEnabled(dto.isEnabled());
        return modelMapper.map(save(result), ToggleEntityDTO.class);
    }


    @Override
    public UserDetailedDTO save(UserInfoDTO dto, boolean insert) {
        if (!Utilities.validateEntityDto(dto, insert)) {
            throw new AppValidationException();
        }

        if (dto.getConfirmPassword() != null && !Utilities.validateConfirmPassword(dto)) {
            throw new AppValidationException();
        }
        // TODO: Check if connections get properly saved
        UserDetailedDTO userDTO = modelMapper.map(save(modelMapper.map(dto, User.class)), UserDetailedDTO.class);
        userDTO.setToken(jwtUtil.generateToken(dto));
        return userDTO;
    }

    @Override
    public User save(User entity) {
        if (!validateFields(entity)) {
            throw new AppValidationException();
        }

        Utilities.validateUniqueConstraint(entity, entityRepository.findByUsername(entity.getUsername()), "Υφίσταται ήδη χρήστης με το συγκεκριμένο username!");

        return entityRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        entityRepository.deleteById(id);
    }

    private boolean validateFields(User entity) {
        return entity.getUsername() != null;
    }

    @Override
    public User getOptionalLoggedUser() {
        return getLoggedUser(true);
    }

    @Override
    public User getLoggedUser() {
        if (authDisabled) {
            //admin
            return entityRepository.findById(1L).orElse(null);
            //professional
//            return entityRepository.findById(2L).orElse(null);
        }
        return getLoggedUser(false);
    }

    private User getLoggedUser(boolean optional) {
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User pUser) {
            user = pUser;
        }
        if (!optional && user == null) {
            throw new AppNotFoundException();
        }
        return user;
    }

    @Override
    public List<UserConnectionDTO> getUserConnectionRequests(boolean accepted){
        return this.getLoggedUser()
                .getConnections()
                .stream()
                .filter(c -> accepted == (c.getConnectionDate() != null))
                .map(uc -> modelMapper.map(uc,UserConnectionDTO.class))
                .toList();
    }

    //    @Override
//    public String getLoggedAfm() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.getPrincipal() instanceof String afm) {
//            return afm;
//        } else {
//            throw new AppNotFoundException();
//        }
//    }

    @Override
    public boolean isUserAdmin(User user) {
        return user.getUserRole() == UserRole.ADMIN;
    }

    @Override
    public boolean isUserProfessional(User user) {
        return user.getUserRole() == UserRole.PROFESSIONAL;
    }

}
