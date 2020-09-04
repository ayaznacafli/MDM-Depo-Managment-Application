package az.mdm.depo.service.impl;

import az.mdm.depo.dto.CategoryDTO;
import az.mdm.depo.repository.CategoryRepository;
import az.mdm.depo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> getCategorys() {
        return categoryRepository.findAll().stream().map(category -> {
            return modelMapper.map(category,CategoryDTO.class);
        }).collect(Collectors.toList());
    }


}
