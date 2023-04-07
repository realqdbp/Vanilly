package codes.qdbp.serverplugin.misc;


import codes.qdbp.serverplugin.Serverplugin;
import com.google.gson.Gson;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;

public class UpdateChecker {

    public UpdateChecker() throws IOException, InterruptedException, URISyntaxException {

        Transcript transcript;

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.github.com/repos/realqdbp/Serverplugin/releases/latest"))
                .build();


        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();

        transcript = gson.fromJson(getResponse.body(), Transcript.class);

        if (transcript.tag_name.equals(Serverplugin.getPluginTagName())) {
            Bukkit.getLogger().log(Level.INFO, "ServerPlugin up to date :)");
        } else {
            Bukkit.getLogger().log(Level.WARNING, "There is a new Plugin version available here: https://github.com/realqdbp/ServerPlugin/releases/latest");
            Serverplugin.setPluginUpToDate(false);
        }
    }
}