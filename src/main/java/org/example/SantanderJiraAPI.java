package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class SantanderJiraAPI {
    public static void main(String[] args) {
        String jiraUrl = "https://gostoldos.atlassian.net";
        String projectKey = "SAN";
        String username = "gostoldos@gmail.com";
        String apiToken = "ATATT3xFfGF0D-taTAiRvnhU5Mem8K9w9vEzWSH-NHtlErqJg3ag6X5Iiz7GUYsuwb9UPWUOfBJ6z2j4F-GPlSyeoDHtq-2t7ncf1IvNgjtG5abxV976H5WMsubslVxHPrYAtlrbjBXJQwi7p_7Cb-7Xko7D_YJm_w8GxLGcTjorBH7n1DDv_2k=481899B1";

        try {
            // Creating an HTTP connection with Jira REST API
            String apiUrl = jiraUrl + "/rest/api/3/search?jql=project=" + projectKey;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method for generating authorization header in Basic Auth format
    private static String getBasicAuth(String username, String password) {
        String auth = username + ":" + password;
        return "Basic " + java.util.Base64.getEncoder().encodeToString(auth.getBytes());
    }
}