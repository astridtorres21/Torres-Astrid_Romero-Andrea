package com.backend.integrador.dao;

import java.util.List;

public interface IDao<T> {
    // guardar - listar
//recuerden que a esta altura los metodos deberian ser generales - registrar(), listar()
    T registrarOdontologo(T var1);

    List<T> listarOdontologos();
}
