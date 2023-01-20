package com.jfarro.app.services.impl;

import com.jfarro.app.models.domains.DocumentType;
import com.jfarro.app.models.domains.Role;
import com.jfarro.app.models.domains.User;
import com.jfarro.app.repositories.DocumentTypeRepository;
import com.jfarro.app.repositories.RoleRepository;
import com.jfarro.app.repositories.UserRepository;
import com.jfarro.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    //------------------------ User ------------------------
    @Override
    @Transactional(readOnly = true)
    public Page<User> findAllPagesUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByIdUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateStateUser(Byte state, Long id) {
        userRepository.updateState(state, id);
    }

    //------------------------ Role ------------------------
    @Override
    @Transactional(readOnly = true)
    public Page<Role> findAllPagesRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Role> findByIdRole(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void updateStateRole(Byte state, Long id) {
        roleRepository.updateState(state, id);
    }

    //------------------------ DocumentType ------------------------
    @Override
    @Transactional(readOnly = true)
    public List<DocumentType> findAllDocumentTypes() {
        return documentTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DocumentType> findByIdDocumentType(Long id) {
        return documentTypeRepository.findById(id);
    }
}
