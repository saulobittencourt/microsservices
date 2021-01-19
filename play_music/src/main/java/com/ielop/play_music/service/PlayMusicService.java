package com.ielop.play_music.service;

import com.ielop.play_music.dto.MusicDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class PlayMusicService {

    public MusicDTO loadMusicFile() throws IOException {
        // Indico a localização do arquivo
        File musicFile = ResourceUtils.getFile("classpath:mp3/01-rappa.mp3");
        // Verifico o tamanho do arquivo
        Long musicFileSize = Files.size(Paths.get(musicFile.toURI()));

        MediaType mediaType = MediaType.parseMediaType("application/octet-stream");

        // Crio o Stream que ser usado para retornar no response
        InputStreamResource resource = new InputStreamResource(new FileInputStream(musicFile));

        MusicDTO musicDTO = new MusicDTO();
        musicDTO.setMusicFile(musicFile);
        musicDTO.setMusicFileSize(musicFileSize);
        musicDTO.setMediaType(mediaType);
        musicDTO.setResource(resource);

        return musicDTO;
    }
}
