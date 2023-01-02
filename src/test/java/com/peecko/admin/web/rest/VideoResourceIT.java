package com.peecko.admin.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.peecko.admin.IntegrationTest;
import com.peecko.admin.domain.Video;
import com.peecko.admin.domain.enumeration.Type;
import com.peecko.admin.domain.enumeration.VideoState;
import com.peecko.admin.repository.VideoRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link VideoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VideoResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Type DEFAULT_TYPE = Type.FITNESS;
    private static final Type UPDATED_TYPE = Type.WELLNESS;

    private static final VideoState DEFAULT_STATE = VideoState.CREATED;
    private static final VideoState UPDATED_STATE = VideoState.PUBLISHED;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LANG = "AAAAAAAAAA";
    private static final String UPDATED_LANG = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/videos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVideoMockMvc;

    private Video video;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Video createEntity(EntityManager em) {
        Video video = new Video()
            .code(DEFAULT_CODE)
            .type(DEFAULT_TYPE)
            .state(DEFAULT_STATE)
            .name(DEFAULT_NAME)
            .lang(DEFAULT_LANG)
            .url(DEFAULT_URL);
        return video;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Video createUpdatedEntity(EntityManager em) {
        Video video = new Video()
            .code(UPDATED_CODE)
            .type(UPDATED_TYPE)
            .state(UPDATED_STATE)
            .name(UPDATED_NAME)
            .lang(UPDATED_LANG)
            .url(UPDATED_URL);
        return video;
    }

    @BeforeEach
    public void initTest() {
        video = createEntity(em);
    }

    @Test
    @Transactional
    void createVideo() throws Exception {
        int databaseSizeBeforeCreate = videoRepository.findAll().size();
        // Create the Video
        restVideoMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(video))
            )
            .andExpect(status().isCreated());

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeCreate + 1);
        Video testVideo = videoList.get(videoList.size() - 1);
        assertThat(testVideo.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testVideo.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testVideo.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testVideo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVideo.getLang()).isEqualTo(DEFAULT_LANG);
        assertThat(testVideo.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    @Transactional
    void createVideoWithExistingId() throws Exception {
        // Create the Video with an existing ID
        video.setId(1L);

        int databaseSizeBeforeCreate = videoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVideoMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(video))
            )
            .andExpect(status().isBadRequest());

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = videoRepository.findAll().size();
        // set the field null
        video.setCode(null);

        // Create the Video, which fails.

        restVideoMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(video))
            )
            .andExpect(status().isBadRequest());

        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = videoRepository.findAll().size();
        // set the field null
        video.setType(null);

        // Create the Video, which fails.

        restVideoMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(video))
            )
            .andExpect(status().isBadRequest());

        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = videoRepository.findAll().size();
        // set the field null
        video.setState(null);

        // Create the Video, which fails.

        restVideoMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(video))
            )
            .andExpect(status().isBadRequest());

        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = videoRepository.findAll().size();
        // set the field null
        video.setName(null);

        // Create the Video, which fails.

        restVideoMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(video))
            )
            .andExpect(status().isBadRequest());

        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLangIsRequired() throws Exception {
        int databaseSizeBeforeTest = videoRepository.findAll().size();
        // set the field null
        video.setLang(null);

        // Create the Video, which fails.

        restVideoMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(video))
            )
            .andExpect(status().isBadRequest());

        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = videoRepository.findAll().size();
        // set the field null
        video.setUrl(null);

        // Create the Video, which fails.

        restVideoMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(video))
            )
            .andExpect(status().isBadRequest());

        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllVideos() throws Exception {
        // Initialize the database
        videoRepository.saveAndFlush(video);

        // Get all the videoList
        restVideoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(video.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].lang").value(hasItem(DEFAULT_LANG)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)));
    }

    @Test
    @Transactional
    void getVideo() throws Exception {
        // Initialize the database
        videoRepository.saveAndFlush(video);

        // Get the video
        restVideoMockMvc
            .perform(get(ENTITY_API_URL_ID, video.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(video.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.lang").value(DEFAULT_LANG))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL));
    }

    @Test
    @Transactional
    void getNonExistingVideo() throws Exception {
        // Get the video
        restVideoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingVideo() throws Exception {
        // Initialize the database
        videoRepository.saveAndFlush(video);

        int databaseSizeBeforeUpdate = videoRepository.findAll().size();

        // Update the video
        Video updatedVideo = videoRepository.findById(video.getId()).get();
        // Disconnect from session so that the updates on updatedVideo are not directly saved in db
        em.detach(updatedVideo);
        updatedVideo.code(UPDATED_CODE).type(UPDATED_TYPE).state(UPDATED_STATE).name(UPDATED_NAME).lang(UPDATED_LANG).url(UPDATED_URL);

        restVideoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVideo.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedVideo))
            )
            .andExpect(status().isOk());

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
        Video testVideo = videoList.get(videoList.size() - 1);
        assertThat(testVideo.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testVideo.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testVideo.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testVideo.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVideo.getLang()).isEqualTo(UPDATED_LANG);
        assertThat(testVideo.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    void putNonExistingVideo() throws Exception {
        int databaseSizeBeforeUpdate = videoRepository.findAll().size();
        video.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVideoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, video.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(video))
            )
            .andExpect(status().isBadRequest());

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVideo() throws Exception {
        int databaseSizeBeforeUpdate = videoRepository.findAll().size();
        video.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(video))
            )
            .andExpect(status().isBadRequest());

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVideo() throws Exception {
        int databaseSizeBeforeUpdate = videoRepository.findAll().size();
        video.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoMockMvc
            .perform(
                put(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(video))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVideoWithPatch() throws Exception {
        // Initialize the database
        videoRepository.saveAndFlush(video);

        int databaseSizeBeforeUpdate = videoRepository.findAll().size();

        // Update the video using partial update
        Video partialUpdatedVideo = new Video();
        partialUpdatedVideo.setId(video.getId());

        partialUpdatedVideo.type(UPDATED_TYPE).lang(UPDATED_LANG);

        restVideoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVideo.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVideo))
            )
            .andExpect(status().isOk());

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
        Video testVideo = videoList.get(videoList.size() - 1);
        assertThat(testVideo.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testVideo.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testVideo.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testVideo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVideo.getLang()).isEqualTo(UPDATED_LANG);
        assertThat(testVideo.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    @Transactional
    void fullUpdateVideoWithPatch() throws Exception {
        // Initialize the database
        videoRepository.saveAndFlush(video);

        int databaseSizeBeforeUpdate = videoRepository.findAll().size();

        // Update the video using partial update
        Video partialUpdatedVideo = new Video();
        partialUpdatedVideo.setId(video.getId());

        partialUpdatedVideo
            .code(UPDATED_CODE)
            .type(UPDATED_TYPE)
            .state(UPDATED_STATE)
            .name(UPDATED_NAME)
            .lang(UPDATED_LANG)
            .url(UPDATED_URL);

        restVideoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVideo.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVideo))
            )
            .andExpect(status().isOk());

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
        Video testVideo = videoList.get(videoList.size() - 1);
        assertThat(testVideo.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testVideo.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testVideo.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testVideo.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVideo.getLang()).isEqualTo(UPDATED_LANG);
        assertThat(testVideo.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    void patchNonExistingVideo() throws Exception {
        int databaseSizeBeforeUpdate = videoRepository.findAll().size();
        video.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVideoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, video.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(video))
            )
            .andExpect(status().isBadRequest());

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVideo() throws Exception {
        int databaseSizeBeforeUpdate = videoRepository.findAll().size();
        video.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(video))
            )
            .andExpect(status().isBadRequest());

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVideo() throws Exception {
        int databaseSizeBeforeUpdate = videoRepository.findAll().size();
        video.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVideoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(video))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Video in the database
        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVideo() throws Exception {
        // Initialize the database
        videoRepository.saveAndFlush(video);

        int databaseSizeBeforeDelete = videoRepository.findAll().size();

        // Delete the video
        restVideoMockMvc
            .perform(delete(ENTITY_API_URL_ID, video.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Video> videoList = videoRepository.findAll();
        assertThat(videoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
