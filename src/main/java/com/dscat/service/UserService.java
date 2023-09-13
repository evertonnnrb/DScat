package com.dscat.service;

import com.dscat.exceptions.DatabaseIntegrityException;
import com.dscat.exceptions.ResourceNotFoundException;
import com.dscat.model.Role;
import com.dscat.model.User;
import com.dscat.model.dto.RoleDTO;
import com.dscat.model.dto.UserDTO;
import com.dscat.model.dto.UserInsertDTO;
import com.dscat.repository.RoleRepository;
import com.dscat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {


    @Autowired
    public BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(x -> new UserDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        User entity = user.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO save(UserInsertDTO userDTO) {
        User user = new User();
        createEntity(userDTO, user);
        user.setPassword(passwordEncoder.encode(userDTO.getPasswd()));
        user = userRepository.save(user);
        return new UserDTO(user);
    }

    private void createEntity(UserDTO userDTO, User user) {
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.getRoles().clear();
        for (RoleDTO rlRoleDTO : userDTO.getRoleDTOS()) {
            Role role = roleRepository.getReferenceById(rlRoleDTO.getId());
            user.getRoles().add(role);
        }
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User user = userRepository.getReferenceById(id);
            user.setFirstName(dto.getFirstName());
            user = userRepository.save(user);
            return new UserDTO(user);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found {" + id + "}");
        }
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found {" + id + "}");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseIntegrityException("Integrity database violation");
        }
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPages(PageRequest pageRequest) {
        Page<User> users = userRepository.findAll(pageRequest);
        return users.map(x -> new UserDTO(x));

    }
}
