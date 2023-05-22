package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public class SantanderJiraAPI {
    public static void main(String[] args) {
        String jiraUrl = "https://gostoldos.atlassian.net";
        String projectKey = "SAN";
        String username = "gostoldos@gmail.com";
        String apiToken = "ATATT3xFfGF0T-qPBQ65kmi4Fbt4QVc3-QooybLhkPphujZrfhjC8GERWnA8CNGLxYAbzMt0MkWOe9M1Xbb25g2P3xVl95XQ1IwqmZXXduPVQIhfD71iv4-VsYgXanwZPPyg95Iptg0G8CYl3yx-Y3_oD6EO6uxlCW3KGSP0SW0-xUQjcI0Va-E=E3AC4E6B";

        try {
            // Creating an HTTP connection with Jira REST API
            String apiUrl = jiraUrl + "/rest/api/3/search?jql=project=" + projectKey;
            URI url = new URI(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.toURL().openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", getBasicAuth(username, apiToken));

            int statusCode = connection.getResponseCode();

            if (statusCode == 200) {
                // Receiving and processing responses
                String jsonResponse = new BufferedReader(new InputStreamReader(connection.getInputStream()))
                        .lines()
                        .collect(Collectors.joining(""));

                // JSON data processing
                JSONObject json = new JSONObject(jsonResponse);
                JSONArray issues = json.getJSONArray("issues");

                // Displaying the issue key and its summary
                for (int i = 0; i < issues.length(); i++) {
                    JSONObject issue = issues.getJSONObject(i);
                    String key = issue.getString("key");
                    String summary = issue.getJSONObject("fields").getString("summary");

                    System.out.println("Key: " + key);
                    System.out.println("Summary: " + summary);
                    System.out.println();
                }
            } else {
                System.out.println("Error: " + statusCode);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    // Method for generating authorization header in Basic Auth format
    private static String getBasicAuth(String username, String password) {
        String auth = username + ":" + password;
        return "Basic " + java.util.Base64.getEncoder().encodeToString(auth.getBytes());
    }
}