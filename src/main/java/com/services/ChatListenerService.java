package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.LiveChatMessage;
import com.google.api.services.youtube.model.LiveChatMessageListResponse;
import com.google.api.services.youtube.model.LiveChatMessageSnippet;
import com.google.api.services.youtube.model.LiveChatTextMessageDetails;
import java.util.List;

@Service
public class ChatListenerService {

    @Autowired
    private YouTubeAuthService authService;

    @Autowired
    private YouTubeStreamService streamService;

    @Scheduled(fixedDelay = 5000) // poll every 5 seconds
    public void pollMessages() throws Exception {
        YouTube youtube = authService.getYouTubeService();
        String streamId = streamService.getLatestStreamId();
        if (streamId == null) {
            System.out.println("No active stream found.");
            return;
        }
        String liveChatId = streamService.getLiveChatId(streamId);
        if (liveChatId == null) {
            System.out.println("No live chat found for stream: " + streamId);
            return;
        }

        YouTube.LiveChatMessages.List request = youtube.liveChatMessages()
                .list(liveChatId, List.of("snippet", "authorDetails"));

        LiveChatMessageListResponse response = request.execute();

        for (LiveChatMessage msg : response.getItems()) {
            String author = msg.getAuthorDetails().getDisplayName();
            String text = msg.getSnippet().getDisplayMessage();
            System.out.println(author + ": " + text);

            respondToMessage(youtube, liveChatId, author, text);
        }
    }

    private void respondToMessage(YouTube youtube, String liveChatId, String author, String text) throws Exception {
        LiveChatMessage reply = new LiveChatMessage();
        LiveChatMessageSnippet snippet = new LiveChatMessageSnippet();
        snippet.setLiveChatId(liveChatId);
        snippet.setType("textMessageEvent");

        LiveChatTextMessageDetails details = new LiveChatTextMessageDetails();
        details.setMessageText("Hello " + author + ", thanks for your message!");
        snippet.setTextMessageDetails(details);

        reply.setSnippet(snippet);

        youtube.liveChatMessages().insert(List.of("snippet"), reply).execute();
    }
}