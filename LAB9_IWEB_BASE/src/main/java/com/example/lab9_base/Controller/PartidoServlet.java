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
                partidosList = daoPartidos.listaDePartidos();
                for (Partido p: partidosList){
                    if(partido.)
                }
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
