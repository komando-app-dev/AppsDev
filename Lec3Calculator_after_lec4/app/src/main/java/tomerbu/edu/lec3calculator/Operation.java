package tomerbu.edu.lec3calculator;

import java.util.function.BiFunction;
import java.util.function.DoubleFunction;

//OOP Design: Design Patterns
public abstract class Operation { //no properties
    //types: class interface enum
    enum OperationType {
        //Switch case is easy!
        UNARY, BINARY, CONSTANT, EQUALS// NULLARY
    }

    //abstract getter:
    public abstract OperationType getOperationType();

    //syntactic sugar: (easy casting)
    //casting support (switch case on the type)
    public ConstantOperation asConstant() {
        return (ConstantOperation) this;
    }
    public UnaryOperation asUnary() {
        return (UnaryOperation) this;
    }
    public BinaryOperation asBinary() {
        return (BinaryOperation) this;
    }
}

//π is Operation
//+ is Operation
//√ is Operation

//()->Double, (Double)->Double, (Double, Double)->Double
class ConstantOperation extends Operation {
    private Double constant;//3.14

    public ConstantOperation(Double constant) {
        this.constant = constant;
    }

    public Double getValue() {
        return constant;
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.CONSTANT;
    }
}

//one operand
class UnaryOperation extends Operation {
    //cos(x) = f(X)
    //function that takes a double and returns a double
    private DoubleFunction<Double> unaryFunction; //(double)->double

    //ctor: takes the function and stores it for later.
    public UnaryOperation(DoubleFunction<Double> unaryFunction) {
        this.unaryFunction = unaryFunction;
    }

    public DoubleFunction<Double> getFunction() {
        return unaryFunction;
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.UNARY;
    }
}

//a+b->c
class BinaryOperation extends Operation {
    private BiFunction<Double, Double, Double> binaryFunction; //(Double, Double)->Double

    //ctor:
    public BinaryOperation(BiFunction<Double, Double, Double> binaryFunction) {
        this.binaryFunction = binaryFunction;
    }

    //getOperation
    public BiFunction<Double, Double, Double> getOperation() {
        return binaryFunction;
    }

    @Override
    public OperationType getOperationType() {
        return  OperationType.BINARY;
    }
}

//just store the enum type.
//switch case and map
class EqualsOperation extends Operation {

    //switch case support:
    @Override
    public OperationType getOperationType() {
        return OperationType.EQUALS;
    }
}
//poly morphism
//class InvalidRadiusException extends RuntimeException{}

