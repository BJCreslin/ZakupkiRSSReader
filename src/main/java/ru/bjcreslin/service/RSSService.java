package ru.bjcreslin.service;

import ru.bjcreslin.configuration.RSSServerCoviguration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
                .setHeader("User-Agent", RSSServerCoviguration.USER_AGENT)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

        var responseText = response.body();

        return responseText;
    }
}
