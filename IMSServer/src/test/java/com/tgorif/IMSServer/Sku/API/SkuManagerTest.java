package com.tgorif.IMSServer.Sku.API;

import com.tgorif.IMSServer.ImsServerApplication;
import com.tgorif.IMSServer.Sku.Core.NutritionData;
import com.tgorif.IMSServer.Sku.Core.Sku;
import com.tgorif.IMSServer.Sku.Core.SkuData;
import com.tgorif.IMSServer.Sku.Core.SkuEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImsServerApplication.class)
public class SkuManagerTest {

    @Autowired
    SkuManager skuManager;


    private static final SkuData sku_test_delete = new SkuData("Test_DeleteThis", "Test_DeleteThis",new NutritionData());
    private static final SkuData sku_test = new SkuData("Test_SKU","testsku)",new NutritionData());

    private static final SkuData sku_test_add = new SkuData("Test_Add","testadd",new NutritionData());
    private static final SkuData sku_test_not_present = new SkuData("Test_Not_Present","getSku_test_not_present",new NutritionData());

    private static final SkuEntity sku_entity_add = new SkuEntity(LocalDate.now(),sku_test_add.getBarcode());
    private static final Set<SkuEntity> entities = new HashSet<>();
    @Before
    public void setUp() {

        entities.add(new SkuEntity(LocalDate.now(),sku_test.getBarcode()));
        entities.add(new SkuEntity(LocalDate.now(),sku_test.getBarcode()));
        entities.add(new SkuEntity(LocalDate.now(),sku_test.getBarcode()));
        entities.add(new SkuEntity(LocalDate.now(),sku_test_delete.getBarcode()));
        entities.add(new SkuEntity(LocalDate.now(),sku_test_delete.getBarcode()));
        entities.add(new SkuEntity(LocalDate.now(),sku_test_delete.getBarcode()));
        entities.add(sku_entity_add);
        skuManager.saveSkuData(sku_test_delete);
        skuManager.saveSkuData(sku_test);
        for (SkuEntity entity : entities) {skuManager.saveEntity(entity);}
        entities.removeAll(entities);
    }
    @After
    public void tearDown() {
        List<SkuData> list = new ArrayList<>();
        list.add(sku_test_delete);
        list.add(sku_test);
        list.add(sku_test_add);
        for(SkuData s : list){
            skuManager.deleteEntities(s.getBarcode());
        }
        skuManager.deleteSku(sku_test_delete.getBarcode());
        skuManager.deleteSku(sku_test.getBarcode());
        skuManager.deleteSku(sku_test_add.getBarcode());

    }
    @Test
    public void saveEntity_whenSkuDoesNotExist_shouldReturnFalse() {
        // given
        boolean expected = false;
        SkuEntity entity = new SkuEntity(LocalDate.now(),sku_test_not_present.getBarcode());
        // when
        boolean actual = skuManager.saveEntity(entity);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void saveEntity_whenSkuExists_shouldReturnTrue() {
        // given
        skuManager.saveSkuData(sku_test_add);
        boolean expected = true;

        // when
        boolean actual = skuManager.saveEntity(sku_entity_add);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void SkuExists_whenSkuExists_shouldReturnTrue() {
        // given
        boolean expected = true;

        // when
        boolean actual = skuManager.skuExists(sku_test.getBarcode());

        // then
        assertEquals(expected, actual);
    }
    @Test
    public void SkuExists_whenSkuDoesNotExist_shouldReturnFalse() {
        // given
        boolean expected = false;

        // when
        boolean actual = skuManager.skuExists("DOES NOT EXIST");

        // then
        assertEquals(expected, actual);
    }
    @Test
    public void testGetSku_IfSkuExists_shouldReturnSku() {
        // given
        String expected = sku_test.getBarcode();

        // when
        String actual = skuManager.getSkuData(sku_test.getBarcode()).getBarcode();

        // then
        assertEquals(expected, actual);
    }
    @Test
    public void testGetSku_IfSkuNotExists_shouldReturnNull() {
        // given
        SkuData expected = null;

        // when
        SkuData actual = skuManager.getSkuData("DOES NOT EXIST");

        // then
        assertEquals(expected, actual);
    }
    @Test
    public void testSaveSku() {
        //given
        String  expected = sku_test_add.getBarcode();
        // when
        skuManager.saveSkuData(sku_test_add);
        //then
        assertEquals(expected,skuManager.getSkuData(sku_test_add.getBarcode()).getBarcode());
    }
    @Test
    public void testGetEntityForSku_whenSkuNotExist_ReturnEmpty() {
        // given
        boolean expected = true;

        // when
        Set<SkuEntity> actual = skuManager.getEntityForSku("DOES NOT EXIST");

        // then
        assertEquals(expected, actual.isEmpty());
    }
    @Test
    public void testGetEntityForSku_whenNoEntityForSku_ReturnEmpty() {
        // given
        boolean expected = true;
        skuManager.saveSkuData(sku_test_add);

        // when
        Set<SkuEntity> actual = skuManager.getEntityForSku(sku_test_add.getBarcode());

        // then
        assertEquals(expected, actual.isEmpty());
    }
    @Test
    public void testGetEntityForSku_whenSkuWith3Entity_ReturnSetSize3() {
        // given
        int expected = 3;

        // when
        Set<SkuEntity> actual = skuManager.getEntityForSku(sku_test.getBarcode());

        // then
        assertEquals(expected, actual.size());
    }
    @Test
    public void testGetEntityForSku_whenSkuWith3Entity_ReturnSetHasSameSku() {
        // given
        String expected= sku_test.getBarcode();
        // when
        Set<SkuEntity> actual = skuManager.getEntityForSku(sku_test.getBarcode());
        // then
        for(SkuEntity s : actual){
            assertEquals(expected,s.getBarcode());
        }
    }
    @Test
    public void testDeleteSku() {
        //given
        //when
        skuManager.deleteSku("Test_DeleteThis");
        //then
        assertEquals(null,skuManager.getSkuData("DELETE THIS"));
    }
    @Test
    public void testDeleteEntity() {
        //given
        int expected = 0;
        //when
        for(SkuEntity s : entities){
            if(s.getBarcode().equals("Test_DeleteThis")) {
                skuManager.deleteEntities(s.getBarcode());
            }
        }
        //then
        assertEquals(expected,skuManager.getEntityForSku("DELETE THIS").size());
    }
}