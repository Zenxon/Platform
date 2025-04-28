/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k2124884.Platform.controller;

/**
 *
 * @author zenas
 */
import com.k2124884.Platform.model.MusicFile;
import com.k2124884.Platform.service.MusicFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/music")
public class MusicFileController {
    @Autowired
    private MusicFileService musicFileService;
    @GetMapping("/all")
    public List<MusicFile> getAllMusicFiles() {
        return musicFileService.getAllMusicFiles();
    }
    @PostMapping("/save")
    public MusicFile saveMusicFile(@RequestBody MusicFile musicFile) {
        // Placeholder userId; replace with actual user authentication logic
        Long userId = 1L;
        return musicFileService.saveMusicFile(musicFile, userId);
    }
}