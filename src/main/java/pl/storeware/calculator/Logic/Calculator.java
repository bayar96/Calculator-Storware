package pl.storeware.calculator.Logic;

import pl.storeware.calculator.Model.Action;
import pl.storeware.calculator.Model.Operation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Calculator {

    public void run(String resourcePath, String fileName){
        List<Action> actionList = getActionListFromFile(resourcePath, fileName);
        Action apply = actionList.remove(actionList.size()-1);
        System.out.println(compute(apply, actionList));
    }

    public void run(){
        run("src/main/resources/", "file1.txt");
    }

    public List<Action> getActionListFromFile(String resourcePath, String fileName){

        Scanner fileReader = getFileScanner(resourcePath, fileName);
        if(!fileReader.hasNext()){ throw new NoSuchElementException("File is empty."); }

        List<Action> actionList = new ArrayList<>();
        Operation operationInput = null;

        try {
            while(operationInput!=Operation.APPLY) {
                operationInput = Operation.valueOf(fileReader.next().toUpperCase());
                int numberInput = fileReader.nextInt();
                actionList.add(new Action(operationInput, numberInput));
            }
        } catch (InputMismatchException inputMismatchException) {
            throw new InputMismatchException("Wrong value");
        } catch (IllegalArgumentException illegalArgumentException){
            throw new IllegalArgumentException("Wrong operation name: "+illegalArgumentException.getMessage());
        }finally {
            fileReader.close();
        }
        return actionList;
    }

    public Scanner getFileScanner(String resourcePath, String fileName){
        File file = new File(resourcePath+fileName);
        Scanner fileReader;
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException fileNotFoundException) {
            throw new RuntimeException("File not found");
        }
        return fileReader;
    }

    public int compute(Action apply, List<Action> actionList){
        for (Action action:actionList){
            switch (action.getOperation()){
                case ADD -> apply = apply.add(action);
                case SUB -> apply = apply.sub(action);
                case DIV -> apply = apply.div(action);
                case MUL -> apply = apply.mul(action);
                case MOD -> apply = apply.mod(action);
            }
        }
        return apply.getNumber();
    }
}
