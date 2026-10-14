package my.gov.imi.niise_demo_hazieq;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/passports")
public class PassportController {

    private final PassportService service;
    private final ModelMapper mapper;

    public PassportController(PassportService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<PassportDto> getPassports() {
        return service.findAllPassports()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/{id}")
    public PassportDto getPassportById(@PathVariable int id) {
        return convertToDto(service.findPassportById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePassportById(@PathVariable int id) {
        service.removePassportById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PassportDto postPassport(@Valid @RequestBody PassportDto passportDto) {
        Passport entity = convertToEntity(passportDto);
        Passport passport = service.addPassport(entity);
        return convertToDto(passport);
    }

    @PutMapping("/{id}")
    public PassportDto putPassport(
            @PathVariable int id,
            @Valid @RequestBody PassportDto passportDto
    ) {
        Passport passportEntity = convertToEntity(passportDto);
        Passport updated = service.updatePassport(id, passportEntity);
        return convertToDto(updated);
    }

    private PassportDto convertToDto(Passport entity) {
        return mapper.map(entity, PassportDto.class);
    }

    private Passport convertToEntity(PassportDto dto) {
        return mapper.map(dto, Passport.class);
    }
}
