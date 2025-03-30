package controllers;

import java.util.ArrayList;
import java.util.List;

import exception.ClienteNoEncontradoException;
import model.Cliente;
import model.Reserva;

public class ClienteController {
    private List<Cliente> clientes;

    public ClienteController() {
        this.clientes = new ArrayList<>();
    }

    public Cliente agregarCliente( String nombreCompleto) {
        Cliente cliente = new Cliente(nombreCompleto);
        clientes.add(cliente);
        return cliente;
    }

    public Cliente buscarClientePorId(String id) throws ClienteNoEncontradoException {
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente con ID " + id + " no encontrado"));
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    public List<Reserva> obtenerReservasActivasCliente(String idCliente) throws ClienteNoEncontradoException {
        Cliente cliente = buscarClientePorId(idCliente);
        return cliente.getReservasActivas();
    }

    public List<Reserva> obtenerHistorialReservasCliente(String idCliente) throws ClienteNoEncontradoException {
        Cliente cliente = buscarClientePorId(idCliente);
        return cliente.getHistorialReservas();
    }
}
