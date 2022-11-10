package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Seleccion;

import java.sql.*;
import java.util.ArrayList;

public class DaoSelecciones extends DaoBase {
    public ArrayList<Seleccion> listarSelecciones() {

        ArrayList<Seleccion> selecciones = new ArrayList<>();
        /*
        Inserte su código aquí
        */
        return selecciones;
    }
    public String obtenerNameId (int idSeleccion){
        Seleccion seleccion;
        String nombre = null;
        String sql = "SELECT nombre FROM seleccion WHERE idSeleccion = ?";
        try(Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1,idSeleccion);
            try (ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    nombre = rs.getString(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nombre;
    }

}
