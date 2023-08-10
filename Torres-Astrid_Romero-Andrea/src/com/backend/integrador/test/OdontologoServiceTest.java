package com.backend.integrador.test;

import com.backend.integrador.dao.impl.OdontologoDaoH2;
import com.backend.integrador.entity.Odontologo;
import com.backend.integrador.service.OdontologoService;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.List;

import static org.testng.AssertJUnit.assertNotNull;

class OdontologoServiceTest {

    private static Connection connection = null;

    private OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @Test
    public void guardamosUnOdontologo() {
        Odontologo odontologoTest = new Odontologo(3245, "Karen", "Ruiz" );
        Odontologo odontologoResultado = odontologoService.guardar(odontologoTest);

        assertNotNull("Karen", odontologoResultado.getNombre());
    }

    @Test
    public void deberiaHaberUnaListaDeTodosLosOdontologos() {
        List<Odontologo> odontologoListTest = odontologoService.listar();
        assertNotNull(odontologoListTest.isEmpty());
    }

}