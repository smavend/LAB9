package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;

import java.sql.*;
import java.util.ArrayList;

public class DaoPartidos extends DaoBase {
    public ArrayList<Partido> listaDePartidos() {

        ArrayList<Partido> partidos = new ArrayList<>();
        Partido partido;

        String sql = "select * from heroe";
        try (Connection connection = this.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)){
            while (rs.next()) {
            partido = new Partido();
            partido.setNumeroJornada(rs.getInt("numeroJornada"));
            partido.setFecha(rs.getString("fecha"));
            Seleccion seleccionLocal = new Seleccion();
            seleccionLocal.setIdSeleccion(rs.getInt("seleccionLocal"));
            partido.setSeleccionLocal();
            heroesList.add(newHero);
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
