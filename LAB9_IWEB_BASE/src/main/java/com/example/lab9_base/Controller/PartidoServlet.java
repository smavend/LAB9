package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Dao.DaoArbitros;
import com.example.lab9_base.Dao.DaoPartidos;
import com.example.lab9_base.Dao.DaoSelecciones;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PartidoServlet", urlPatterns = {"/PartidoServlet", ""})
public class PartidoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        DaoSelecciones daoSelecciones = new DaoSelecciones();
        DaoPartidos daoPartidos = new DaoPartidos();
        DaoArbitros daoArbitros = new DaoArbitros();
        ArrayList<Partido> partidosList = new ArrayList<>();
        RequestDispatcher view;
        Partido partido = null;

        switch (action) {

            case "guardar":
                partido = new Partido();
                partido.setNumeroJornada(Integer.parseInt(request.getParameter("jornada")));
                partido.setFecha(request.getParameter("fecha"));
                partido.setSeleccionLocal(daoSelecciones.obtenerSeleccionXId(Integer.parseInt(request.getParameter("local"))));
                partido.setSeleccionVisitante(daoSelecciones.obtenerSeleccionXId(Integer.parseInt(request.getParameter("visitante"))));
                partido.setArbitro(daoArbitros.buscarArbitro(Integer.parseInt(request.getParameter("arbitro"))));
<<<<<<< HEAD
                partidosList = daoPartidos.listaDePartidos();
                boolean valid = true;
                for (Partido p: partidosList){
                    if(p.getSeleccionLocal().getIdSeleccion()==partido.getSeleccionLocal().getIdSeleccion()){
                        valid = false;
                    }
                }
                if(valid){
                    if(partido.getSeleccionLocal().getIdSeleccion()==partido.getSeleccionVisitante().getIdSeleccion()){
                        valid = false;
                    }
                }
                if(valid){
                    if(daoPartidos.crearPartido(partido)){
                        response.sendRedirect(request.getContextPath());
                    }
                    else{
                        request.setAttribute("listaSel", daoSelecciones.listarSelecciones());
                        request.setAttribute("arbitros", daoArbitros.listarArbitros());
                        view = request.getRequestDispatcher("partidos/form.jsp");
                        view.forward(request, response);
                    }

                }
                else{
                    request.setAttribute("listaSel", daoSelecciones.listarSelecciones());
                    request.setAttribute("arbitros", daoArbitros.listarArbitros());
                    view = request.getRequestDispatcher("partidos/form.jsp");
                    view.forward(request, response);
                }
=======

                daoPartidos.crearPartido(partido);

                response.sendRedirect(request.getContextPath() + "/PartidoServlet");

>>>>>>> f9d24a6b148ef246da1a86fa01efc7b72ec62ab5

                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DaoPartidos daoPartidos = new DaoPartidos();
        DaoSelecciones daoSelecciones = new DaoSelecciones();
        DaoArbitros daoArbitros = new DaoArbitros();
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        switch (action) {
            case "lista":
                request.setAttribute("listaPartidos", daoPartidos.listaDePartidos());
                view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
                break;
            case "crear":
                request.setAttribute("listaSel", daoSelecciones.listarSelecciones());
                request.setAttribute("arbitros", daoArbitros.listarArbitros());
                view = request.getRequestDispatcher("partidos/form.jsp");
                view.forward(request, response);
                break;

        }

    }
}
