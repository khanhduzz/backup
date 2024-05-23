package nashtech.khanhdu.backend.services.impl;

import nashtech.khanhdu.backend.dto.SignUpDto;
import nashtech.khanhdu.backend.dto.UserDto;
import nashtech.khanhdu.backend.entities.User;
import nashtech.khanhdu.backend.exceptions.UserExistException;
import nashtech.khanhdu.backend.mapper.UserMapper;
import nashtech.khanhdu.backend.repositories.RoleRepository;
import nashtech.khanhdu.backend.repositories.UserRepository;
import nashtech.khanhdu.backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl (UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public ResponseEntity<User> createUser(UserDto dto) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<User> updateUser(Long id, UserDto dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserExistException("User not found"));
        dto.setId(id);
        var updateUser = userMapper.updateUser(user, dto);
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        updateUser.setPassword(encryptedPassword);
        updateUser.setUsername(dto.getUsername());
        userRepository.save(updateUser);
        return ResponseEntity.ok(updateUser);
    }

    @Override
    public ResponseEntity<String> deleteUser(Long id) {
        return null;
    }

    @Transactional
    public UserDetails signUp (SignUpDto data) throws UserExistException {
        var userByName = userRepository.findOneByUsername(data.username());
        if ( userByName.isPresent() ) {
            throw new UserExistException("Username already exists");
        }

        var userByEmail = userRepository.findByEmailEquals(data.email());
        if (userByEmail.isPresent()) {
            throw new UserExistException("Email already exists");
        }

        String encryptedPassword = passwordEncoder.encode(data.password());
        var newUser = new User();
        newUser.setPassword(encryptedPassword);
        newUser.setUsername(data.username());
        newUser.setEmail(data.email());
        if ( !data.roles().isEmpty() ) {
            var userRoles = roleRepository.findAllById(data.roles());
            if ( userRoles.size() != data.roles().size() ) {
                throw new UserExistException("Role not found");
            }
            newUser.setRoles(new HashSet<>(userRoles));
        }

        return userRepository.save(newUser);
    }
}
