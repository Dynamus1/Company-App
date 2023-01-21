package com.personPractice.services;

import com.personPractice.models.Department;
import com.personPractice.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    DepartmentRepository departmentRepository;

    @InjectMocks
    DepartmentServiceImpl departmentService;

    Department department;

    @BeforeEach
    void setUp() {

        department = new Department();

        department.setId(1L);
    }

    @Test
    @Disabled
    void getDepartments() {

        List<Department> departments = new ArrayList<>();

        departments.add(new Department());
        departments.add(new Department());

        when(departmentService.getDepartments()).thenReturn(departments);
        List<Department> departmentList = departmentService.getDepartments();

        assertNotNull(departmentList);
        assertEquals(2,departments.size());


    }

    @Test
    void findAll() {
        Set<Department> returnDepartmentsSet = new HashSet<>();
        returnDepartmentsSet.add(new Department());
        returnDepartmentsSet.add(new Department());

        when(departmentRepository.findAll()).thenReturn(returnDepartmentsSet);
        Set<Department> departments = departmentService.findAll();

        assertNotNull(departments);
        assertEquals(2,departments.size());
    }

    @Test
    void findById() {

        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));

        Department department = departmentService.findById(1L);

        assertNotNull(department);


    }

    @Test
    void save() {

        Department departmentToSave = new Department();
        departmentToSave.setId(1L);

        when(departmentRepository.save(any())).thenReturn(department);

        Department savedDepartment = departmentService.save(departmentToSave);

        assertNotNull(savedDepartment);

        verify(departmentRepository).save(any());
    }

    @Test
    void delete() {

        departmentService.delete(department);

        verify(departmentRepository).delete(any());

    }

    @Test
    void deleteById() {

        departmentService.deleteById(1L);

        verify(departmentRepository, times(1)).deleteById(anyLong());
    }
}