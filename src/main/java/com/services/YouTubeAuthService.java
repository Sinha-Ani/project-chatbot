package com.services;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.services.youtube.YouTube;
import com.google.api.client.json.gson.GsonFactory;




@Service
public class YouTubeAuthService {
    
    private static final String CLIENT_SECRET_FILE = "client_secret.json";
    private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/youtube.force-ssl");

    public YouTube getYouTubeService() throws Exception{
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
            GsonFactory .getDefaultInstance(),
            new InputStreamReader(
                new FileInputStream( CLIENT_SECRET_FILE)
            )
        );
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            com.google.api.client.googleapis.javanet.GoogleNetHttpTransport.newTrustedTransport(),
            GsonFactory .getDefaultInstance(),
            clientSecrets,
            SCOPES
        ).setAccessType("offline").build();

        Credential credential = new AuthorizationCodeInstalledApp(
            flow,
            new LocalServerReceiver()
        ).authorize("user");

        return new YouTube.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            GsonFactory .getDefaultInstance(),
            credential
        ).setApplicationName("YouTubeBot").build();
    }
    
}
