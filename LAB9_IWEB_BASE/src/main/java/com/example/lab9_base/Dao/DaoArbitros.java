package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;

import java.util.ArrayList;
import java.sql.*;

public class DaoArbitros extends DaoBase {




    public ArrayList<Arbitro> listarArbitros() {
        ArrayList<Arbitro> arbitros = new ArrayList<>();
        String sql = "SELECT * FROM lab9.arbitro";

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

    public Arbitro buscarArbitro(int id) {
        Arbitro arbitro = new Arbitro();
        /*
        Inserte su código aquí
        */
        return arbitro;
    }

    public void borrarArbitro(int id) {
        /*
        Inserte su código aquí
        */
    }
}
