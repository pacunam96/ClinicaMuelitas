package com.dh.clase23.service;
import com.dh.clase23.model.Paciente;
import com.dh.clase23.model.Usuario;
import com.dh.clase23.model.UsuarioRole;
import com.dh.clase23.repository.PacienteRepository;
import com.dh.clase23.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private PacienteRepository pacienteRepository;
    private UsuarioRepository usuarioRepository;
    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.pacienteRepository = pacienteRepository;
    }
    public Paciente guardarPaciente (Paciente paciente){
        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();
        String passCifrada=cifrador.encode(paciente.getDni());
        Usuario usuario=new Usuario(paciente.getNombre(), paciente.getNombre(),paciente.getEmail(),
                passCifrada, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);
        return pacienteRepository.save(paciente);
    }
    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }
    public void actualizarPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPaciente(Long id){
        return pacienteRepository.findById(id);
    }
    public List<Paciente> buscarTodosPacientes(){
        return pacienteRepository.findAll();
    }
    public Optional<Paciente> buscarPacienteByEmail(String email){
        return pacienteRepository.findByEmail(email);
    }
}
