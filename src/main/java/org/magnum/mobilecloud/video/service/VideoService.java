package org.magnum.mobilecloud.video.service;

import org.magnum.mobilecloud.video.repository.Video;
import org.magnum.mobilecloud.video.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    public Collection<Video> getAll() {
        return videoRepository.findAll();
    }

    public Video getVideoById(long id) {
        Video video = videoRepository.findOne(id);
        if (video != null) {
            return video;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public Video save(Video video) {
        return videoRepository.save(video);
    }

    public ResponseEntity like(long id, String name) {
        Video video = videoRepository.findOne(id);
        if (video == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (video.getLikedBy().contains(name)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        video.setLikes(video.getLikes() + 1);
        video.getLikedBy().add(name);
        videoRepository.save(video);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity unlike(long id, String name) {
        Video video = videoRepository.findOne(id);
        if (video == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (!video.getLikedBy().contains(name)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        video.setLikes(video.getLikes() - 1);
        video.getLikedBy().remove(name);
        videoRepository.save(video);
        return new ResponseEntity(HttpStatus.OK);
    }

    public Collection<Video> findByName(String title) {
        return videoRepository.findByName(title);
    }

    public Collection<Video> findByDurationLessThan(long duration) {
        return videoRepository.findByDurationLessThan(duration);
    }
}
