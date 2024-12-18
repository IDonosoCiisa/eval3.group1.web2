package ipss.group1.practicas.controllers.mantainer;

import ipss.group1.practicas.controllers.response.ResponseFormat;
import ipss.group1.practicas.controllers.response.ResponseFormatLists;
import ipss.group1.practicas.services.dtos.PracticaDTO;
import ipss.group1.practicas.models.Practica;
import ipss.group1.practicas.services.PracticaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/practicas")
public class PracticaController {
    private final PracticaService practicaService;

    public PracticaController(PracticaService practicaService) {
        this.practicaService = practicaService;
    }

    @GetMapping
    public ResponseEntity<ResponseFormatLists> getAllPracticas() {
        return ResponseEntity.ok().body(
                ResponseFormatLists.ResponseFormatListsBuilder.aResponseFormatLists()
                        .withData(practicaService.getAllPracticas())
                        .withMessage("Listado de Prácticas").build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFormat> getPracticaById(@PathVariable Long id) {
        PracticaDTO practicaDTO = practicaService.getPracticaById(id);
        if (practicaDTO != null) {
            return ResponseEntity.ok(ResponseFormat.ResponseFormatBuilder.aResponseFormat()
                    .withData(practicaDTO)
                    .withMessage("Práctica encontrada")
                    .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public  ResponseEntity<ResponseFormat> createPractica(@RequestBody Practica practica) {
        return ResponseEntity.ok(ResponseFormat.ResponseFormatBuilder.aResponseFormat()
                .withData(practicaService.createPractica(practica))
                .withMessage("Práctica creada")
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseFormat> updatePractica(@PathVariable Long id, @RequestBody Practica practica) {
        PracticaDTO practicaDTO = practicaService.updatePractica(id, practica);
        if (practicaDTO != null) {
            return ResponseEntity.ok(ResponseFormat.ResponseFormatBuilder.aResponseFormat()
                    .withData(practicaDTO)
                    .withMessage("Práctica actualizada")
                    .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePractica(@PathVariable Long id) {
        if (practicaService.deletePractica(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}