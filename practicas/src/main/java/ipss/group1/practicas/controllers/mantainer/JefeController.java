package ipss.group1.practicas.controllers.mantainer;

import ipss.group1.practicas.controllers.response.ResponseFormat;
import ipss.group1.practicas.controllers.response.ResponseFormatLists;
import ipss.group1.practicas.services.dtos.JefeDTO;
import ipss.group1.practicas.models.Jefe;
import ipss.group1.practicas.services.JefeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/jefes")
public class JefeController {
    private final JefeService jefeService;

    public JefeController(JefeService jefeService) {
        this.jefeService = jefeService;
    }

    @GetMapping
    public ResponseEntity<ResponseFormatLists> getAllJefes() {
        return ResponseEntity.ok().body(
                ResponseFormatLists.ResponseFormatListsBuilder.aResponseFormatLists()
                        .withData(jefeService.getAllJefes())
                        .withMessage("Listado Jefes").build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFormat> getJefeById(@PathVariable Long id) {
        JefeDTO jefeDTO = jefeService.getJefeById(id);
        if (jefeDTO != null) {
            return ResponseEntity.ok(ResponseFormat.ResponseFormatBuilder.aResponseFormat()
                    .withData(jefeDTO)
                    .withMessage("Jefe encontrado")
                    .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ResponseFormat> createJefe(@RequestBody Jefe jefe) {
        return ResponseEntity.ok(ResponseFormat.ResponseFormatBuilder.aResponseFormat()
                .withData(jefeService.createJefe(jefe))
                .withMessage("Jefe creado")
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseFormat> updateJefe(@PathVariable Long id, @RequestBody Jefe jefe) {
        JefeDTO jefeDTO = jefeService.updateJefe(id, jefe);
        if (jefeDTO != null) {
            return ResponseEntity.ok(ResponseFormat.ResponseFormatBuilder.aResponseFormat()
                    .withData(jefeDTO)
                    .withMessage("Jefe actualizado")
                    .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJefe(@PathVariable Long id) {
        if (jefeService.deleteJefe(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}