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
            partido.setIdPartido(rs.getInt("idPartido"));
            partido.setNumeroJornada(rs.getInt("numeroJornada"));
            partido.setFecha(rs.getString("fecha"));
            partido.setSeleccionLocal(daoSelecciones.obtenerSeleccionXId(rs.getInt("seleccionLocal")));
            partido.setSeleccionVisitante(daoSelecciones.obtenerSeleccionXId(rs.getInt("seleccionVisitante")));
            partido.setArbitro(daoArbitros.buscarArbitro(rs.getInt("arbitro")));

            partidos.add(partido);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return partidos;
    }

    public boolean crearPartido(Partido partido) {
        String sql = "INSERT INTO partido (seleccionLocal, seleccionVisitante, arbitro, fecha, numeroJornada)"+
                "VALUES ( ?, ?, ?, ?, ?)";
        DaoPartidos daoPartidos = new DaoPartidos();
        try(Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            boolean valid = true;
            for (Partido p: daoPartidos.listaDePartidos()){
                if(p.getSeleccionLocal().getIdSeleccion()==partido.getSeleccionLocal().getIdSeleccion() && p.getSeleccionVisitante().getIdSeleccion() == partido.getSeleccionVisitante().getIdSeleccion()){
                    valid = false;
                }
            }
            if(valid && partido.getSeleccionLocal().getIdSeleccion()==partido.getSeleccionVisitante().getIdSeleccion()){
                valid = false;
            }
            if(valid){
                pstmt.setInt(1,partido.getSeleccionLocal().getIdSeleccion());
                pstmt.setInt(2,partido.getSeleccionVisitante().getIdSeleccion());
                pstmt.setInt(3,partido.getArbitro().getIdArbitro());
                pstmt.setString(4,partido.getFecha());
                pstmt.setInt(5,partido.getNumeroJornada());

                pstmt.executeUpdate();
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }
}
