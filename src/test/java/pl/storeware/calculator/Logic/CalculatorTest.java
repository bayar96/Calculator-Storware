package pl.storeware.calculator.Logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.storeware.calculator.Model.Action;
import pl.storeware.calculator.Model.Operation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


class CalculatorTest {


    private Calculator calculator;
    String resourcePath = "src/test/resources/";

    @BeforeEach
    public void setUp(){
        calculator = new Calculator();
    }

    @Test
    public void shouldReturnFileScanner(){
        Scanner scanner = calculator.getFileScanner(resourcePath, "file1.txt");
        Assertions.assertTrue(scanner.hasNext());
    }

    @Test()
    public void shouldThrowFileNotFoundWhenGettingFileScanner(){
        Assertions.assertThrows(RuntimeException.class, () -> calculator.getFileScanner(resourcePath, "noFile.txt"));
    }

    @Test
    public void shouldReturnActionListFromFile(){
        List<Action> actionList = calculator.getActionListFromFile(resourcePath, "file1.txt");
        Assertions.assertEquals(6, actionList.size());
        Assertions.assertEquals(Operation.ADD ,actionList.get(0).getOperation());
        Assertions.assertEquals(6 ,actionList.get(0).getNumber());
        Assertions.assertEquals(Operation.APPLY ,actionList.get(actionList.size()-1).getOperation());
        Assertions.assertEquals(10 ,actionList.get(actionList.size()-1).getNumber());
    }

    @Test
    public void shouldThrowNoSuchElementExceptionDuringGettingActionListFromFileWhenFileIsEmpty(){
        Assertions.assertThrows(NoSuchElementException.class, () -> calculator.getActionListFromFile(resourcePath, "file2.txt"));
    }

    @Test
    public void shouldThrowInputMismatchExceptionDuringGettingActionListFromFileWhenNumberProblemAfterOperation(){
        Assertions.assertThrows(InputMismatchException.class, () -> calculator.getActionListFromFile(resourcePath, "file3.txt"));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionDuringGettingActionListFromFileWhenWrongOperation(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.getActionListFromFile(resourcePath, "file4.txt"));
    }

    @Test
    public void shouldCompute1(){
        List<Action> actionList = calculator.getActionListFromFile(resourcePath, "file1.txt");
        Action apply = actionList.remove(actionList.size()-1);
        int result = calculator.compute(apply, actionList);
        Assertions.assertEquals(1, result);
    }

}