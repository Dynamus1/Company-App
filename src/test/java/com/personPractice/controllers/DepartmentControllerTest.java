package com.personPractice.controllers;

import com.personPractice.controllers.DepartmentController;
import com.personPractice.models.Department;
import com.personPractice.services.DepartmentService;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.testng.AssertJUnit.assertEquals;


public class DepartmentControllerTest {
    @Mock
    DepartmentService departmentService;
    @Mock
    Model model;

    DepartmentController departmentController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        departmentController = new DepartmentController(departmentService);
    }

    @Test
    public void getListOfDepartments() {

        //given
        List<Department> departments = new ArrayList<>();
        departments.add(new Department());

        Department department = new Department();
        department.setId(1L);

        departments.add(department);

        when(departmentService.getDepartments()).thenReturn(departments);

        ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(List.class);


        //when
        String isDepartments = departmentController.getListOfDepartments(model);

        //then
        assertEquals("department/index",isDepartments);
        verify(departmentService,times(1)).getDepartments();
        verify(model,times(1)).addAttribute(eq("departments"),argumentCaptor.capture());
        List<Department> listInDepartmentController = argumentCaptor.getValue();
        assertEquals(2,listInDepartmentController.size());
    }

    @Test
    public void findById() throws Exception {
        Department department = new Department();
        department.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();

        when(departmentService.findById(anyLong())).thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/departments/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("department/show"));
    }
}