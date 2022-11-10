package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Seleccion;

import java.util.ArrayList;
import java.sql.*;

public class DaoArbitros extends DaoBase {




    public ArrayList<Arbitro> listarArbitros() {
        ArrayList<Arbitro> arbitros = new ArrayList<>();
        String sql = "SELECT * FROM arbitro";

        try (Connection connection = this.getConnection();
             Statement stm = connection.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {


            while(rs.next()){
                Arbitro arbitro = new Arbitro();
                arbitro.setIdArbitro(rs.getInt(1));
                arbitro.setNombre(rs.getString(2));
                arbitro.setPais(rs.getString(3));

                arbitros.add(arbitro);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return arbitros;
    }






    public void crearArbitro(Arbitro arbitro) {
        /*
        Inserte su código aquí
        */
    }

    public ArrayList<Arbitro> busquedaPais(String pais) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();
        /*
        Inserte su código aquí
        */
        return arbitros;
    }

    public ArrayList<Arbitro> busquedaNombre(String nombre) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();
        /*
        Inserte su código aquí
        */
        return arbitros;
    }

    public Arbitro buscarArbitro (int id){
        Arbitro arbitro = null;
        String sql = "SELECT * FROM arbitro WHERE idArbitro = ?";
        try(Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1,id);
            try (ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    arbitro = new Arbitro();
                    arbitro.setIdArbitro(id);
                    arbitro.setPais(rs.getString("pais"));
                    arbitro.setNombre(rs.getString("nombre"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arbitro;
    }

    public void borrarArbitro(int id) {
        /*
        Inserte su código aquí
        */
    }

    public void guardar(Arbitro arbitro) {

        String sql = "INSERT INTO `lab9`.`arbitro` (`nombre`, `pais`) VALUES (?, ?);";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, arbitro.getNombre());
            pstmt.setString(2, arbitro.getPais());
            
            pstmt.executeUpdate();

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

}
