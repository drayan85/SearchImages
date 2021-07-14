package com.image.search.data.database.dao;


import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.image.search.data.database.AppDatabase;
import com.image.search.data.database.entity.ImageModelEntity;
import com.image.search.data.database.entity.mapper.ImageModelEntityMapper;
import com.image.search.domain.model.ImageModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 14th of July 2021
 */
@RunWith(AndroidJUnit4.class)
public class SearchImageDaoTest {

    private AppDatabase mAppDatabase;
    private SearchImageDao mSearchImageDao;
    private ImageModelEntity mImageModelEntity1, mImageModelEntity2, mImageModelEntity3;

    @Before
    public void createDataBase() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        mAppDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mSearchImageDao = mAppDatabase.searchImageDao();

        setImageModels();
    }

    private void setImageModels() {
        ImageModelEntityMapper mapper = new ImageModelEntityMapper();

        ImageModel imageModel1 = new ImageModel();
        imageModel1.setUrl("https://s.pacn.ws/1500/i8/t-h-e-limited-edition-328005.2.jpg?o3ydth");
        imageModel1.setName("");
        imageModel1.setTitle("J-Pop - T H E [Limited Edition] (Tricot) to");
        imageModel1.setThumbnail("https://rapidapi.usearch.com/api/thumbnail/get?value\u003d8413020404504575875");
        imageModel1.setHeight(512);
        imageModel1.setWidth(512);
        imageModel1.setThumbnailHeight(64);
        imageModel1.setThumbnailWidth(64);
        imageModel1.setImageWebSearchUrl("https://usearch.com/search/the/images");
        imageModel1.setWebpageUrl("https://www.audiotool.com/track/979tu2kt/");

        mImageModelEntity1 = mapper.transForm(imageModel1);

        ImageModel imageModel2 = new ImageModel();
        imageModel2.setUrl("https://CNTSYNCONT.images.worldnow.com/images/18829576_G.jpg?lastEditedDate\\u003d20191006211923");
        imageModel2.setName("");
        imageModel2.setTitle("Want to toast someone on their birthday or anniversary? Use the");
        imageModel2.setThumbnail("https://rapidapi.usearch.com/api/thumbnail/get?value\u003d4635220413032217724");
        imageModel2.setHeight(647);
        imageModel2.setWidth(940);
        imageModel2.setThumbnailHeight(242);
        imageModel2.setThumbnailWidth(351);
        imageModel2.setImageWebSearchUrl("https://usearch.com/search/the/images");
        imageModel2.setWebpageUrl("http://kake.com/story/41146550/want-to-toast-someone-on-their-birthday-or-anniversary-use-these-7-questions-to-prepare");

        mImageModelEntity2 = mapper.transForm(imageModel2);

        ImageModel imageModel3 = new ImageModel();
        imageModel3.setUrl("http://2.bp.blogspot.com/_sfw0cPTAuWs/TFbB4yO-nVI/AAAAAAAASgc/2xE8liNQAVs/w1200-h630-p-k-no-nu/obama+bday+card.jpg");
        imageModel3.setName("");
        imageModel3.setTitle("* T h e  *  V i s u a l  *  V a m p  *: Sign The President\u0027s Birthday Card to");
        imageModel3.setThumbnail("https://rapidapi.usearch.com/api/thumbnail/get?value\u003d5589560759783002234");
        imageModel3.setHeight(293);
        imageModel3.setWidth(435);
        imageModel3.setThumbnailHeight(219);
        imageModel3.setThumbnailWidth(325);
        imageModel3.setImageWebSearchUrl("https://usearch.com/search/the/images");
        imageModel3.setWebpageUrl("http://thevisualvamp.blogspot.com/2010/08/sign-president-birthday-card_882.html");

        mImageModelEntity3 = mapper.transForm(imageModel3);
    }

    @Test
    public void getImageModelsWhenNoItemsInserted() {
        ImageModelEntity[] imageModelEntities = mSearchImageDao.getPaginatedImagesBasedOnQuery(10, 0, "the").blockingGet();
        assertEquals(0, imageModelEntities.length);
    }

    @Test
    public void countImageModelsAvailableForGivenQueryString() {
        mSearchImageDao.insertImageModelEntities(mImageModelEntity1); // not contain word 'the'
        mSearchImageDao.insertImageModelEntities(mImageModelEntity2); // contain word 'the'
        mSearchImageDao.insertImageModelEntities(mImageModelEntity3); // contain word 'the'

        int count = mSearchImageDao.getTotalNumberOfItemsForGivenSearchQuery("the").blockingGet(0);

        assertEquals(count, 2);
    }

    @Test
    public void getImageModelsAfterInsertedForGivenQueryString() {
        mSearchImageDao.insertImageModelEntities(mImageModelEntity1); // contain word 'to'
        mSearchImageDao.insertImageModelEntities(mImageModelEntity2); // contain word 'to'
        mSearchImageDao.insertImageModelEntities(mImageModelEntity3); // contain word 'to'

        ImageModelEntity[] imageModelEntities = mSearchImageDao.getPaginatedImagesBasedOnQuery(10, 0, "to").blockingGet();

        int count = mSearchImageDao.getTotalNumberOfItemsForGivenSearchQuery("to").blockingGet(0);

        assertEquals(count, imageModelEntities.length);
    }

    @After
    public void closeDb() {
        mAppDatabase.close();
    }

}