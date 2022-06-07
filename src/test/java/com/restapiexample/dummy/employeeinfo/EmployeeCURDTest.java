package com.restapiexample.dummy.employeeinfo;

import com.restapiexample.dummy.testbase.TestBase;
import com.restapiexample.dummy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;

import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;


@RunWith(SerenityRunner.class)
public class EmployeeCURDTest extends TestBase {

    static String name = "Prime" + TestUtils.getRandomValue();
    static String salary = "100000" + TestUtils.getRandomValue();
    static String age = "21";
    static int employeeId;

    @Steps
    EmployeeSteps employeeSteps;

    @Title("This will create a new employee")
    @Test
    public void test001() {
        ValidatableResponse response = employeeSteps.createEmployee(name, salary, age);
        response.log().all().statusCode(200);
    }
    @Test
    public void test002() {
        HashMap<String, Object> employeeMap = employeeSteps.getStudentInfoByFirstname(name);
        Assert.assertThat(employeeMap, hasValue(name));
        employeeId = (int) employeeMap.get("id");
        System.out.println(employeeId);
    }

    @Test
    public void test003() {

        name = name + "_updated";

        employeeSteps.updateStudent(name, employeeId).log().all().statusCode(200);

        HashMap<String, Object> employeeMap = employeeSteps.getStudentInfoByFirstname(name);
        Assert.assertThat(employeeMap, hasValue(name));

    }

    @Test
    public void test004() {
        employeeSteps.deletEmployee(employeeId).statusCode(204);
        employeeSteps.getEmployeeById(employeeId).statusCode(404);
    }


}
