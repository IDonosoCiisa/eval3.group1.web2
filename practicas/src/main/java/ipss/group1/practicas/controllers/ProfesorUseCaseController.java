package ipss.group1.practicas.controllers;

import ipss.group1.practicas.controllers.response.ResponseFormat;
import ipss.group1.practicas.controllers.response.ResponseFormatLists;
import ipss.group1.practicas.models.Practica;
import ipss.group1.practicas.services.dtos.PracticaDTO;
import ipss.group1.practicas.services.usecases.ProfesorUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorUseCaseController {
    private final ProfesorUseCase profesorUseCase;

    public ProfesorUseCaseController(ProfesorUseCase profesorUseCase) {
        this.profesorUseCase = profesorUseCase;
    }

    @GetMapping("/practicas")
    public ResponseEntity<ResponseFormatLists> getAllPracticas() {
        return  ResponseEntity.ok().body(
                ResponseFormatLists.ResponseFormatListsBuilder.aResponseFormatLists()
                        .withData(profesorUseCase.getAllPracticas())
                        .withMessage("Listado Prácticas").build());
    }

    @GetMapping("/practicas/{id}")
    public ResponseEntity<ResponseFormat> getPracticaById(@PathVariable Long id) {
        Optional<PracticaDTO> practicaDTO = profesorUseCase.getPracticaById(id);
        return practicaDTO.map(dto -> ResponseEntity.ok(ResponseFormat.ResponseFormatBuilder.aResponseFormat()
                .withData(dto)
                .withMessage("Práctica encontrada")
                .build())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/estudiantes/{estudianteId}/practicas")
    public ResponseEntity<ResponseFormatLists> getPracticasByEstudianteId(@PathVariable Long estudianteId) {
        profesorUseCase.getPracticasByEstudianteId(estudianteId);
        return ResponseEntity.ok().body(
                ResponseFormatLists.ResponseFormatListsBuilder.aResponseFormatLists()
                        .withData(profesorUseCase.getPracticasByEstudianteId(estudianteId))
                        .withMessage("Listado prácticas para estudiante id: " + estudianteId).build());
    }


    @PostMapping("/practicas")
    public ResponseEntity<ResponseFormat> createPractica(@RequestBody Practica practica) {
        return ResponseEntity.ok(ResponseFormat.ResponseFormatBuilder.aResponseFormat()
                .withData(profesorUseCase.createPractica(practica))
                .withMessage("Práctica creada")
                .build());
    }

    @PutMapping("/practicas/{id}")
    public ResponseEntity<ResponseFormat> updatePractica(@PathVariable Long id, @RequestBody Practica practica) {
        Optional<PracticaDTO> updatedPractica = profesorUseCase.updatePractica(id, practica);
        return updatedPractica.map(dto -> ResponseEntity.ok(ResponseFormat.ResponseFormatBuilder.aResponseFormat()
                .withData(dto)
                .withMessage("Práctica actualizada")
                .build())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/practicas/{id}")
    public ResponseEntity<Void> deletePractica(@PathVariable Long id) {
        if (profesorUseCase.deletePractica(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}