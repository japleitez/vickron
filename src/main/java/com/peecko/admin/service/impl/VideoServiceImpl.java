package com.peecko.admin.service.impl;

import com.peecko.admin.domain.Video;
import com.peecko.admin.repository.VideoRepository;
import com.peecko.admin.service.VideoService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Video}.
 */
@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    private final Logger log = LoggerFactory.getLogger(VideoServiceImpl.class);

    private final VideoRepository videoRepository;

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public Video save(Video video) {
        log.debug("Request to save Video : {}", video);
        return videoRepository.save(video);
    }

    @Override
    public Video update(Video video) {
        log.debug("Request to update Video : {}", video);
        return videoRepository.save(video);
    }

    @Override
    public Optional<Video> partialUpdate(Video video) {
        log.debug("Request to partially update Video : {}", video);

        return videoRepository
            .findById(video.getId())
            .map(existingVideo -> {
                if (video.getCode() != null) {
                    existingVideo.setCode(video.getCode());
                }
                if (video.getType() != null) {
                    existingVideo.setType(video.getType());
                }
                if (video.getState() != null) {
                    existingVideo.setState(video.getState());
                }
                if (video.getName() != null) {
                    existingVideo.setName(video.getName());
                }
                if (video.getLang() != null) {
                    existingVideo.setLang(video.getLang());
                }
                if (video.getUrl() != null) {
                    existingVideo.setUrl(video.getUrl());
                }

                return existingVideo;
            })
            .map(videoRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Video> findAll(Pageable pageable) {
        log.debug("Request to get all Videos");
        return videoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Video> findOne(Long id) {
        log.debug("Request to get Video : {}", id);
        return videoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Video : {}", id);
        videoRepository.deleteById(id);
    }
}
