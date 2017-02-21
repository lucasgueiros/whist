/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.partida;

import com.github.lucasgueiros.whist.partida.eventos.EventoPartida;
import com.github.lucasgueiros.whist.partida.eventos.ListenerPartida;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lucas
 */
@WebServlet(name = "AtualizarPaginaJogarServlet", urlPatterns = {"/AtualizarPaginaJogarServlet"})
public class AtualizarPaginaJogarServlet extends HttpServlet {
    
    public enum TipoDeEvento {
        MUDANCA_DE_VEZ,FIM_DE_VAZA,FIM_DE_JOGO, ESPERE;
    }
    
    private class Listener implements ListenerPartida{
                private Queue<TipoDeEvento> queue;
                
                public Listener(Queue<TipoDeEvento> queue){
                    this.queue = queue;
                }
                
                @Override
                public void partidaAcabou(EventoPartida evento) {
                    //queue.add(TipoDeEvento.FIM_DE_JOGO);
                }

                @Override
                public void alguemJogou(EventoPartida evento) {
                    if(evento.getPartidaAcabou()){
                        queue.add(TipoDeEvento.ESPERE);
                    } else {
                        queue.add(TipoDeEvento.MUDANCA_DE_VEZ);
                    }
                }

                @Override
                public void vazaAcabou(EventoPartida evento) {
                    //queue.add(TipoDeEvento.FIM_DE_VAZA);
                    
                }
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Queue<TipoDeEvento> mudancasDeVez = (Queue<TipoDeEvento>) request.getSession().getAttribute("eventos");
        PartidaSessionBean partidaSessionBean = ((PartidaSessionBean)request.getSession().getAttribute("partidaSessionBean"));
        if(partidaSessionBean == null) return;
        if(mudancasDeVez==null){
            mudancasDeVez = new LinkedList<>();
            partidaSessionBean.getPartida().addListener(new Listener(mudancasDeVez));
            request.getSession().setAttribute("eventos", mudancasDeVez);
            // atualize tudo, a partir de agora estou no controle!
            //partidaSessionBean.atualizarTudo();
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("true");
            return;
        }
        //partidaSessionBean.atualizarTudo();
        response.setContentType("text/plain;charset=UTF-8");
        if(partidaSessionBean.isMudou()){
            response.getWriter().print("true");
            partidaSessionBean.setMudou(false);
            return;
        } else if(mudancasDeVez.peek()==null){
            response.getWriter().print("false");
        } else if(mudancasDeVez.peek() == TipoDeEvento.MUDANCA_DE_VEZ){
            response.getWriter().print("true");
        } else if(mudancasDeVez.peek() == TipoDeEvento.ESPERE){
            response.getWriter().print("wait");
        }
        mudancasDeVez.poll();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
