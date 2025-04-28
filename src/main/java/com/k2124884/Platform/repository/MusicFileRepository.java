/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k2124884.Platform.repository;

/**
 *
 * @author zenas
 */
import com.k2124884.Platform.model.MusicFile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MusicFileRepository extends JpaRepository<MusicFile, Long> {
    List<MusicFile> findByProjectId(Long projectId);
}
