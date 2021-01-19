package com.ielop.play_music.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;

import java.io.File;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicDTO implements Serializable {
    private File musicFile;
    private Long musicFileSize;
    private MediaType mediaType;
    private InputStreamResource resource;
}
