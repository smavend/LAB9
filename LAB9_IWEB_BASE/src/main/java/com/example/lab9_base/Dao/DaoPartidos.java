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
        DaoArbitros daoArbitros = new DaoArbitros();
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
            partido.setSeleccionVisitante(daoSelecciones.obtenerSeleccionXId(rs.getInt("seleccionVisitante")));
            partido.setArbitro(daoArbitros.buscarArbitro(rs.getInt("arbitro")));

            Estadio estadio = new Estadio();


            partidos.add(partido);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return partidos;
    }

    public void crearPartido(Partido partido) {
        String sql = "INSERT INTO partido (idPartido, seleccionLocal, seleccionVisitante, arbitro, fecha, numeroJornada)"+
                "VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1,partido.getIdPartido());
            pstmt.setInt(2,partido.getSeleccionLocal().getIdSeleccion());
            pstmt.setInt(3,partido.getSeleccionVisitante().getIdSeleccion());
            pstmt.setInt(4,partido.getArbitro().getIdArbitro());
            pstmt.setString(5,partido.getFecha());
            pstmt.setInt(6,partido.getNumeroJornada());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
