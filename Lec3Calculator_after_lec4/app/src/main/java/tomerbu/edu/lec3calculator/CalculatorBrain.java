package tomerbu.edu.lec3calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

//sqrt and PI
public class CalculatorBrain {

    //Double is immutable
    private Double accumulator;//tempValue

    private Map<String, Operation> operations = new HashMap<>();


    //Extensible
    public CalculatorBrain() {
        operations.put("π", new ConstantOperation(Math.PI));//const 3.14
        operations.put("e", new ConstantOperation(Math.E));//const 2.71

        //method reference(takes a double and returns a double)
        operations.put("√", new UnaryOperation(Math::sqrt));  //(x)->√(x)
        operations.put("cos", new UnaryOperation(Math::cos)); //double->double
        operations.put("sin", new UnaryOperation(Math::sin)); //double->double
        operations.put("%", new UnaryOperation(x -> x / 100));
        operations.put("+/-", new UnaryOperation(x -> -x));


        //noinspection Convert2MethodRef
        operations.put("+", new BinaryOperation((x, y) -> x + y));//Double::sum
        operations.put("-", new BinaryOperation((x, y) -> x - y));
        operations.put("÷", new BinaryOperation((x, y) -> x / y));
        operations.put("x", new BinaryOperation((x, y) -> x * y));

        operations.put("=", new EqualsOperation());
        //stack exchange code review
    }

    //*+-=(operator)
    public void performOperation(String symbol) {//π

        Operation operation = operations.get(symbol);//unary, constant, binary
        if (operation == null) return;//not in the map

        switch (operation.getOperationType()) {
            case CONSTANT:
                accumulator = operation.asConstant().getValue();
                break;
            case UNARY:
                performPendingOperation();
                //sqrt
                if (accumulator != null)//result = cos(x)
                    accumulator = operation.asUnary().getFunction().apply(accumulator);
                break;
            case BINARY://3 * 4
                boolean didPerform = performPendingOperation();
                //save double and the func, description
                if (accumulator != null) {
                    BiFunction<Double, Double, Double> binary = operation.asBinary().getOperation();//func (x, y->x+y)
                    pbo = new PendingBinaryOperation(accumulator, binary);
                    if (!didPerform) { // 3*5=
                        accumulator = null;
                    }
                }
                break;
            case EQUALS:
                performPendingOperation();
                break;
        }
    }

    private boolean performPendingOperation() {
        if (pbo != null && accumulator != null) {
            accumulator = pbo.applyWith(accumulator);
            //accumulator = 7
            pbo = null; // 3*
            return true;
        }
        return false;
    }

    //save operator(func) and operand(number)
    //3+4
    private PendingBinaryOperation pbo = null;

    //what do we want to save:
    private static class PendingBinaryOperation {
        private final double firstOperand;//3
        private final BiFunction<Double, Double, Double> func;//(x, y)-> after .apply(x+y)->

        //ctor:
        public PendingBinaryOperation(double firstOperand, BiFunction<Double, Double, Double> func) {
            this.firstOperand = firstOperand;
            this.func = func;
        }

        public double applyWith(double secondOperand /*4*/) {
            return func.apply(firstOperand, secondOperand);
        }
    }

    //save a number
    //89(operand)
    public void setOperand(Double operand) {
        accumulator = operand;
    }

    //get the result:
    public Double getResult() {
        return accumulator;
    }
}
