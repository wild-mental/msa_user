package hana.corp.msa_user.service;

import hana.corp.msa_user.domain.User;
import hana.corp.msa_user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1.1 Create User
    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    // 1.2 Get User by ID
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // 1.3 Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 1.4 Update User
    public Optional<User> updateUser(Long userId, User updatedUser) {
        return userRepository.findById(userId).map(user -> {
            user.setName(updatedUser.getName() != null ? updatedUser.getName() : user.getName());
            user.setEmail(updatedUser.getEmail() != null ? updatedUser.getEmail() : user.getEmail());
            user.setPhone(updatedUser.getPhone() != null ? updatedUser.getPhone() : user.getPhone());
            return userRepository.save(user);
        });
    }
}
