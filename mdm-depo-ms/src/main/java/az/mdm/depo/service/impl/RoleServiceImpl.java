package az.mdm.depo.service.impl;

import az.mdm.depo.dto.RoleDTO;
import az.mdm.depo.repository.RoleRepository;
import az.mdm.depo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<RoleDTO> getRoles() {
        return roleRepository.findAll().stream().map(role -> modelMapper.map(role, RoleDTO.class)).collect(Collectors.toList());
    }
}
