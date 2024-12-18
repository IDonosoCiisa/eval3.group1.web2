package ipss.group1.practicas.services.usecases;

import ipss.group1.practicas.models.Empresa;
import ipss.group1.practicas.models.Estudiante;
import ipss.group1.practicas.models.Tutor;
import ipss.group1.practicas.repositories.EmpresaRepository;
import ipss.group1.practicas.repositories.TutorRepository;
import ipss.group1.practicas.services.dtos.PracticaDTO;
import ipss.group1.practicas.models.Practica;
import ipss.group1.practicas.repositories.EstudianteRepository;
import ipss.group1.practicas.repositories.PracticaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteUseCase {
    private final PracticaRepository practicaRepository;
    private final EstudianteRepository estudianteRepository;
    private final TutorRepository tutorRepository;
    private final EmpresaRepository empresaRepository;

    public EstudianteUseCase(PracticaRepository practicaRepository, EstudianteRepository estudianteRepository, TutorRepository tutorRepository, EmpresaRepository empresaRepository) {
        this.practicaRepository = practicaRepository;
        this.estudianteRepository = estudianteRepository;
        this.tutorRepository = tutorRepository;
        this.empresaRepository = empresaRepository;
    }

    public List<PracticaDTO> getPracticasByEstudianteId(Long estudianteId) {
        return practicaRepository.findAll().stream()
                .filter(practica -> practica.getEstudiante().getId().equals(estudianteId))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PracticaDTO createPractica(PracticaDTO practicaDTO, Long estudianteId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId).orElse(null);
        Tutor tutor = tutorRepository.findById(practicaDTO.getTutorId()).orElse(null);
        Empresa empresa = empresaRepository.findById(practicaDTO.getEmpresaId()).orElse(null);
        return convertToDTO(practicaRepository.save(Practica.PracticaBuilder.aPractica()
                .withDescripcion(practicaDTO.getDescripcion())
                .withEstudiante(estudiante)
                .withTutor(tutor)
                .withEmpresa(empresa)
                .build()));
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