package az.mdm.depo.service.impl;

import az.mdm.depo.dto.CategoryDTO;
import az.mdm.depo.dto.ExpenditureListDTO;
import az.mdm.depo.dto.InstrumentDTO;
import az.mdm.depo.dto.QuantityDTO;
import az.mdm.depo.handler.CategoryNotFoundException;
import az.mdm.depo.handler.InstrumentNotFoundException;
import az.mdm.depo.handler.QuantityNotFoundException;
import az.mdm.depo.model.Category;
import az.mdm.depo.model.Instrument;
import az.mdm.depo.model.Quantity;
import az.mdm.depo.repository.CategoryRepository;
import az.mdm.depo.repository.InstrumentRepository;
import az.mdm.depo.service.InstrumentService;
import az.mdm.depo.service.QuantityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstrumentServiceImpl implements InstrumentService {

    private final ModelMapper modelMapper;
    private final InstrumentRepository instrumentRepository;
    private final QuantityService quantityService;
    private final CategoryRepository categoryRepository;

    @Override
    public Long inCome(InstrumentDTO dto) {
        Quantity quantity = checkQuantity(dto);
        Category category = checkCategory(dto);
        Instrument instrument = null;
        if(quantity != null && category !=null) {
            instrument = new Instrument();
            instrument.setCount(dto.getCount());
            instrument.setName(dto.getName());
            instrument.setFullAmount(checkFullAmount(dto.getCount(),dto.getOneAmount()));
            instrument.setQuantity(quantity);
            instrument.setCategory(category);
            instrument.setOneAmount(dto.getOneAmount());
            instrument.setIncomeDate(LocalDateTime.now());
        }
        return instrumentRepository.save(instrument).getId();
    }

    @Override
    public String inComeList(List<InstrumentDTO> dtos) {
       List<Instrument> instruments = dtos.stream().map(dto -> {
           Instrument instrument = modelMapper.map(dto,Instrument.class);
           if (dto.getQuantity() != null && dto.getCategory() != null) {
               instrument.setQuantity(modelMapper.map(dto.getQuantity(),Quantity.class));
               instrument.setCategory(modelMapper.map(dto.getCategory(),Category.class));
           } else
               throw new InstrumentNotFoundException();
           return instrument;
       }).collect(Collectors.toList());
             instrumentRepository.saveAll(instruments);
             return "Successful";
    }

    @Override
    public List<InstrumentDTO> getInstruments() {
        return instrumentRepository.findAll().stream().map(instrument -> {
           InstrumentDTO dto = modelMapper.map(instrument,InstrumentDTO.class);
            if(instrument.getQuantity() != null && instrument.getCategory() != null){
                dto.setQuantity(modelMapper.map(instrument.getQuantity(),QuantityDTO.class));
                dto.setCategory(modelMapper.map(instrument.getCategory(),CategoryDTO.class));
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<InstrumentDTO> getInstrumentsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
           return instrumentRepository.findInstrumentsByCategory(category).stream().map(instrument -> {
                InstrumentDTO dto = modelMapper.map(instrument,InstrumentDTO.class);
                if(instrument.getQuantity() != null) {
                    dto.setQuantity(modelMapper.map(instrument.getQuantity(),QuantityDTO.class));
                    dto.setCategory(modelMapper.map(category,CategoryDTO.class));
                }
                return dto;
            }).collect(Collectors.toList());
    }

    @Override
    public void updateInstrument(InstrumentDTO dto) {
        Quantity quantity = checkQuantity(dto);
        Category category = checkCategory(dto);
        Instrument instrument = null;
        if(quantity != null) {
            instrument = instrumentRepository.findById(dto.getId()).orElseThrow(() -> new InstrumentNotFoundException(dto.getId()));
            instrument.setCount(dto.getCount());
            instrument.setName(dto.getName());
            instrument.setFullAmount(checkFullAmount(dto.getCount(),dto.getOneAmount()));
            instrument.setQuantity(quantity);
            instrument.setCategory(category);
            instrument.setOneAmount(dto.getOneAmount());
            instrument.setUpdateDate(LocalDateTime.now());
        }
    }

    @Override
    public void expenditure(Long instrumentId, Long count) {
        if(instrumentId != null && count != null) {
            Instrument instrument = getInstrument(instrumentId);
            instrument.setExpenditureDate(LocalDateTime.now());
            instrument.setCount(instrument.getCount()-count);
            instrument.setFullAmount(checkFullAmount(instrument.getCount(),instrument.getOneAmount()));
        }
    }

    @Override
    public String expenditureList(List<ExpenditureListDTO> dtos) {
         dtos.stream().forEach(dto -> {
             if(dto.getCount() != null && dto.getInstrumentId() != null) {
                 Instrument instrument = getInstrument(dto.getInstrumentId());
                 if(instrument.getCount()>=dto.getCount()){
                     instrument.setExpenditureDate(LocalDateTime.now());
                     instrument.setCount(instrument.getCount()-dto.getCount());
                     instrument.setFullAmount(checkFullAmount(instrument.getCount(),instrument.getOneAmount()));
                     instrumentRepository.save(instrument);
                 }
             }
         });
         return null;
    }

    private Double checkFullAmount(Long count, Double amount) {
        return count * amount;
    }

    private Quantity checkQuantity(InstrumentDTO dto) {
       return quantityService.getQuantityById(dto.getQuantity().getId());
    }

    private Category checkCategory(InstrumentDTO dto) {
        return categoryRepository.findById(dto.getCategory().getId()).orElseThrow(() ->
                new CategoryNotFoundException(dto.getCategory().getId()));
    }
    private Instrument getInstrument(Long id){
        return instrumentRepository.findById(id).orElseThrow(() -> new InstrumentNotFoundException(id));
    }
}
