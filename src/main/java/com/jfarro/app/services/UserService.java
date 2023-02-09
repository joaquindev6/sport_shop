package com.jfarro.app.services;

import com.jfarro.app.models.domains.DocumentType;
import com.jfarro.app.models.domains.Role;
import com.jfarro.app.models.domains.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    //User
    Page<User> findAllPagesUsers(Pageable pageable);
    List<User> findAllUsers();
    Optional<User> findByIdUser(Long id);
    User findByUsernameUser(String username);
    void saveUser(User user);
    void updateStateUser(Byte state, Long id);

    //Role
    Page<Role> findAllPagesRoles(Pageable pageable);
    List<Role> findAllRoles();
    Optional<Role> findByIdRole(Long id);
    void saveRole(Role role);
    void updateStateRole(Byte state, Long id);

    //DocumentType
    List<DocumentType> findAllDocumentTypes();
    Optional<DocumentType> findByIdDocumentType(Long id);
}
