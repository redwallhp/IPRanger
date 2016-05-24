package io.github.redwallhp.ipranger;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MCBouncerRequest {


    private String apiKey;


    public MCBouncerRequest(String apiKey) {
        this.apiKey = apiKey;
    }


    public void ban(ProxiedPlayer player, String reason) throws APIException {
        HashMap<String, Object> fields = new HashMap<String, Object>();
        fields.put("user_id", player.getUniqueId().toString());
        fields.put("username", player.getName());
        fields.put("reason", reason);
        fields.put("issuer_id", "00000000-0000-0000-0000-000000000000"); // console UUID
        post("ban", fields);
    }


    private JSONObject post(String resource, final Map<String, Object> fields) throws APIException {

        if (apiKey == null || apiKey.equalsIgnoreCase("REPLACE")) {
            throw new APIException("API key not set");
        }

        String url = String.format("http://mcbouncer.com/api/v2/%s", resource);
        HttpRequestWithBody req = Unirest.post(url);
        req.header("Authorization", "APIKey " + apiKey);

        JSONObject fobj = new JSONObject();
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            fobj.put(entry.getKey(), entry.getValue());
        }

        req.body(fobj.toString());

        try {
            HttpResponse<JsonNode> response = req.asJson();
            JSONObject obj = response.getBody().getObject();
            if (response.getStatus() >= 400) {
                throw new APIException("MCBouncer error: " + obj.getString("message"));
            }
            return obj;
        } catch (UnirestException ex) {
            throw new APIException("MCBouncer request failed");
        }

    }


}
