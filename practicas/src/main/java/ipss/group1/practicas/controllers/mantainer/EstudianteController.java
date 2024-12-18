package ipss.group1.practicas.controllers.mantainer;

import ipss.group1.practicas.controllers.response.ResponseFormat;
import ipss.group1.practicas.controllers.response.ResponseFormatLists;
import ipss.group1.practicas.services.dtos.EstudianteDTO;
import ipss.group1.practicas.models.Estudiante;
import ipss.group1.practicas.services.EstudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/estudiantes")
public class EstudianteController {
    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public ResponseEntity<ResponseFormatLists> getAllEstudiantes() {
        return ResponseEntity.ok().body(
                ResponseFormatLists.ResponseFormatListsBuilder.aResponseFormatLists()
                .withData(estudianteService.getAllEstudiantes())
                .withMessage("Listado Estudiantes").build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFormat> getEstudianteById(@PathVariable Long id) {
        EstudianteDTO estudianteDTO = estudianteService.getEstudianteById(id);
        if (estudianteDTO != null) {
            return ResponseEntity.ok(ResponseFormat.ResponseFormatBuilder.aResponseFormat()
                    .withData(estudianteDTO)
                    .withMessage("Estudiante encontrado")
                    .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public  ResponseEntity<ResponseFormat> createEstudiante(@RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(ResponseFormat.ResponseFormatBuilder.aResponseFormat()
                .withData(estudianteService.createEstudiante(estudiante))
                .withMessage("Estudiante creado")
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseFormat> updateEstudiante(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        EstudianteDTO estudianteDTO = estudianteService.updateEstudiante(id, estudiante);
        if (estudianteDTO != null) {
            return ResponseEntity.ok(ResponseFormat.ResponseFormatBuilder.aResponseFormat()
                    .withData(estudianteDTO)
                    .withMessage("Estudiante actualizado")
                    .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable Long id) {
        if (estudianteService.deleteEstudiante(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}