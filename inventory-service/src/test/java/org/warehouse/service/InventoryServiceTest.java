package org.warehouse.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.warehouse.model.Item;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@ActiveProfiles("test")
public class InventoryServiceTest {

	@Autowired
	InventoryService inventoryService;
	
    @Before
    public void init() {
    }
    
    @After
	public void after() {
    }

	@Test
	public void testGetAll() {
		
		Page<Item> result = inventoryService.getAll(0);
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.hasContent());
        Assert.assertEquals(5, result.getContent().size());
        
        Assert.assertEquals(0, result.getNumber());
        Assert.assertEquals(2, result.getTotalPages());
        
		Assert.assertTrue(result.hasNext());
		Assert.assertFalse(result.hasPrevious());
	}
	
	@Test
	public void testGetAllNonExistingPage() {
		
		Page<Item> result = inventoryService.getAll(2);
		
		Assert.assertNotNull(result);
		Assert.assertFalse(result.hasContent());
	}

	@Test
	public void testGetAllWrongInput() {

		Page<Item> result = inventoryService.getAll(-1);
		// must return page #0
		Assert.assertNotNull(result);
		Assert.assertTrue(result.hasContent());
        Assert.assertEquals(0, result.getNumber());

	}

}
