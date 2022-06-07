package com.restapiexample.dummy.employeeinfo;


import com.restapiexample.dummy.constants.EndPoints;
import com.restapiexample.dummy.model.EmployeePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class EmployeeSteps {

    @Step
    public ValidatableResponse createEmployee(String name,String salary,String age) {
        EmployeePojo employeePojo = new EmployeePojo();
        employeePojo.setName(name);
        employeePojo.setSalary(salary);
        employeePojo.setAge(age);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(employeePojo)
                .when()
                .post(EndPoints.CREATE_EMPLOYEE)
                .then();
    }
    @Step
    public HashMap<String, Object> getStudentInfoByFirstname(String name) {
        String p1 = "findAll{it.name=='";
        String p2 = "'}.get(0)";
        HashMap<String, Object> employeeMap = SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_EMPLOYEE)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + name + p2);
        return employeeMap;
    }

    @Step
    public ValidatableResponse updateStudent(String name,int employeeId) {
        EmployeePojo employeePojo = new EmployeePojo();
        employeePojo.setName(name);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("employeeId", employeeId)
                .body(employeePojo)
                .when()
                .put(EndPoints.UPDATE_EMPLOYEE_BY_ID)
                .then();
    }

    @Step
    public ValidatableResponse deletEmployee(int employeeId){
        return SerenityRest.given().log().all()
                .pathParam("employeeId", employeeId)
                .when()
                .delete(EndPoints.DELETE_EPMLOYEE_BY_ID)
                .then();
    }

    @Step
    public ValidatableResponse getEmployeeById(int employeeId){
        return SerenityRest.given().log().all()
                .pathParam("employeeId", employeeId)
                .when()
                .get(EndPoints.GET_ALL_EMPLOYEE)
                .then();
    }



}
