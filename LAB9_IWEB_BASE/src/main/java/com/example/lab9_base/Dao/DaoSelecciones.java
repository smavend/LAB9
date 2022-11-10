package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Estadio;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;

import java.sql.*;
import java.util.ArrayList;

public class DaoSelecciones extends DaoBase {
    public ArrayList<Seleccion> listarSelecciones() {

        ArrayList<Seleccion> selecciones = new ArrayList<>();
        DaoEstadio daoEstadio = new DaoEstadio();
        Seleccion seleccion = null;

        String sql = "select * from seleccion";
        try (Connection connection = this.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)){
            while (rs.next()) {
                seleccion = new Seleccion();
                seleccion.setIdSeleccion(rs.getInt("idSeleccion"));
                seleccion.setNombre(rs.getString("nombre"));
                seleccion.setTecnico(rs.getString("tecnico"));
                seleccion.setEstadio(daoEstadio.obtenerEstadioXId(rs.getInt(4)));

                selecciones.add(seleccion);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return selecciones;
    }
    public Seleccion obtenerSeleccionXId (int idSeleccion){
        Seleccion seleccion = null;
        String sql = "SELECT * FROM seleccion WHERE idSeleccion = ?";
        DaoEstadio daoEstadio = new DaoEstadio();
        try(Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1,idSeleccion);
            try (ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    seleccion = new Seleccion();
                    seleccion.setIdSeleccion(idSeleccion);
                    seleccion.setNombre(rs.getString("nombre"));
                    seleccion.setTecnico(rs.getString("tecnico"));
                    seleccion.setEstadio(daoEstadio.obtenerEstadioXId(rs.getInt("estadio_idEstadio")));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seleccion;
    }

}
