package com.backend.integrador.dao.impl;

import com.backend.integrador.dao.H2Connection;
import com.backend.integrador.dao.IDao;
import com.backend.integrador.entity.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    //no es necesario dejar escritos los constructores vacios si no tenemos otro
    public OdontologoDaoH2() {
    }

    public Odontologo registrarOdontologo(Odontologo odontologo) {
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ODONTOLOGOS(MATRICULA,NOMBRE,APELLIDO) VALUES (?, ?, ?)", 1);

            ps.setInt(1, odontologo.getMatricula());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getApellido());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            while (rs.next()) {
                odontologo.setId(rs.getInt(1));
            }

            connection.commit();
            LOGGER.info("Odontologo registrado: " + odontologo);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            ex.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Existe un error, intente nuevamente");
                } catch (SQLException exept) {
                    LOGGER.error(exept.getMessage());
                    exept.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Problema al cerrar la base de datos" + e.getMessage());
                e.printStackTrace();
            }

        }
    //recuerden que es buena practica retornar un nuevo objeto, en vez de modificar y retornar lo que nos llega por parametro
        return odontologo;
    }

    public List<Odontologo> listarOdontologos() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList();

        try {
            connection = H2Connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Odontologo odontologo = new Odontologo(rs.getInt(1),  rs.getInt(2), rs.getString(3), rs.getString(4));
                odontologos.add(odontologo);
            }

            LOGGER.info("Listado de los odontólogos registrados: " + odontologos);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ocurrió un problema al intentar cerrar la base de datos. " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return odontologos;
    }
}
