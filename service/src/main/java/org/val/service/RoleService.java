package org.val.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.val.dto.RoleCreateEditDto;
import org.val.dto.RoleReadDto;
import org.val.dtoMapper.RoleCreateEditMapper;
import org.val.dtoMapper.RoleReadMapper;
import org.val.entity.Role;
import org.val.repository.RoleRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleService {

    private final RoleReadMapper roleReadMapper;
    private final RoleRepository roleRepository;

    private final RoleCreateEditMapper roleCreateEditMapper;
    public List<RoleReadDto> findAll() {
        return roleRepository.findAll().stream()
                .map(roleReadMapper::map)
                .toList();
    }

    public Optional<RoleReadDto> findById(Integer id) {
        return roleRepository.findById(id)
                .map(roleReadMapper::map);
    }

    @Transactional
    public RoleReadDto create(RoleCreateEditDto roleDto) {
        return Optional.of(roleDto)
                .map(roleCreateEditMapper::map)
                .map(roleRepository::save)
                .map(roleReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<RoleReadDto> update(Integer id, RoleCreateEditDto roleDto) {
        return roleRepository.findById(id)
                .map(role -> roleCreateEditMapper.map(roleDto, role))
                .map(roleRepository::saveAndFlush)
                .map(roleReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return roleRepository.findById(id)
                .map(entity -> {
                    roleRepository.delete(entity);
                    return true;
                })
                .orElse(false);
    }
}
