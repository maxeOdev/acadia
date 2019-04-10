package com.hb.Service;

import com.hb.Model.Video;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;

@Slf4j
public class VideoServiceTest extends AbstractApplicationTest {

	/**
	 * SHARED DATAS
	 */
	private static Video video1;
	
	@BeforeClass
	public static void launching() {
		
		log.info("************************************ STARTING VIDEO TEST ***************************************");
		
	}

	@AfterClass
	public static void ending() {
		
		log.info("************************************ THE END ***************************************");
	
	}

	/**
	 * Creating a little dataset in the database
	 */
	@Before
	public void createDataset() {
		
//		video1 = new Video("Apprendre à coder avec ses pieds", "/data/videos/video1.mp4", null);
//		video1 = videoService.createVideo(video1);
		
	}
	
	/**
	 * Clear the dataset in the database
	 */
	@After
	public void clearDataset() {
//		
//		videoService.deleteAllVideos();
//		
	}
	
	/**
	 * CREATE
	 */
	@Test
	public void create() {
		
//		/* DATASET */
//		Video video = new Video("Test video creation", "/data/videos/videoCreation.mp4", null);
//		video = videoService.createVideo(video);
//		
//		/* TESTS */
//		assertThat(video.getId(), notNullValue());
//		assertThat(video.getUuid(), notNullValue());
//		assertThat(video.getName(), notNullValue());
//		assertThat(video.getPath(), notNullValue());
//		assertThat(video.getTrainings(), nullValue());
//		
	}
	
	/**
	 * UPDATE 
	 */
	@Test
	public void update() {
		
//		/* CHANGES ON DATAS */
//		Video vid2modif = videoService.getByUuid(video1.getUuid());
//		vid2modif.setName("nameChangeees");
//		vid2modif.setPath("pathChangeees");
//		
//		
//		/* UPDATE */
//		vid2modif = videoService.updateVideo(vid2modif);
//		
//		/* TESTS */
//		assertEquals(vid2modif, videoService.getByUuid(vid2modif.getUuid()));
//		
	}
	
	/**
	 * GET
	 */
	@Test
	public void get() {
//		
//		/* GET */
//		Video gotVideo = videoService.getByUuid(video1.getUuid());
//		
//		/* TESTS */
//		assertEquals(video1, gotVideo);
//		
	}
	
	/**
	 * DELETE
	 */
	@Test
	public void delete() {
//		
//		/* GET AN ENTRY */
//		Video video = videoService.getByUuid(video1.getUuid());
//		assertThat(video, notNullValue());
//		
//		/* DELETE */
//		videoService.deleteVideo(video1);
//		video = videoService.getByUuid(video1.getUuid());
//		assertThat(video, nullValue());
//		
	}
	
	/**
	 * DELETE
	 */
	@Test
	public void count() {
//		
//		/* GET AN ENTRY */
//		assertEquals(1, videoService.count());
//		
//		/* DELETE */
//		videoService.deleteVideo(video1);
//		assertEquals(0, videoService.count());
//		
	}
	
	@Test
	public void deleteAllVideos() {
//		
//		Video video = new Video("Test testing tested", "/data/videos/tests.mp4", null);
//		video = videoService.createVideo(video);
//		assertThat(videoService.count(), equalTo(2L));
//		videoService.deleteAllVideos();
//		assertThat(videoService.count(), equalTo(0L));
		
	}
	
}
