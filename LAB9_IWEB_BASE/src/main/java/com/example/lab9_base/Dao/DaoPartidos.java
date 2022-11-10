package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;

import java.sql.*;
import java.util.ArrayList;

public class DaoPartidos {
    public ArrayList<Partido> listaDePartidos() {

        ArrayList<Partido> partidos = new ArrayList<>();
        Partido partido;
        try {
            String user = "root";
            String passw = "root";
            String url = "jdbc:mysql://localhost:3306/lab9";
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, user, passw);
            Statement statement = connection.createStatement();
            String sql = "select * from heroe";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                partido = new Partido();
                partido.setNumeroJornada(rs.getInt("numeroJornada"));
                partido.setFecha(rs.getString("fecha"));
                Seleccion seleccionLocal = new Seleccion();
                seleccionLocal.setIdSeleccion(rs.getInt("seleccionLocal"));
                partido.setSeleccionLocal();
                heroesList.add(newHero);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return heroesList;
        return partidos;
    }

    public void crearPartido(Partido partido) {

        /*
        Inserte su código aquí
        */
    }
}
