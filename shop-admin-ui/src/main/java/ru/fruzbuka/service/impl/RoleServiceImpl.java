package ru.fruzbuka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fruzbuka.persist.entity.Role;
import ru.fruzbuka.persist.repo.RoleRepository;
import ru.fruzbuka.service.RoleService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Role> getById(Long id) {
        return roleRepository.findById(id);
    }

    @Transactional
    @Override
    public void saveOrUpdate(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Role getByName(String name) {
        return roleRepository.findByName(name);
    }
}
