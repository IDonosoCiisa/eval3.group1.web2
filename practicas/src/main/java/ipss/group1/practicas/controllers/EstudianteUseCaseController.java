package ipss.group1.practicas.controllers;

import ipss.group1.practicas.controllers.response.ResponseFormat;
import ipss.group1.practicas.controllers.response.ResponseFormatLists;
import ipss.group1.practicas.services.dtos.PracticaDTO;
import ipss.group1.practicas.services.usecases.EstudianteUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteUseCaseController {
    private final EstudianteUseCase estudianteUseCase;

    public EstudianteUseCaseController(EstudianteUseCase estudianteUseCase) {
        this.estudianteUseCase = estudianteUseCase;
    }

    @GetMapping("/{estudianteId}/practicas")
    public ResponseEntity<ResponseFormatLists> getPracticasByEstudianteId(@PathVariable Long estudianteId) {
        return ResponseEntity.ok().body(
                ResponseFormatLists.ResponseFormatListsBuilder.aResponseFormatLists()
                        .withData(estudianteUseCase.getPracticasByEstudianteId(estudianteId))
                        .withMessage("Listado prácticas para id: " + estudianteId).build());
    }

    @PostMapping("/{estudianteId}/practicas")
    public ResponseEntity<ResponseFormat> createPractica(@PathVariable Long estudianteId, @RequestBody PracticaDTO practicaDTO) {
        return ResponseEntity.ok(ResponseFormat.ResponseFormatBuilder.aResponseFormat()
                .withData( estudianteUseCase.createPractica(practicaDTO, estudianteId))
                .withMessage("Práctica creada")
                .build());
    }
}