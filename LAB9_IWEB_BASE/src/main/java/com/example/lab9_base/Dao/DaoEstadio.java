package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Estadio;
import com.example.lab9_base.Bean.Seleccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoEstadio extends  DaoBase{
    public Estadio obtenerEstadioXId (int idEstadio){
        Estadio estadio = null;
        String sql = "SELECT * FROM estadio WHERE idEstadio = ?";
        try(Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1,idEstadio);
            try (ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    estadio.setIdEstadio(idEstadio);
                    estadio.setNombre(rs.getString("nombre"));
                    estadio.setProvincia(rs.getString("provincia"));
                    estadio.setClub(rs.getString("club"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estadio;
    }
}
