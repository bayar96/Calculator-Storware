package pl.storeware.calculator.Model;

public class Action {
    Operation operation;
    int number;

    public Action(Operation operation, int number) {
        this.operation = operation;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Action{" +
                "operation=" + operation +
                ", number=" + number +
                '}';
    }

    public Operation getOperation() {
        return operation;
    }

    public int getNumber() {
        return number;
    }

    public Action add(Action action){
        return new Action(Operation.APPLY, this.number+action.number);
    }

    public Action sub(Action action){
        return new Action(Operation.APPLY, this.number-action.number);
    }

    public Action mul(Action action){
        return new Action(Operation.APPLY, this.number*action.number);
    }

    public Action div(Action action){
        return new Action(Operation.APPLY, this.number/action.number);
    }

    public Action mod(Action action){
        return new Action(Operation.APPLY, this.number%action.number);
    }


}
