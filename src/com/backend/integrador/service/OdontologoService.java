package com.backend.integrador.service;

import com.backend.integrador.dao.IDao;
import com.backend.integrador.entity.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {this.odontologoIDao = odontologoIDao;}

    public Odontologo guardar(Odontologo odontologo) {
        return odontologoIDao.registrarOdontologo(odontologo);
    }


    public List<Odontologo> listar() {return odontologoIDao.listarOdontologos();}

   }