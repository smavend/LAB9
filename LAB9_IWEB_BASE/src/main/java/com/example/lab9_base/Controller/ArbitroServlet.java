package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Dao.DaoArbitros;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ArbitroServlet", urlPatterns = {"/ArbitroServlet"})
public class ArbitroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        Arbitro arbitro = new Arbitro();
        DaoArbitros daoArbitros = new DaoArbitros();
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");

        switch (action) {

            case "lista":



            case "crear":

                break;

            case "buscar":


                String tipo = request.getParameter("tipo");
                ArrayList<Arbitro> listarbitro;
                switch (tipo){
                    case "nombre":
                         listarbitro = daoArbitros.busquedaNombre(tipo);
                        request.setAttribute("listarbitro", listarbitro);

                        view= request.getRequestDispatcher("list.jsp");
                        view.forward(request, response);
                        break;

                    case "pais":
                        listarbitro = daoArbitros.busquedaPais(tipo);
                        request.setAttribute("listarbitro", listarbitro);

                        view = request.getRequestDispatcher("list.jsp");
                        view.forward(request, response);
                        break;

                }

                break;

            case "guardar":

                String nombreArb = request.getParameter("nombre");
                String paisArb = request.getParameter("pais");

                Arbitro arbitro1 = new Arbitro();
                arbitro1.setNombre(nombreArb);
                arbitro1.setPais(paisArb);
                daoArbitros.guardar(arbitro1);


                response.sendRedirect(request.getContextPath() + "/JobServlet");


                break;

            case "borrar":  // JobServlet?action=borrar&id=50

                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        ArrayList<String> paises = new ArrayList<>();
        paises.add("Peru");
        paises.add("Chile");
        paises.add("Argentina");
        paises.add("Paraguay");
        paises.add("Uruguay");
        paises.add("Colombia");
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");
        DaoArbitros daoArbitros = new DaoArbitros();

        switch (action) {
            case "lista":
                request.setAttribute("listaArbitro", daoArbitros.listarArbitros());
                view = request.getRequestDispatcher("/arbitros/list.jsp");
                view.forward(request, response);
                break;
            case "crear":
                request.setAttribute("listapaises", paises);
                view = request.getRequestDispatcher("/arbitros/form.jsp");
                view.forward(request, response);

                break;
            case "borrar":
                String arbitroID = request.getParameter("id");
                daoArbitros.borrarArbitro(arbitroID);

                response.sendRedirect(request.getContextPath() + "/ArbitroServlet");
                break;
        }
    }
}
