package com.ielop.play_music.controller;

import com.ielop.play_music.dto.MusicDTO;
import com.ielop.play_music.service.PlayMusicService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("v1/playmusic")
public class PlayMusicController {
    private final PlayMusicService playMusicService;

    @GetMapping("/play")
    public ResponseEntity<InputStreamResource> playMusic() throws IOException {
        MusicDTO musicDTO = playMusicService.loadMusicFile();

        // Crio o response
        return ResponseEntity.ok()
                .contentType(musicDTO.getMediaType())
                .contentLength(musicDTO.getMusicFileSize())
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + musicDTO.getMusicFile().getName() + "\"")
                .body(musicDTO.getResource());
    }
}
