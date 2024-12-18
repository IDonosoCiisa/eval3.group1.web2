package ipss.group1.practicas.services.usecases;

import ipss.group1.practicas.models.Empresa;
import ipss.group1.practicas.models.Estudiante;
import ipss.group1.practicas.models.Tutor;
import ipss.group1.practicas.repositories.EmpresaRepository;
import ipss.group1.practicas.repositories.EstudianteRepository;
import ipss.group1.practicas.repositories.TutorRepository;
import ipss.group1.practicas.services.dtos.PracticaDTO;
import ipss.group1.practicas.models.Practica;
import ipss.group1.practicas.repositories.PracticaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfesorUseCase {
    private final PracticaRepository practicaRepository;
    private final EstudianteRepository estudianteRepository;
    private final TutorRepository tutorRepository;
    private final EmpresaRepository empresaRepository;

    public ProfesorUseCase(PracticaRepository practicaRepository, EstudianteRepository estudianteRepository, TutorRepository tutorRepository, EmpresaRepository empresaRepository) {
        this.practicaRepository = practicaRepository;
        this.estudianteRepository = estudianteRepository;
        this.tutorRepository = tutorRepository;
        this.empresaRepository = empresaRepository;
    }

    public List<PracticaDTO> getAllPracticas() {
        return practicaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PracticaDTO> getPracticasByEstudianteId(Long estudianteId) {
        return practicaRepository.findAll().stream()
                .filter(practica -> practica.getEstudiante().getId().equals(estudianteId))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PracticaDTO> getPracticaById(Long id) {
        return practicaRepository.findById(id)
                .map(this::convertToDTO);
    }

    public PracticaDTO createPractica(Practica practica) {
        Empresa empresa = empresaRepository.findById(practica.getEmpresa().getId()).orElse(null);
        Tutor tutor = tutorRepository.findById(practica.getTutor().getId()).orElse(null);
        Estudiante estudiante = estudianteRepository.findById(practica.getEstudiante().getId()).orElse(null);
        return convertToDTO(practicaRepository.save(
                Practica.PracticaBuilder.aPractica()
                        .withDescripcion(practica.getDescripcion())
                        .withEstudiante(estudiante)
                        .withTutor(tutor)
                        .withEmpresa(empresa)
                        .build())
        );
    }

    public Optional<PracticaDTO> updatePractica(Long id, Practica practica) {
        return practicaRepository.findById(id)
                .map(existingPractica -> {
                    existingPractica.setDescripcion(practica.getDescripcion());
                    existingPractica.setEstudiante(practica.getEstudiante());
                    existingPractica.setTutor(practica.getTutor());
                    existingPractica.setEmpresa(practica.getEmpresa());
                    Practica updatedPractica = practicaRepository.save(existingPractica);
                    return convertToDTO(updatedPractica);
                });
    }

    public boolean deletePractica(Long id) {
        return practicaRepository.findById(id)
                .map(practica -> {
                    practicaRepository.delete(practica);
                    return true;
                })
                .orElse(false);
    }

    private PracticaDTO convertToDTO(Practica practica) {
        return PracticaDTO.PracticaDTOBuilder.aPracticaDTO()
                .withId(practica.getId())
                .withDescripcion(practica.getDescripcion())
                .withEstudianteId(practica.getEstudiante().getId())
                .withTutorId(practica.getTutor().getId())
                .withEmpresaId(practica.getEmpresa().getId())
                .build();
    }
}