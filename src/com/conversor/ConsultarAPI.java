package com.conversor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class ConsultarAPI {

    public Conversor obtenerTasa(String monedaBase, String monedaDestino) {
        // Construimos la URL para la API usando las monedas
        URI url = URI.create("https://v6.exchangerate-api.com/v6/b67a00bbb3f485c4e441570d/pair/" + monedaBase + "/" + monedaDestino);

        // Creamos el cliente HTTP
        HttpClient cliente = HttpClient.newHttpClient();

        // Construimos la solicitud
        HttpRequest solicitud = HttpRequest.newBuilder().uri(url).build();

        try {
            // Enviamos la solicitud y guardamos la respuesta como String
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

            // Convertimos el JSON en un objeto Conversor usando Gson
            return new Gson().fromJson(respuesta.body(), Conversor.class);

        } catch (IOException | InterruptedException e) {
            System.out.println("Ocurrió un error al consultar la API: " + e.getMessage());
            return null;
        }
    }
}
