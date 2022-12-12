package com.dh.clase23.service;

import com.dh.clase23.dto.TurnoDTO;
import com.dh.clase23.model.Domicilio;
import com.dh.clase23.model.Odontologo;
import com.dh.clase23.model.Paciente;
import com.dh.clase23.model.Turno;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest

public class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarTurnoTest(){
        Odontologo odontologoAGuardar= new Odontologo("12", "jhon", "parra");
        Odontologo odontologoGuardado=odontologoService.guardarOdontologo(odontologoAGuardar);
        Paciente pacienteAGuardar= new Paciente("Rodolfo","Baspineiro"
                ,"5161", LocalDate.of(2022,11,28),"prueba@gmail.com",
                new Domicilio("Calle a",548,"Salta Capital","Salta"));
        Paciente pacienteGuardado=pacienteService.guardarPaciente(pacienteAGuardar);
        TurnoDTO turnoAGuardar= new TurnoDTO();
        turnoAGuardar.setOdontologoId(1L);
        turnoAGuardar.setPacienteId(1L);
        turnoAGuardar.setFecha(LocalDate.of(2022,11,28));
        TurnoDTO turnoGuardado=turnoService.guardarTurno(turnoAGuardar);
        assertEquals(1L,turnoGuardado.getId());
    }
   @Test
    @Order(2)
    public void buscarTurnoPorIdTest(){
        Long idABuscar=1L;
        Optional<TurnoDTO> turnoBuscado=turnoService.buscarTurno(idABuscar);
        assertNotNull(turnoBuscado.get());
    }


   @Test
    @Order(3)
    public void listarTurnoTest(){
        List<TurnoDTO> turnos= turnoService.buscarTodosTurno();
        Integer cantidadEsperada=1;
        assertEquals(cantidadEsperada,turnos.size());
    }
    @Test
    @Order(4)
    public void actualizarTurnoTest(){
        TurnoDTO turnoAActualizar= new TurnoDTO();
        turnoAActualizar.setId(1L);
        turnoAActualizar.setOdontologoId(1L);
        turnoAActualizar.setPacienteId(1L);
        turnoAActualizar.setFecha(LocalDate.of(2022,12,28));
        turnoService.actualizarTurno(turnoAActualizar);
        Optional<TurnoDTO> turnoActualizado= turnoService.buscarTurno(turnoAActualizar.getId());
        assertEquals((LocalDate.of(2022,12,28)),turnoActualizado.get().getFecha());
    }
    @Test
    @Order(5)
    public void eliminarTurnoTest()  {
        Long idAEliminar=1L;
        turnoService.eliminarTurno(idAEliminar);
        Optional<TurnoDTO> turnoEliminado=turnoService.buscarTurno(idAEliminar);
        assertFalse(turnoEliminado.isPresent());
    }
}
