package ipss.group1.practicas.controllers.mantainer;

import ipss.group1.practicas.controllers.response.ResponseFormat;
import ipss.group1.practicas.controllers.response.ResponseFormatLists;
import ipss.group1.practicas.services.dtos.TutorDTO;
import ipss.group1.practicas.models.Tutor;
import ipss.group1.practicas.services.TutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/tutores")
public class TutorController {
    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @GetMapping
    public ResponseEntity<ResponseFormatLists> getAllTutores() {
        return ResponseEntity.ok().body(
                ResponseFormatLists.ResponseFormatListsBuilder.aResponseFormatLists()
                        .withData(tutorService.getAllTutores())
                        .withMessage("Listado Tutores").build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFormat> getTutorById(@PathVariable Long id) {
        TutorDTO tutorDTO = tutorService.getTutorById(id);
        if (tutorDTO != null) {
            return ResponseEntity.ok(ResponseFormat.ResponseFormatBuilder.aResponseFormat()
                    .withData(tutorDTO)
                    .withMessage("Tutor encontrado")
                    .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ResponseFormat> createTutor(@RequestBody Tutor tutor) {
        return ResponseEntity.ok(ResponseFormat.ResponseFormatBuilder.aResponseFormat()
                .withData(tutorService.createTutor(tutor))
                .withMessage("Tutor creado")
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseFormat> updateTutor(@PathVariable Long id, @RequestBody Tutor tutor) {
        TutorDTO tutorDTO = tutorService.updateTutor(id, tutor);
        if (tutorDTO != null) {
            return ResponseEntity.ok(ResponseFormat.ResponseFormatBuilder.aResponseFormat()
                    .withData(tutorDTO)
                    .withMessage("Tutor actualizado")
                    .build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTutor(@PathVariable Long id) {
        if (tutorService.deleteTutor(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}