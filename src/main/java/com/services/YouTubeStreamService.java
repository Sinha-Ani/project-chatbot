package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.LiveBroadcastListResponse;
import com.google.api.services.youtube.model.VideoListResponse;
import java.util.List;

@Service
public class YouTubeStreamService {

    @Autowired
    private YouTubeAuthService authService;

    public String getLatestStreamId() throws Exception {
        YouTube youtube = authService.getYouTubeService();
        YouTube.LiveBroadcasts.List request = youtube.liveBroadcasts()
                .list(List.of("id", "snippet"))
                .setBroadcastStatus("active")
                .setMine(true);

        LiveBroadcastListResponse response = request.execute();
        if (!response.getItems().isEmpty()) {
            return response.getItems().get(0).getId();
        }
        return null;
    }

    public String getLiveChatId(String streamId) throws Exception {
        YouTube youtube = authService.getYouTubeService();
        YouTube.Videos.List request = youtube.videos()
                .list(List.of("liveStreamingDetails"))
                .setId(List.of(streamId));

        VideoListResponse response = request.execute();
        return response.getItems().get(0).getLiveStreamingDetails().getActiveLiveChatId();
    }
}
