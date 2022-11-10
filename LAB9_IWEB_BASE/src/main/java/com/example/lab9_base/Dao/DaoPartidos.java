package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Estadio;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;

import java.sql.*;
import java.util.ArrayList;

public class DaoPartidos extends DaoBase {
    public ArrayList<Partido> listaDePartidos() {

        ArrayList<Partido> partidos = new ArrayList<>();
        DaoSelecciones daoSelecciones = new DaoSelecciones();
        Partido partido;

        String sql = "select * from partido";
        try (Connection connection = this.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)){
            while (rs.next()) {
            partido = new Partido();
            partido.setNumeroJornada(rs.getInt("numeroJornada"));
            partido.setFecha(rs.getString("fecha"));
            partido.setSeleccionLocal(daoSelecciones.obtenerSeleccionXId(rs.getInt("seleccionLocal")));

            Seleccion seleccionVisitante = new Seleccion();
            seleccionVisitante.setIdSeleccion(rs.getInt("seleccionVisitante"));
            seleccionVisitante.setNombre(daoSelecciones.obtenerNameId(rs.getInt("seleccionVisitante")));
            partido.setSeleccionVisitante(seleccionVisitante);

            Estadio estadio = new Estadio();


            partidos.add(partido);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return partidos;
    }

    public void crearPartido(Partido partido) {

        /*
        Inserte su código aquí
        */
    }
}
