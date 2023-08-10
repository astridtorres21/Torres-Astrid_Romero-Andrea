package com.backend.integrador.dao.impl;

import com.backend.integrador.dao.IDao;
import com.backend.integrador.entity.Odontologo;
import org.apache.log4j.Logger;

import java.util.List;

public class OdontologoDaoMemoria implements IDao<Odontologo> {
    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoMemoria.class);
    private List<Odontologo> odontologosRepository;

    public OdontologoDaoMemoria(List<Odontologo> odontologosRepository) {
        this.odontologosRepository = odontologosRepository;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo) {
        this.odontologosRepository.add(odontologo);
        LOGGER.info("Odontólogo guardado: " + odontologo);
        return odontologo;
    }

    public List<Odontologo> listarOdontologos() {
        LOGGER.info("Listado de todos los odontologos registrados en el sistema: " + (this.odontologosRepository));
        return this.odontologosRepository;
    }
}

