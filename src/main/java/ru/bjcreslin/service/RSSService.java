package ru.bjcreslin.service;

import org.springframework.stereotype.Service;
import ru.bjcreslin.configuration.RSSServerConfiguration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class RSSService {

    /**
     * Метод возвращающий ответ от закупок
     *
     * @param queryString адрес запроса. берем с сайта zakupki.gov.ru
     * @return XML документ
     */
    public String getXMLFromServer(String queryString) throws IOException, InterruptedException {

        var client = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        var request = HttpRequest
                .newBuilder()
                .GET()
                .uri(URI.create(queryString))
                .setHeader("User-Agent", RSSServerConfiguration.USER_AGENT)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
