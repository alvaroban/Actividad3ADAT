package com.utad;

import models.Ticket;
import models.Usuario;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.utad.Main.session;

public class AtraccionDAO {

    public void crearUser(){
            session.beginTransaction();
            
            Usuario user = new Usuario();
            user.setEdad(33);
            user.setAltura(164.0);
            user.setNombre("Miriam");
            user.setPreferente(true);

            session.persist(user);

            Ticket ticket = new Ticket();
            ticket.setPrecio(10.0);
            ticket.setNombreAtraccion("Caseta de Santa");
            ticket.setIdUser(user);

            Set<Ticket> setTickets = new HashSet<>();
            setTickets.add(ticket);
            user.setTickets(setTickets);

            session.persist(user);
            session.getTransaction().commit();
    }

    public void eliminarUsuario(int idUsuario){
            Usuario user = session.get(Usuario.class, idUsuario);

            if (user != null) {
                session.beginTransaction();
                session.remove(user);
                session.getTransaction().commit();
                System.out.println("Usuario eliminado");
            } else {
                System.out.println("No se encontró el usuario con el ID: " + idUsuario);
            }

    }

    public void eliminarTicket(int idTicket){

            Ticket voleta = session.get(Ticket.class, idTicket);

            if (voleta != null) {
                session.beginTransaction();
                session.remove(voleta);
                session.getTransaction().commit();
                System.out.println("Ticket eliminado");
            } else {
                System.out.println("No se encontró el ticket con el ID: " + idTicket);
            }
    }

    public void buscarUsuario(String nombre) {
        String qry = "FROM Usuario WHERE nombre = :nombre";
        Usuario user = session.createQuery(qry, Usuario.class)
                .setParameter("nombre", nombre)
                .uniqueResult();

        if (user != null) {
            System.out.println("ID: " + user.getId() + ", Nombre: " + user.getNombre());
        } else {
            System.out.println("No se encontró el usuario con el nombre: " + nombre);
        }
    }

    public void buscarTicket(String atraccion) {
        String qry = "FROM Ticket WHERE nombreAtraccion = :nombre";
        Ticket voleta = session.createQuery(qry, Ticket.class)
                .setParameter("nombreAtraccion", atraccion)
                .uniqueResult();

        if (voleta != null) {
            System.out.println("ID: " + voleta.getId() + ", Atracción: " + voleta.getNombreAtraccion());
        } else {
            System.out.println("No se encontró la atracción: " + atraccion);
        }
    }

    public void actualizarUsuario(int idUsuario, String nombre) {
        Usuario user = session.get(Usuario.class, idUsuario);

        if (user != null) {
            user.setNombre(nombre);
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();

            System.out.println("Usuario actualizado correctamente.");
        } else {
            System.out.println("No se encontró el usuario con el ID: " + idUsuario);
        }
    }

    public void actualizarPrecioTicket(int idTicket, Double precio) {
        Ticket voleta = session.get(Ticket.class, idTicket);

        if (voleta != null) {
            voleta.setPrecio(precio);
            session.beginTransaction();
            session.merge(voleta);
            session.getTransaction().commit();

            System.out.println("Precio del ticket actualizado correctamente.");
        } else {
            System.out.println("No se encontró el ticket con el ID: " + idTicket);
        }
    }
}
