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
        ArrayList<Arbitro> listaArbitro;
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("Nombre");
        opciones.add("Pais");

        switch (action) {


            case "buscar":
                String busqueda = request.getParameter("buscar");
                String tipo = request.getParameter("tipo");

                if (tipo.equalsIgnoreCase("Nombre")){

                    request.setAttribute("listaArbitro", daoArbitros.busquedaNombre(busqueda));

                }else{

                    request.setAttribute("listaArbitro", daoArbitros.busquedaPais(busqueda));
                }
                request.setAttribute("listaopciones",opciones);
                view = request.getRequestDispatcher("arbitros/list.jsp");
                view.forward(request, response);




                break;

            case "guardar":

                String nombreArb = request.getParameter("nombre");
                String paisArb = request.getParameter("pais");

                Arbitro arbitro1 = new Arbitro();
                arbitro1.setNombre(nombreArb);
                arbitro1.setPais(paisArb);
                daoArbitros.guardar(arbitro1);
                response.sendRedirect(request.getContextPath() + "/ArbitroServlet");
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
        opciones.add("Nombre");
        opciones.add("Pais");
        DaoArbitros daoArbitros = new DaoArbitros();

        switch (action) {
            case "lista":
                request.setAttribute("listaArbitro", daoArbitros.listarArbitros());
                request.setAttribute("listaopciones",opciones);
                view = request.getRequestDispatcher("arbitros/list.jsp");
                view.forward(request, response);
                break;
            case "crear":
                request.setAttribute("listapaises", paises);
                view = request.getRequestDispatcher("arbitros/form.jsp");
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
