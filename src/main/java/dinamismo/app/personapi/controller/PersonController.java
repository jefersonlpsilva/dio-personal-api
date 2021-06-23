package dinamismo.app.personapi.controller;

import dinamismo.app.personapi.dto.MessageResponseDTO;
import dinamismo.app.personapi.dto.request.PersonDTO;
import dinamismo.app.personapi.service.PersonService;
import dinamismo.app.personapi.service.exception.PersonAlreadyRegisteredException;
import dinamismo.app.personapi.service.exception.PersonCpfNotFoundException;
import dinamismo.app.personapi.service.exception.PersonNotFoundException;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v2/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {
    
    private PersonService personService;

    @ApiOperation(value="Create person")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO)
            throws PersonAlreadyRegisteredException {
        return personService.createPerson(personDTO);
    }

    @ApiOperation(value="List all people")
    @GetMapping
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }

    @ApiOperation(value="Find person by Id")
    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @ApiOperation(value="Find person by Cpf")
    @GetMapping("/cpf/{cpf}")
    public PersonDTO findByCpf(@PathVariable String cpf) throws PersonCpfNotFoundException {
        return personService.findByCfp(cpf);
    }

    @ApiOperation(value="Update person by id")
    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id, personDTO);
    }

    @ApiOperation(value="Delete person by id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.deleteById(id);
    }
    
}
