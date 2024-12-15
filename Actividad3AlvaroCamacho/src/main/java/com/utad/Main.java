package com.utad;


import org.hibernate.Session;

public class Main {

    public static Session session = null;

    public static void main(String[] args) {
        try {
            session = HibernateUtil.getSession();
            AtraccionDAO att = new AtraccionDAO();
            //att.crearUser();
            //att.eliminarUsuario(3);
            //att.eliminarTicket(3);
            //att.actualizarUsuario(5, "Pedro");
            att.actualizarPrecioTicket(4, 10.00);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

}