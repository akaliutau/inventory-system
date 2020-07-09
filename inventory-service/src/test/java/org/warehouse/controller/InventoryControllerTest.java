package org.warehouse.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.warehouse.model.Item;
import org.warehouse.service.InventoryService;
import org.warehouse.util.DataUtils;
import org.warehouse.util.JsonUtil;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class InventoryControllerTest {

    public static final String base = "/api/v1/items";
    public static final String base_with_param = "/api/v1/items?page=%d";

    @InjectMocks
    private InventoryController inventoryController;

    @MockBean
    private InventoryService inventoryService;

    @Autowired
    private MockMvc mockMvc;

    private static Page<Item> getPage() {
        Pageable p = PageRequest.of(0, 5);
        return new PageImpl<>(DataUtils.genItems(2), p, 2);
    }

    @Test
    public void testFindAllPage0() throws Exception {
        Page<Item> page = getPage();
        when(inventoryService.getAll(0)).thenReturn(page);
        HttpHeaders httpHeaders = new HttpHeaders();

        MvcResult res = this.mockMvc.perform(get(base).headers(httpHeaders)).andDo(print()).andExpect(status().isOk())
                .andReturn();

        // check results
        Page<Item> result = JsonUtil.fromJson(res.getResponse().getContentAsString());
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());

        //check the service has been called with specified argument
        verify(inventoryService, times(1)).getAll(0);
    }

    @Test
    public void testFindAllPage1() throws Exception {
        Page<Item> page = getPage();
        when(inventoryService.getAll(1)).thenReturn(page);
        HttpHeaders httpHeaders = new HttpHeaders();

        MvcResult res = this.mockMvc.perform(get(String.format(base_with_param, 1)).headers(httpHeaders)).andDo(print()).andExpect(status().isOk())
                .andReturn();

        // check results
        Page<Item> result = JsonUtil.fromJson(res.getResponse().getContentAsString());
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());

        //check the service has been called with specified argument
        verify(inventoryService, times(1)).getAll(1);

    }

}
